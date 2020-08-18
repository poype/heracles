package com.poype.heracles.core.repository.dao;

import com.poype.heracles.core.repository.dao.model.ApplicationConfigDO;
import com.poype.heracles.core.repository.dao.model.ApplicationDO;
import com.poype.heracles.core.repository.dao.model.JavaApplicationDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface ApplicationDAO {

    void saveApplication(ApplicationDO applicationDO);

    void saveJavaApplication(JavaApplicationDO javaApplicationDO);

    void saveApplicationConfig(ApplicationConfigDO applicationConfigDO);

    ApplicationDO queryApplicationById(@Param("applicationId") String applicationId);

    ApplicationDO queryApplicationByName(@Param("applicationName") String applicationName);

    List<ApplicationDO> queryPageOfApplication(@Param("offset") int offset);

    JavaApplicationDO queryJavaApplicationById(@Param("applicationId") String applicationId);

    List<ApplicationConfigDO> queryConfigListByAppId(@Param("applicationId") String applicationId);
}
