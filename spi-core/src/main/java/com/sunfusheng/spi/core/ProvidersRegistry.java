package com.sunfusheng.spi.core;

/**
 * @author sunfusheng on 2019/3/16.
 */
interface ProvidersRegistry {
    void register(String key, String value);
}