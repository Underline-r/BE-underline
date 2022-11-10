package com.project.underline.common.config;

import com.p6spy.engine.spy.P6SpyOptions;
import com.project.underline.common.util.P6spyCustomSqlFormatter;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class P6spyConfig {
    @PostConstruct
    public void setLogMessageFormat() {
        P6SpyOptions.getActiveInstance().setLogMessageFormat(P6spyCustomSqlFormatter.class.getName());
    }
}