package com.sunfusheng.spi.module_b;

import android.app.Application;
import android.util.Log;

import com.sunfusheng.spi.annotation.Provide;
import com.sunfusheng.spi.module.interfaces.AbsApplicationDelegate;

/**
 * @author by sunfusheng on 2019/3/14
 */
@Provide(AbsApplicationDelegate.class)
public class BApplicationDelegate extends AbsApplicationDelegate {

    private static final String TAG = "SPI";

    @Override
    public void onCreate(Application application) {
        Log.d(TAG, "BApplicationDelegate::onCreate()");
    }
}
