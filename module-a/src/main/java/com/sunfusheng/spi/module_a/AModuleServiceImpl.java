package com.sunfusheng.spi.module_a;

import com.sunfusheng.spi.core.Provide;
import com.sunfusheng.spi.module.interfaces.IAModuleService;

/**
 * @author by sunfusheng on 2019-04-25
 */
@Provide(IAModuleService.class)
public class AModuleServiceImpl implements IAModuleService {

    @Override
    public String getAModuleService() {
        return "来自 module-a 模块提供的服务";
    }
}
