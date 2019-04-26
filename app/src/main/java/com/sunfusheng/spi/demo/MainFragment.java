package com.sunfusheng.spi.demo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sunfusheng.spi.core.Provide;
import com.sunfusheng.spi.core.ServiceProvider;
import com.sunfusheng.spi.module.interfaces.AbsApplicationDelegate;
import com.sunfusheng.spi.module.interfaces.AbsMainFragment;

import java.util.List;

/**
 * @author by sunfusheng on 2019/4/11
 */
@Provide(AbsMainFragment.class)
public class MainFragment extends AbsMainFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        StringBuilder sb = new StringBuilder();
        List<AbsApplicationDelegate> applicationDelegates = ServiceProvider.getProviders(AbsApplicationDelegate.class);
        sb.append("【AbsApplicationDelegate Providers】: ");
        for (AbsApplicationDelegate delegate : applicationDelegates) {
            sb.append("\n").append(delegate.getClass().getSimpleName());
        }

        TextView vInfo = getView().findViewById(R.id.vInfo);
        vInfo.setText(sb);
    }

    @Override
    public String tabName() {
        return "MainFragment";
    }

    @Override
    public int tabIcon() {
        return 0;
    }

    @Override
    public boolean visible() {
        return true;
    }
}
