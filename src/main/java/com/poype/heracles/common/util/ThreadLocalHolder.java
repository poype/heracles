package com.poype.heracles.common.util;

import com.poype.heracles.common.dto.error.BizScene;

/**
 * 对多个ThreadLocal的一个封装，因为一个ThreadLocal对象只能为线程承载一个变量，所以ThreadLocalHolder可能会封装多个ThreadLocal
 */
public final class ThreadLocalHolder {

    private static ThreadLocal<BizScene> bizSceneHolder = new ThreadLocal<>();

    private static ThreadLocal<String> traceIdHolder = new ThreadLocal<>();

    /**
     * 禁止实例化。
     */
    private ThreadLocalHolder() {}

    public static BizScene getBizScene() {
        return bizSceneHolder.get();
    }

    public static void setBizScene(BizScene bizScene) {
        bizSceneHolder.set(bizScene);
    }

    public static String getTraceId() {
        return traceIdHolder.get();
    }

    public static void setTraceId(String traceId) {
        traceIdHolder.set(traceId);
    }

    public static void clear() {
        bizSceneHolder.remove();
        traceIdHolder.remove();
        // 如果同时管理多个ThreadLocal对象，要保证调用所有ThreadLocal的remove方法
    }
}
