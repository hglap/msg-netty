package com.ebanma.cloud.msg.service.netty.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author 黄贵龙
 * @version $ Id: AppContextHolder, v 0.1 2023/02/17 8:39 banma- Exp $
 */
@Component
public class AppContextHolder implements ApplicationContextAware {

    private static ApplicationContext appContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        appContext = applicationContext;
    }

    public static ApplicationContext getAppContext(){
        return appContext;
    }
}
