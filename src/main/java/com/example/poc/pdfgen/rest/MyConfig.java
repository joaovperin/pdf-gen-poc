package com.example.poc.pdfgen.rest;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

import freemarker.template.Configuration;
import freemarker.template.Template;

@org.springframework.context.annotation.Configuration
public class MyConfig {

    @Bean
    Template freemarkerTemplate(
            @Qualifier("freemarkerConfiguration") Configuration freemarkerConfiguration) throws IOException {
        return freemarkerConfiguration.getTemplate("my-template.ftlh");
    }

    @Bean("freemarkerConfiguration")
    Configuration freemarkerConfiguration() throws IOException {
        final var cfg = new Configuration(Configuration.VERSION_2_3_32);
        cfg.setClassForTemplateLoading(this.getClass(), "/templates/");
        cfg.setDefaultEncoding("UTF-8");
        return cfg;
    }

}
