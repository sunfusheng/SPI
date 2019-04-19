package com.sunfusheng.spi.module_b;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.sunfusheng.spi.api.Provide;
import com.sunfusheng.spi.module.interfaces.AbsMainFragment;

/**
 * @author by sunfusheng on 2019/3/14
 */
@Provide(AbsMainFragment.class)
public class BFragment extends AbsMainFragment {

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
