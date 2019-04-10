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
    private static boolean isInitialized = false;

    public static synchronized void init() {
        Log.d(TAG, "ServiceProvider::init()");
        register();
        isInitialized = true;
    }

    public static synchronized void destroy() {
        ProvidersPool.providers.clear();
        isInitialized = false;
    }

    private static void register() {
    }

    @NonNull
    public static List<Class<?>> getProviders(Class clazz) {
        if (!isInitialized) {
            init();
        }

        Set<Class<?>> classSet = ProvidersPool.providers.get(clazz.getCanonicalName());
        List<Class<?>> classList;
        if (classSet != null && classSet.size() > 0) {
            classList = new ArrayList<>(classSet);
        } else {
            classList = new ArrayList<>();
        }
        return classList;
    }
}