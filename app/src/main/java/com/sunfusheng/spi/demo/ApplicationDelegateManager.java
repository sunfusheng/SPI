package com.sunfusheng.spi.demo;

import android.app.Application;

import com.sunfusheng.spi.core.ServiceProvider;
import com.sunfusheng.spi.module.interfaces.AbsApplicationDelegate;

import java.util.List;

/**
 * @author by sunfusheng on 2019/4/11
 */
public class ApplicationDelegateManager {
    private static final ApplicationDelegateManager mDelegatesManager = new ApplicationDelegateManager();
    private List<AbsApplicationDelegate> mDelegates;

    private ApplicationDelegateManager() {
        mDelegates = ServiceProvider.getProviders(AbsApplicationDelegate.class);
    }

    public static ApplicationDelegateManager getInstant() {
        return mDelegatesManager;
    }

    public void onCreate(Application application) {
        for (AbsApplicationDelegate delegate : mDelegates) {
            delegate.onCreate(application);
        }
    }

    public void onLowMemory() {
        for (AbsApplicationDelegate delegate : mDelegates) {
            delegate.onLowMemory();
        }
    }

    public void onTrimMemory(int level) {
        for (AbsApplicationDelegate delegate : mDelegates) {
            delegate.onTrimMemory(level);
        }
    }

    public void onTerminate() {
        for (AbsApplicationDelegate delegate : mDelegates) {
            delegate.onTerminate();
        }
    }
}
