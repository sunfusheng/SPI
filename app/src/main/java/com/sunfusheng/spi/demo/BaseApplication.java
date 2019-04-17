package com.sunfusheng.spi.demo;

import android.app.Application;

import com.sunfusheng.spi.api.ServiceProvider;

/**
 * @author by sunfusheng on 2019/3/14
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ServiceProvider.init();
        ApplicationDelegateManager.getInstant().onCreate(this);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        ApplicationDelegateManager.getInstant().onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        ApplicationDelegateManager.getInstant().onTrimMemory(level);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ApplicationDelegateManager.getInstant().onTerminate();
        ServiceProvider.destroy();
    }
}
