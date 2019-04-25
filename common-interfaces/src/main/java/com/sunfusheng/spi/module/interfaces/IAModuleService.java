package com.sunfusheng.spi.module.interfaces;

/**
 * @author by sunfusheng on 2019-04-25
 */
public interface IAModuleService {
    // 在 module-a 中提供服务，module-b 中使用
    String getAModuleService();
}
