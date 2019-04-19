package com.sunfusheng.spi.core;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author by sunfusheng on 2019/3/14
 */
class ProvidersPool {
    static Map<String, Set<String>> mProviders = new HashMap<>();
    static Map<String, Set<Object>> mProvidersCache = new HashMap<>();

    static ProvidersRegistry registry = new ProvidersRegistry() {
        @Override
        public void register(String key, String value) {
            if (!TextUtils.isEmpty(key) && !TextUtils.isEmpty(value)) {
                Set<String> set = mProviders.get(key);
                if (set == null) {
                    set = new HashSet<>();
                    mProviders.put(key, set);
                }
                set.add(value);
            }
        }
    };
}