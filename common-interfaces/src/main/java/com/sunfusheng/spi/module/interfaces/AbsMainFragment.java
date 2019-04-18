package com.sunfusheng.spi.module.interfaces;

import android.support.v4.app.Fragment;

/**
 * @author by sunfusheng on 2019/3/14
 */
abstract public class AbsMainFragment extends Fragment {

    protected String tabName() {
        return "Default Tab Name";
    }

    protected int tabIcon() {
        return 0;
    }

    protected boolean visible() {
        return true;
    }
}
