package com.poype.heracles.common.aop;

import com.poype.heracles.common.util.LogUtil;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Digest日志拦截器
 */
@Component("digestLogInterceptor")
public class DigestLogInterceptor implements MethodInterceptor {

    private static final Logger digestLogger = LoggerFactory.getLogger("DIGEST");

    private static final Logger logger = LoggerFactory.getLogger("DIGEST");

    public Object invoke(MethodInvocation invocation) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object invocationResult = null;

        try {
            invocationResult = invocation.proceed();
            return invocationResult;
        } catch (Throwable e) {
            throw e;
        } finally {
            try {
                long elapseTime = System.currentTimeMillis() - startTime;
                Method method = invocation.getMethod();

                StringBuilder simpleMethodName = new StringBuilder(method.getDeclaringClass().getSimpleName());
                simpleMethodName.append(".");
                simpleMethodName.append(method.getName());

                Object[] args = invocation.getArguments();

                LogUtil.info(digestLogger, "[(", simpleMethodName.toString(), ",", elapseTime, "ms)]",
                        ",(", Arrays.toString(args), "),", "(",
                        invocationResult != null ? invocationResult.toString() : "", ")");
            } catch (Exception e) {
                LogUtil.warn(logger, e, "Fail to output digest log");
            }
        }
    }
}
