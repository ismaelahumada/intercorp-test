package com.intercorp.challenge.bean;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class SpringBeanConfig {
    @Bean
    public DozerBeanMapper dozerBeanMapper() {
        DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
        dozerBeanMapper.setMappingFiles(Collections.singletonList("dozerJdk8Converters.xml"));
        return dozerBeanMapper;
    }
}
