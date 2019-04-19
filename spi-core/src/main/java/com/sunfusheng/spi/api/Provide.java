package com.sunfusheng.spi.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author by sunfusheng on 2019/3/5
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.CLASS)
public @interface Provide {
    Class<?> value();
}
