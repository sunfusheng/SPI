package com.sunfusheng.spi.module.interfaces;

import android.support.v4.app.Fragment;

/**
 * @author by sunfusheng on 2019/3/14
 */
abstract public class AbsMainFragment extends Fragment {

    public String tabName() {
        return "Default Tab Name";
    }

    public int tabIcon() {
        return 0;
    }

    public boolean visible() {
        return true;
    }
}
