package com.sunfusheng.spi.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.sunfusheng.spi.api.ServiceProvider;
import com.sunfusheng.spi.module.interfaces.AbsApplicationDelegate;
import com.sunfusheng.spi.module.interfaces.IMainFragment;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Class<?>> absApplicationDelegateProviders = ServiceProvider.getProviders(AbsApplicationDelegate.class);
        Log.d("sfs", "absApplicationDelegateProviders: " + absApplicationDelegateProviders);

        List<Class<?>> iMainFragmentProviders = ServiceProvider.getProviders(IMainFragment.class);
        Log.d("sfs", "iMainFragmentProviders: " + iMainFragmentProviders);

    }

}
