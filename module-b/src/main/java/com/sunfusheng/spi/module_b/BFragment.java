package com.sunfusheng.spi.module_b;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.sunfusheng.spi.annotation.Provide;
import com.sunfusheng.spi.module.interfaces.IMainFragment;

/**
 * @author by sunfusheng on 2019/3/14
 */
@Provide(IMainFragment.class)
public class BFragment extends Fragment implements IMainFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public String tabName() {
        return "BFragment";
    }
}