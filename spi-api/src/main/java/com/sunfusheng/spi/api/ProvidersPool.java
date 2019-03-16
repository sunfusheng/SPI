package com.sunfusheng.spi.api;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author by sunfusheng on 2019/3/14
 */
class ProvidersPool {
    static Map<String, Set<Class<?>>> providers = new HashMap<>();

    static ProvidersRegistry registry = (key, value) -> {
        if (!TextUtils.isEmpty(key) && value != null) {
            Set<Class<?>> classes = providers.get(key);
            if (classes == null) {
                classes = new HashSet<>();
                providers.put(key, classes);
            }
            classes.add(value);
        }
    };
}
