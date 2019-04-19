package com.sunfusheng.spi.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.sunfusheng.spi.core.Provide;
import com.sunfusheng.spi.module.interfaces.AbsMainFragment;

/**
 * @author by sunfusheng on 2019/4/11
 */
@Provide(AbsMainFragment.class)
public class MainFragment extends AbsMainFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected String tabName() {
        return "MainFragment";
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
