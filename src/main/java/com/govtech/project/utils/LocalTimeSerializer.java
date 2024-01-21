package com.govtech.project.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.LocalTime;

@JsonSerialize
public class LocalTimeSerializer extends JsonSerializer<LocalTime> implements FormatConstant  {
    private final Logger log = LoggerFactory.getLogger(LocalTimeSerializer.class);

    @Override
    public void serialize(LocalTime value, JsonGenerator generator, SerializerProvider provider) {
        if (value != null) {
            try {
                generator.writeString(TIME_FORMATTER.format(value));
            } catch (Exception ex) {
                log.warn("LocalTimeSerializer: format invalid, " + ex.getMessage());
            }
        }
    }
}
