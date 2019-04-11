package com.sunfusheng.spi.module_a;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.sunfusheng.spi.annotation.Provide;
import com.sunfusheng.spi.module.interfaces.IMainTab;

/**
 * @author by sunfusheng on 2019/3/14
 */
@Provide(IMainTab.class)
public class AFragment extends Fragment implements IMainTab {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public String tabName() {
        return "AFragment";
    }
}
