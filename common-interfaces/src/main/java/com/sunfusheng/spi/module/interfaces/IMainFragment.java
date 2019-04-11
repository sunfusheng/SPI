package com.sunfusheng.spi.module.interfaces;

import android.support.v4.app.Fragment;

/**
 * @author by sunfusheng on 2019/3/14
 */
public interface IMainFragment {

    Fragment getFragment();

    String tabName();

    int tabIcon();

    boolean visible();
}
