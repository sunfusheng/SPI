package com.sunfusheng.spi.demo;

import com.sunfusheng.spi.api.ProvidersPool;
import com.sunfusheng.spi.providers.SPI$$Provider$$com_sunfusheng_spi_module_a;

/**
 * @author by sunfusheng on 2019/3/25
 */
public class SPIRegistry {

    private static void register() {
        SPI$$Provider$$com_sunfusheng_spi_module_a.register(ProvidersPool.registry);
    }

}
