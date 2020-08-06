package com.poype.heracles.common.util;

import com.poype.heracles.common.enums.BusinessErrorCode;
import com.poype.heracles.common.exception.BusinessException;
import org.apache.commons.lang.StringUtils;

public class AssertUtil {

    public static void isTrue(final boolean expValue, final BusinessErrorCode resultCode, final Object... objs) {
        if (!expValue) {
            String logString = getLogString(objs);
            String resultMsg = StringUtils.isBlank(logString) ? resultCode.getResultMsg() : logString;
            BusinessException businessException = new BusinessException(resultMsg);
            businessException.setBusinessError(resultCode);
            businessException.setBizScene(ThreadLocalHolder.getBizScene());
            throw businessException;
        }
    }

    public static void isFalse(final boolean expValue, final BusinessErrorCode resultCode, final Object... objs) {
        isTrue(!expValue, resultCode, objs);
    }

    public static void is(final Object base, final Object target, final BusinessErrorCode resultCode,
                          final Object... objs) {
        isTrue(base == target, resultCode, objs);
    }

    public static void isNot(final Object base, final Object target, final BusinessErrorCode resultCode,
                             final Object... objs) {
        isTrue(base != target, resultCode, objs);
    }

    public static void isNull(final Object object, final BusinessErrorCode resultCode, final Object... objs) {
        isTrue(object == null, resultCode, objs);
    }

    public static void notNull(final Object object, final BusinessErrorCode resultCode, final Object... objs) {
        isTrue(object != null, resultCode, objs);
    }

    public static void in(final Object target, final Object[] array,
                          final BusinessErrorCode resultCode, final Object... objs) {
        notNull(array, resultCode, objs);

        boolean hasEqual = false;
        for (Object o : array) {
            if (target == o) {
                hasEqual = true;
                break;
            }
        }
        isTrue(hasEqual, resultCode, objs);
    }

    public static void notIn(final Object target, final Object[] array,
                             final BusinessErrorCode resultCode, final Object... objs) {
        if (null == array) {
            return;
        }
        for (Object o : array) {
            isTrue(target != o, resultCode, objs);
        }
    }

    public static void blank(final String str, final BusinessErrorCode resultCode, final Object... objs) {
        isTrue(StringUtils.isBlank(str), resultCode, objs);
    }

    public static void notBlank(final String str, final BusinessErrorCode resultCode, final Object... objs) {
        isTrue(StringUtils.isNotBlank(str), resultCode, objs);
    }

    private static String getLogString(Object... objs) {
        StringBuilder log = new StringBuilder();
        for (Object o : objs) {
            log.append(o);
        }
        return log.toString();
    }
}
