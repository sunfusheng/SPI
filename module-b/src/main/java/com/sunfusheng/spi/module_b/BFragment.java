package com.sunfusheng.spi.module_b;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sunfusheng.spi.core.Provide;
import com.sunfusheng.spi.core.ServiceProvider;
import com.sunfusheng.spi.module.interfaces.AbsMainFragment;
import com.sunfusheng.spi.module.interfaces.IAModuleService;

/**
 * @author by sunfusheng on 2019/3/14
 */
@Provide(AbsMainFragment.class)
public class BFragment extends AbsMainFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_b, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        IAModuleService iaModuleService = ServiceProvider.getProvider(IAModuleService.class);
        if (iaModuleService != null) {
            TextView vInfo = getView().findViewById(R.id.vInfo);
            vInfo.setText(iaModuleService.getAModuleService());
        }
    }

    @Override
    public String tabName() {
        return "BFragment";
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
