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

    static ProvidersRegistry registry = new ProvidersRegistry() {
        @Override
        public void register(String key, String value) {
            if (TextUtils.isEmpty(key) || TextUtils.isEmpty(value)) {
                return;
            }
            try {
                Class<?> clazz = Class.forName(value);
                Set<Class<?>> classes = providers.get(key);
                if (classes == null) {
                    classes = new HashSet<>();
                    providers.put(key, classes);
                }
                classes.add(clazz);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    };
}
