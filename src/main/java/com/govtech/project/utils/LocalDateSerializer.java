package com.govtech.project.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

@JsonSerialize
public class LocalDateSerializer extends JsonSerializer<LocalDate> implements FormatConstant {
    private static final Logger log = LoggerFactory.getLogger(LocalDateSerializer.class);

    @Override
    public void serialize(LocalDate value, JsonGenerator generator, SerializerProvider provider) {
        if (value != null) {
            try {
                generator.writeString(DATE_FORMATTER.format(value));
            } catch (Exception ex) {
                log.warn("LocalDateSerializer: serialize, " + ex.getMessage());
            }
        }
    }
}
