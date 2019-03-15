package com.sunfusheng.spi.api;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author by sunfusheng on 2019/3/14
 */
public class ProvidersPool {
    private static Map<String, Set<Class<?>>> providers = new HashMap<>();

    private static void registerProvider(String key, Class<?> value) {
        if (!TextUtils.isEmpty(key) && value != null) {
            Set<Class<?>> classes = providers.get(key);
            if (classes == null) {
                classes = new HashSet<>();
                providers.put(key, classes);
            }
            classes.add(value);
        }
    }

    public static Set<Class<?>> getProviders(String key) {
        return providers.get(key);
    }

    public static void destroy() {
        providers.clear();
    }
}
