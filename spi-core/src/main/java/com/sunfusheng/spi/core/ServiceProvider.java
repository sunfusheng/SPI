package com.sunfusheng.spi.core;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author sunfusheng on 2019/3/16.
 */
public class ServiceProvider {
    private static final String TAG = "SPI";
    private static boolean isInitialized = false;

    public static synchronized void init() {
        Log.d(TAG, "ServiceProvider::init()");
        if (!isInitialized) {
            isInitialized = true;
            register();
        }
    }

    private static synchronized void register() {
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public static <T> List<T> getProviders(Class<T> clazz) {
        if (!isInitialized) {
            init();
        }

        List<T> providers = new ArrayList<>();
        if (clazz == null) {
            return providers;
        }

        String classCanonicalName = clazz.getCanonicalName();
        if (classCanonicalName == null) {
            return providers;
        }

        Set<Object> providersSet = ProvidersPool.mProvidersCache.get(classCanonicalName);
        if (providersSet == null) {
            providersSet = new HashSet<>();
            Set<String> classNameSet = ProvidersPool.mProviders.get(classCanonicalName);
            if (classNameSet == null) {
                classNameSet = new HashSet<>();
            }

            if (classNameSet.size() > 0) {
                for (String className : classNameSet) {
                    try {
                        Class<?> cls = Class.forName(className);
                        providersSet.add(cls.getConstructor().newInstance());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            ProvidersPool.mProvidersCache.put(classCanonicalName, providersSet);
        }

        if (providersSet.size() > 0) {
            for (Object object : providersSet) {
                providers.add((T) object);
            }
        }
        return providers;
    }

    public static <T> T getProvider(Class<T> clazz) {
        List<T> providers = getProviders(clazz);
        if (providers.size() > 0) {
            return providers.get(0);
        }
        return null;
    }

    public static synchronized void destroy() {
        Log.d(TAG, "ServiceProvider::destroy()");
        if (isInitialized) {
            isInitialized = false;
            ProvidersPool.mProviders.clear();
            ProvidersPool.mProvidersCache.clear();
        }
    }
}