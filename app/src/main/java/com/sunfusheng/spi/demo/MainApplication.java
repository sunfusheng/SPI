package com.sunfusheng.spi.demo;

import android.app.Application;

import com.sunfusheng.spi.api.ServiceProvider;

/**
 * @author by sunfusheng on 2019/3/14
 */
public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ServiceProvider.init();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ServiceProvider.destroy();
    }
}
