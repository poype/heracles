package com.poype.heracles.core.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.poype.heracles.common.constant.Constants;
import com.poype.heracles.common.util.AssertUtil;
import com.poype.heracles.common.util.LogUtil;
import com.poype.heracles.core.domain.model.Environment;
import com.poype.heracles.core.domain.model.HostConfig;
import com.poype.heracles.core.domain.model.application.Application;
import com.poype.heracles.core.domain.model.application.JavaApplication;
import com.poype.heracles.core.domain.model.application.config.ApplicationConfig;
import com.poype.heracles.core.domain.model.enums.ApplicationConfigTypeEnum;
import com.poype.heracles.core.domain.model.enums.EnvironmentType;
import com.poype.heracles.core.domain.model.enums.HardwareLevelEnum;
import com.poype.heracles.core.domain.service.ConfigFileService;
import com.poype.heracles.core.repository.EnvironmentRepository;
import com.poype.heracles.core.repository.HostConfigRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static com.poype.heracles.common.enums.BusinessErrorCode.SYSTEM_ERROR;

@Service("configFileService")
public class ConfigFileServiceImpl implements ConfigFileService {
    private static final Logger logger = LoggerFactory.getLogger(ConfigFileService.class);

    @Resource
    private HostConfigRepository hostConfigRepository;

    @Resource
    private EnvironmentRepository environmentRepository;

    private final String HOST_IP = "10.47.52.70";

    @Override
    public void generateConfigFileForJavaApp(JavaApplication app) {
        // 遍历每个环境，为应用创建values运维文件
        for (EnvironmentType envType : EnvironmentType.values()) {
            Map<String, Object> valueConfigMap = generateValuesConfigMap(app, envType);
            Yaml yaml = new Yaml();
            String fileContent = yaml.dumpAsMap(valueConfigMap);

            writeContentToFile(Constants.OPS_WORKSPACE_DIR + app.getApplicationName()
                    + "/values-" + envType.getName() +".yaml", fileContent);
        }
    }

    /**
     * 为运维生成values-{envType}文件
     * @param application 应用模型
     * @param envType 对应环境
     * @return 包含文件内容的map
     */
    private Map<String, Object> generateValuesConfigMap(JavaApplication application,
                                                        EnvironmentType envType) {
        Map<String, Object> valuesMap = new TreeMap<>();
        addCommonToValueConfig(valuesMap, envType);
        addHostToValueConfig(valuesMap, application, envType);
        addIngressToValueConfig(valuesMap);
        addResourcesToValueConfig(valuesMap, application, envType);
        addVolumeToValueConfig(valuesMap);
        return valuesMap;
    }

    /**
     * 添加Volume配置
     */
    private void addVolumeToValueConfig(Map<String, Object> valuesMap) {
        Map<String, String> mountItem = new TreeMap<>();
        mountItem.put("name", "logs");
        mountItem.put("mountPath", "/wls/wls81/logs");

        List<Map<String, String>> mountItemList = new ArrayList<>();
        mountItemList.add(mountItem);

        valuesMap.put("volumeMounts", mountItemList);

        // ------------------------volumes--------------------------

        Map<String, Object> volumeItem = new TreeMap<>();
        volumeItem.put("name", "logs");

        Map<String, String> pathItem = new TreeMap<>();
        pathItem.put("path", "/data/logs");
        volumeItem.put("hostPath", pathItem);

        List<Map<String, Object>> volumeItemList = new ArrayList<>();
        volumeItemList.add(volumeItem);

        valuesMap.put("volumes", volumeItemList);
    }

