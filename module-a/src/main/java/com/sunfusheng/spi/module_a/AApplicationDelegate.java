package com.sunfusheng.spi.module_a;

import android.app.Application;
import android.util.Log;

import com.sunfusheng.spi.core.Provide;
import com.sunfusheng.spi.module.interfaces.AbsApplicationDelegate;

/**
 * @author by sunfusheng on 2019/3/14
 */
@Provide(AbsApplicationDelegate.class)
public class AApplicationDelegate extends AbsApplicationDelegate {

    @Override
    public void onCreate(Application application) {
        Log.d("SPI", "AApplicationDelegate::onCreate()");
    }
}
