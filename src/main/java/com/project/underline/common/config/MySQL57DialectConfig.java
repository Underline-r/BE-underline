package com.project.underline.common.config;

import org.hibernate.dialect.MySQL57Dialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.StandardBasicTypes;

public class MySQL57DialectConfig extends MySQL57Dialect {
    public MySQL57DialectConfig() {
        super();
        // register custom/inner function here
        this.registerFunction("group_concat", new SQLFunctionTemplate(StandardBasicTypes.STRING, "group_concat(?1)"));
    }
}