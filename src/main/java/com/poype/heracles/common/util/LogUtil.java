package com.poype.heracles.common.util;

import org.slf4j.Logger;

public class LogUtil {


    private static final char THREAD_RIGHT_TAG = ']';

    private static final char THREAD_LEFT_TAG = '[';

    // disable the constructor
    private LogUtil() {}

    public static void debug(Logger logger, Object... obj) {
        if (logger.isDebugEnabled()) {
            logger.debug(getLogString(obj));
        }
    }

    public static void info(Logger logger, Object... obj) {
        if (logger.isInfoEnabled()) {
            logger.info(getLogString(obj));
        }
    }

    public static void warn(Logger logger, Object... obj) {
        logger.warn(getLogString(obj));
    }

    public static void warn(Logger logger, Throwable e, Object... obj) {
        logger.warn(getLogString(obj), e);
    }

    public static void error(Logger logger, Object... obj) {
        logger.error(getLogString(obj));
    }

    public static void error(Logger logger, Throwable e, Object... obj) {
        logger.error(getLogString(obj), e);
    }

    private static String getLogString(Object... obj) {
        StringBuilder log = new StringBuilder();
        log.append(THREAD_LEFT_TAG).append(fetchTraceId()).append(THREAD_RIGHT_TAG);
        for (Object o : obj) {
            log.append(o);
        }
        return log.toString();
    }

    private static String fetchTraceId() {
        return ThreadLocalHolder.getTraceId();
    }
}
