package com.poype.heracles.common.util;

import java.util.UUID;

public class IdUtil {

    public static String generate22Id() {
        return UUID.randomUUID().toString().replace("-", "").toLowerCase().substring(10);
    }
}
