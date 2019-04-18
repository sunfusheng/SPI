package com.sunfusheng.spi.module_a;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.sunfusheng.spi.annotation.Provide;
import com.sunfusheng.spi.module.interfaces.AbsMainFragment;

/**
 * @author by sunfusheng on 2019/3/14
 */
@Provide(AbsMainFragment.class)
public class AFragment extends AbsMainFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected String tabName() {
        return "AFragment";
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
