package com.sunfusheng.spi.module.interfaces;

import android.app.Application;

/**
 * @author by sunfusheng on 2019/3/14
 */
abstract public class AbsApplicationDelegate {

    abstract public void onCreate(Application application);

    public void onLowMemory() { }

    public void onTrimMemory(int level) { }

    public void onTerminate() { }
}
