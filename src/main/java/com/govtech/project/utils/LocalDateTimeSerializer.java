package com.govtech.project.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

@JsonSerialize
public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> implements FormatConstant  {
    private final Logger log = LoggerFactory.getLogger(LocalDateTimeSerializer.class);

    @Override
    public void serialize(LocalDateTime value, JsonGenerator generator, SerializerProvider provider) {
        if (value != null) {
            try {
                generator.writeString(DATE_TIME_FORMATTER.format(value));
            } catch (Exception ex) {
                log.warn("LocalDateTimeSerializer: format invalid, " + ex.getMessage());
            }
        }
    }
}
