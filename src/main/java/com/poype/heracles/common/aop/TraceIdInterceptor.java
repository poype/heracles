package com.poype.heracles.common.aop;

import com.poype.heracles.common.util.IdUtil;
import com.poype.heracles.common.util.ThreadLocalHolder;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

@Component("traceIdInterceptor")
public class TraceIdInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        ThreadLocalHolder.setTraceId(IdUtil.generate22Id());
        try {
            return invocation.proceed();
        } finally {
            ThreadLocalHolder.clear();
        }
    }
}
