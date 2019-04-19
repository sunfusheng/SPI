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
    static Map<String, Set<Object>> providers = new HashMap<>();

    static ProvidersRegistry registry = new ProvidersRegistry() {
        @Override
        public void register(String key, String value) {
            if (TextUtils.isEmpty(key) || TextUtils.isEmpty(value)) {
                return;
            }

            try {
                Class<?> clazz = Class.forName(value);
                Object object = clazz.getConstructor().newInstance();
                Set<Object> objectSet = providers.get(key);
                if (objectSet == null) {
                    objectSet = new HashSet<>();
                    providers.put(key, objectSet);
                }
                objectSet.add(object);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
}