package com.sunfusheng.spi.api;

import android.app.Application;
import android.content.Context;

import java.util.Set;

/**
 * @author sunfusheng on 2019/3/16.
 */
public class ServiceProvider {
    static Context mContext;

    public static synchronized void init(Application application) {
        mContext = application;
        register();
    }

    public static synchronized void destroy() {
        ProvidersPool.providers.clear();
    }

    private static synchronized void register() {
//        SPI$$Provider$$module_a.register(ProvidersPool.providerRegister);
//        SPI$$Provider$$module_b.register(ProvidersPool.providerRegister);
    }

    public static Set<Class<?>> getProviders(String key) {
        return ProvidersPool.providers.get(key);
    }
}
