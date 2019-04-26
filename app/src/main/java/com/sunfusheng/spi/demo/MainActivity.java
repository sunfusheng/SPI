package com.sunfusheng.spi.demo;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.sunfusheng.spi.core.ServiceProvider;
import com.sunfusheng.spi.demo.adapter.FragmentPagerItemAdapter;
import com.sunfusheng.spi.module.interfaces.AbsMainFragment;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("SPI （V" + getVersionName(this) + "）");
        loadFragments();
    }

    private void loadFragments() {
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager viewPager = findViewById(R.id.viewPager);

        FragmentPagerItemAdapter.Builder builder = new FragmentPagerItemAdapter.Builder(this, getSupportFragmentManager());
        List<AbsMainFragment> fragments = ServiceProvider.getProviders(AbsMainFragment.class);
        for (AbsMainFragment fragment : fragments) {
            builder.add(fragment.tabName(), fragment);
        }
        viewPager.setAdapter(builder.build());
        tabLayout.setupWithViewPager(viewPager);
    }

    public static String getVersionName(Context context) {
        try {
            PackageManager packageManager = context.getApplicationContext().getPackageManager();
            PackageInfo pi = packageManager.getPackageInfo(context.getApplicationContext().getPackageName(), 0);
            return pi == null ? "V1.4.0" : pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
