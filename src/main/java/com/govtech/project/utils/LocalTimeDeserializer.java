package com.govtech.project.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalTime;

@JsonDeserialize
public class LocalTimeDeserializer extends JsonDeserializer<LocalTime> implements FormatConstant {

    private static final Logger log = LoggerFactory.getLogger(LocalTimeDeserializer.class);

    @Override
    public LocalTime deserialize(JsonParser jp, DeserializationContext context) throws IOException {
        return asLocalTime(jp.getText());
    }

    public static LocalTime asLocalTime(String text) {
        if (text != null) {
            try {
                return LocalTime.parse(text, TIME_FORMATTER);
            } catch (Exception ex) {
                log.warn("LocalTimeDeserializer: format invalid, " + ex.getMessage());
            }
        }
        return null;
    }
}
