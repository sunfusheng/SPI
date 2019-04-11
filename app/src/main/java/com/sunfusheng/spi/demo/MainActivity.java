package com.sunfusheng.spi.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.sunfusheng.spi.api.ServiceProvider;
import com.sunfusheng.spi.module.interfaces.AbsApplicationDelegate;
import com.sunfusheng.spi.module.interfaces.IMainTab;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView vInfo = findViewById(R.id.vInfo);

        StringBuilder sb = new StringBuilder();
        List<AbsApplicationDelegate> applicationDelegates = ServiceProvider.getProviders(AbsApplicationDelegate.class);
        sb.append("AbsApplicationDelegate List: ");
        for (AbsApplicationDelegate delegate : applicationDelegates) {
            sb.append("\n").append(delegate);
        }

        List<IMainTab> mainTabs = ServiceProvider.getProviders(IMainTab.class);
        sb.append("\n\nIMainTab List: ");
        for (IMainTab tab : mainTabs) {
            sb.append("\n").append(tab);
        }

        vInfo.setText(sb);
    }

}
