package com.sunfusheng.spi.api;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
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
        register();
        isInitialized = true;
    }

    private static void register() {
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public static <T> List<T> getProviders(Class<T> clazz) {
        if (!isInitialized) {
            init();
        }

        List<T> objects = new ArrayList<>();
        Set<Object> clazzSet = ProvidersPool.providers.get(clazz.getCanonicalName());
        if (clazzSet != null && clazzSet.size() > 0) {
            for (Object object : clazzSet) {
                objects.add((T) object);
            }
        }
        return objects;
    }

    public static synchronized void destroy() {
        Log.d(TAG, "ServiceProvider::destroy()");
        ProvidersPool.providers.clear();
        isInitialized = false;
    }
}