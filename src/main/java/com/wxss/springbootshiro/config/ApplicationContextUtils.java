package com.wxss.springbootshiro.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationContextUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
      ApplicationContextUtils.applicationContext = applicationContext;
    }

    /**
     * 根据名称获取Bean
     * @param beanName
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBeanOfName(String beanName,Class<T> clazz){
        return applicationContext.getBean(beanName, clazz);
    }
}
