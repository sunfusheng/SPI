package com.sunfusheng.spi.module_b;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.sunfusheng.spi.core.Provide;
import com.sunfusheng.spi.core.ServiceProvider;
import com.sunfusheng.spi.module.interfaces.AbsMainFragment;
import com.sunfusheng.spi.module.interfaces.IAModuleService;

/**
 * @author by sunfusheng on 2019/3/14
 */
@Provide(AbsMainFragment.class)
public class BFragment extends AbsMainFragment {

    public BFragment() {
        IAModuleService iaModuleService = ServiceProvider.getProvider(IAModuleService.class);
        if (iaModuleService != null) {
            Log.d("sfs", "IAModuleService：" + iaModuleService.getAModuleService());
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected String tabName() {
        return "BFragment";
    }

    @Override
    protected int tabIcon() {
        return 0;
    }

    @Override
    protected boolean visible() {
        return true;
    }
}
