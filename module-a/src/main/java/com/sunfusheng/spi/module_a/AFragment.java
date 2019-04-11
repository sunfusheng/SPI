package com.sunfusheng.spi.module_a;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.sunfusheng.spi.annotation.Provide;
import com.sunfusheng.spi.module.interfaces.IMainFragment;

/**
 * @author by sunfusheng on 2019/3/14
 */
@Provide(IMainFragment.class)
public class AFragment extends Fragment implements IMainFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Fragment getFragment() {
        return this;
    }

    @Override
    public String tabName() {
        return "AFragment";
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
