package com.sunfusheng.spi.demo;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.sunfusheng.spi.core.ServiceProvider;
import com.sunfusheng.spi.module.interfaces.AbsApplicationDelegate;
import com.sunfusheng.spi.module.interfaces.AbsMainFragment;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("SPI （V" + getVersionName(this) + "）");

        TextView vInfo = findViewById(R.id.vInfo);

        StringBuilder sb = new StringBuilder();
        List<AbsApplicationDelegate> applicationDelegates = ServiceProvider.getProviders(AbsApplicationDelegate.class);
        sb.append("【AbsApplicationDelegate Providers】: ");
        for (AbsApplicationDelegate delegate : applicationDelegates) {
            sb.append("\n").append(delegate.getClass().getSimpleName());
        }

        List<AbsMainFragment> mainFragments = ServiceProvider.getProviders(AbsMainFragment.class);
        sb.append("\n\n【AbsMainFragment Providers】: ");
        for (AbsMainFragment fragment : mainFragments) {
            sb.append("\n").append(fragment.getClass().getSimpleName());
        }

        vInfo.setText(sb);
    }

    public static String getVersionName(Context context) {
        try {
            PackageManager packageManager = context.getApplicationContext().getPackageManager();
            PackageInfo pi = packageManager.getPackageInfo(context.getApplicationContext().getPackageName(), 0);
            return pi == null ? "V1.2.0" : pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
