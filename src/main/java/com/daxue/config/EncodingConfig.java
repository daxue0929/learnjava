package com.daxue.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.nio.charset.StandardCharsets;
import java.util.List;


public class EncodingConfig implements ApplicationContextAware, ApplicationListener<ContextRefreshedEvent> {

    private ApplicationContext app;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.app = applicationContext;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        try {
            RequestMappingHandlerAdapter requestMappingHandlerAdapter = app.getBean(RequestMappingHandlerAdapter.class);
            if (requestMappingHandlerAdapter != null) {
                List<HttpMessageConverter<?>> messageConverters = requestMappingHandlerAdapter.getMessageConverters();
                if (messageConverters != null) {
                    // 获取bean容器中的StringHttpMessageConverter, 并修改DefaultCharset
                    for (HttpMessageConverter item : messageConverters) {
                        ((StringHttpMessageConverter) item).setDefaultCharset(StandardCharsets.UTF_8);
                    }
                }
            }
        } catch (BeansException e) {
            e.printStackTrace();
        }

    }
}
