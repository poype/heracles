package com.poype.heracles.common.template;

import com.poype.heracles.common.dto.BaseResult;
import com.poype.heracles.common.dto.error.BizScene;
import com.poype.heracles.common.dto.error.CommonError;
import com.poype.heracles.common.dto.error.ErrorCode;
import com.poype.heracles.common.enums.BusinessErrorCode;
import com.poype.heracles.common.exception.BusinessException;
import com.poype.heracles.common.util.LogUtil;
import com.poype.heracles.common.util.ThreadLocalHolder;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ExecuteTemplate {

    private static final Logger logger = LoggerFactory.getLogger(ExecuteTemplate.class);

    public void execute(BaseResult result, ExecuteCallback executeCallback) {
        try {
            // default is success
            result.setSuccess(true);

            executeCallback.check();

            executeCallback.doService();
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setError(buildCommonError(e.getBizScene(), e.getBusinessError(), e.getMessage()));
        } catch (Throwable e) {
            // 打印error级别错误日志
            LogUtil.error(logger, e);
            result.setSuccess(false);
            result.setError(buildCommonError(ThreadLocalHolder.getBizScene(), BusinessErrorCode.SYSTEM_ERROR, e.getMessage()));
        }
    }

    /**
     * 在CommonError中附加错误信息
     *
     * @param bizScene      业务场景枚举，用于拼装ErrorCode对象
     * @param businessError 错误码枚举，用于拼装ErrorCode对象
     * @param description   错误具体描述，可以为empty。如果为description是empty，则用BusinessErrorEnum已经给定的description作为错误描述
     */
    private CommonError buildCommonError(BizScene bizScene, BusinessErrorCode businessError, String description) {
        ErrorCode errorCode = new ErrorCode(businessError.getErrorLevel(), bizScene.getSceneCode(),
                businessError.getSpecificCode());

        // 构造CommonError对象
        String errorMessage = StringUtils.isNotBlank(description) ? description : businessError.getDescription();
        return new CommonError(errorCode, errorMessage);
    }
}
