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
    static final String TAG = "SPI";
    static boolean isInitialized = false;

    public static synchronized void init() {
        Log.d(TAG, "ServiceProvider::init()");
        register();
        isInitialized = true;
    }

    public static synchronized void destroy() {
        Log.d(TAG, "ServiceProvider::destroy()");
        ProvidersPool.providers.clear();
        isInitialized = false;
    }

    private static void register() {
    }

    @NonNull
    public static <T> List<Class<T>> getProviders(Class<T> clazz) {
        if (!isInitialized) {
            init();
        }

        List<Class<T>> clazzList = new ArrayList<>();
        Set<Class<?>> clazzSet = ProvidersPool.providers.get(clazz.getCanonicalName());
        if (clazzSet != null && clazzSet.size() > 0) {
            for (Class cls : clazzSet) {
                clazzList.add(cls);
            }
        }
        return clazzList;
    }
}