    /**
     * 添加资源配置
     */
    private void addResourcesToValueConfig(Map<String, Object> valuesMap,
                                           Application application,
                                           EnvironmentType envType) {
        Map<String, Object> resourcesMap = new TreeMap<>();
        Map<String, String> requestsMap = new TreeMap<>();
        requestsMap.put("cpu", "0.5");
        requestsMap.put("memory", "1024Mi");

        Map<String, String> limitsMap = new TreeMap<>();
        for (ApplicationConfig config : application.getConfigs()) {
            if (config.getConfigType() == ApplicationConfigTypeEnum.HARDWARE_LEVEL) {
                Map<String, String> hardwareLevelMap = JSON.parseObject(config.getConfigValue(),
                        new TypeReference<Map<String, String>>(){});
                if (hardwareLevelMap.get(envType.getName()) != null) {
                    HardwareLevelEnum level =
                            HardwareLevelEnum.getByName(hardwareLevelMap.get(envType.getName()));
                    limitsMap.put("cpu", level.getCpu() + "");
                    limitsMap.put("memory", level.getMemory() + "Mi");
                }
                break;
            }
        }
        if (limitsMap.isEmpty()) {
            // 使用默认的环境配置
            Environment environment = environmentRepository.queryByEnvName(envType.getName());
            limitsMap.put("cpu", environment.getDefaultCpuOfApp() + "");
            limitsMap.put("memory", environment.getDefaultMemoryOfApp() + "Mi");
        }

        resourcesMap.put("requests", requestsMap);
        resourcesMap.put("limits", limitsMap);
        valuesMap.put("resources", resourcesMap);
    }

    /**
     * 添加ingress配置
     */
    private void addIngressToValueConfig(Map<String, Object> valuesMap) {
        Map<String, Boolean> ingressMap = new TreeMap<>();
        ingressMap.put("enabled", true);
        valuesMap.put("ingress", ingressMap);
    }

    /**
     * 添加域名配置
     */
    private void addHostToValueConfig(Map<String, Object> valuesMap,
                                      JavaApplication application,
                                      EnvironmentType envType) {
        Map<String, Object> hostAliases = new TreeMap<>();
        hostAliases.put("ip", HOST_IP);

        List<String> hostNames = new ArrayList<>();
        for (ApplicationConfig config : application.getConfigs()) {
            if (config.getConfigType() == ApplicationConfigTypeEnum.HOST) {
                List<String> titles = JSON.parseArray(config.getConfigValue(), String.class);
                for (String title : titles) {
                    HostConfig hostConfig = hostConfigRepository.queryByConfigName(title);
                    if (envType == EnvironmentType.PROD || envType == EnvironmentType.RC) {
                        hostNames.add(hostConfig.getProdHostName());
                    } else {
                        hostNames.add(hostConfig.getTestHostName());
                    }
                }
                break;
            }
        }
        hostAliases.put("hostNames", hostNames);
        valuesMap.put("hostAliases", hostAliases);
    }

    /**
     * 向valueConfig中添加common信息
     */
    private void addCommonToValueConfig(Map<String, Object> valuesMap,
                                        EnvironmentType envType) {
        valuesMap.put("node", "FLS-AFLM-SPRINTBOOT-APP-CMN-prod");
        valuesMap.put("replicaCount", 1);
        valuesMap.put("java_opts", "-XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -XX:MaxRAMPercentage=70.0");
        valuesMap.put("httpGetPath", "/actuator/health");
        valuesMap.put("port", "");
        valuesMap.put("imageName", "");
        valuesMap.put("namespace", "");
        valuesMap.put("ServiceName", "");
        valuesMap.put("env", envType.getName());
        valuesMap.put("apolloEnv", "PRO");
        valuesMap.put("apolloCluster", "default");
    }

    /**
     * 把生成好的内容写到指定的文件中
     * @param path 文件路径
     * @param content 文件内容
     */
    private void writeContentToFile(String path, String content) {
        File file = new File(path);
        File fileParent = file.getParentFile();
        FileWriter fileWriter = null;
        try {
            if (!fileParent.exists()) {
                fileParent.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }

            fileWriter = new FileWriter(file);
            fileWriter.write(content);
        } catch (IOException e) {
            LogUtil.info(logger, "write file error: " + e);
            AssertUtil.isTrue(false, SYSTEM_ERROR);
        } finally {
            try {
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException e) {
                LogUtil.info(logger, "close file error: " + e);
                AssertUtil.isTrue(false, SYSTEM_ERROR);
            }
        }
    }
}

