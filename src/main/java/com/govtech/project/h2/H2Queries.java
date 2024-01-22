package com.govtech.project.h2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StringUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
@Slf4j
public class H2Queries {

    private final Properties properties;

    public H2Queries(ResourceLoader resourceLoader) {
        properties = readProperties(resourceLoader);
    }

    private static Properties readProperties(ResourceLoader loader) {
        /*Resource resource = loader.getResource("classpath:/query/h2-query.properties");*/
        Properties properties = new Properties();
        ClassPathResource classPathResource = new ClassPathResource("query/h2-query.properties");
        try (InputStream fileInputStream = classPathResource.getInputStream()) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }

    public String getNamedQuery(String name) {
        if(!StringUtils.hasText(name)) {
            debug("H2 named query parameter should not be null or empty.");
            throw new NullPointerException("H2 named query parameter should not be null or empty.");
        } else if(!properties.containsKey(name)) {
            debug("H2 named query not found (" + name + ")");
            throw new NullPointerException("H2 named query not found (" + name + ")");
        }
        return properties.getProperty(name);
    }

    public String getQuery(String name) {
        name = name + ".query";
        if(!properties.containsKey(name)) {
            debug("H2 query not found (" + name + ")");
            throw new NullPointerException("H2 query not found (" + name + ")");
        }
        return properties.getProperty(name);
    }

    public String getFields(String name) {
        name =  name + ".field";
        if(!properties.containsKey(name)) {
            debug("H2 field not found (" + name + ")");
            throw new NullPointerException("H2 field not found (" + name + ")");
        }
        return properties.getProperty(name);
    }

    private void debug(String logText) {
        if(log.isDebugEnabled()) {
            log.info(logText);
        }
    }
}
