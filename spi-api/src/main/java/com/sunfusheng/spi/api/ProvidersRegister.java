package com.sunfusheng.spi.api;

/**
 * @author sunfusheng on 2019/3/16.
 */
@FunctionalInterface
public interface ProvidersRegister {
    void register(String key, Class<?> value);
}
