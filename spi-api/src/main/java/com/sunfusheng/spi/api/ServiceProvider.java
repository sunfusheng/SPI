package com.sunfusheng.spi.api;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author sunfusheng on 2019/3/16.
 */
public class ServiceProvider {
    @SuppressLint("StaticFieldLeak")
    private static Context mContext;
    private static boolean isInitialized = false;

    public static synchronized void init(Application application) {
        mContext = application;
        register();
        isInitialized = true;
        Log.d("sfs", "ServiceProvider::init()");
    }

    public static synchronized void destroy() {
        ProvidersPool.providers.clear();
        isInitialized = false;
    }

    private static void register() {
//        SPI$$Provider$$com_sunfusheng_spi_module_a.register(ProvidersPool.registry);
//        SPI$$Provider$$com_sunfusheng_spi_module_b.register(ProvidersPool.registry);
    }

    @NonNull
    public static List<Class<?>> getProviders(Class clazz) {
        if (!isInitialized) {
            throw new RuntimeException("Please initialize first!");
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