package com.govtech.project.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.IOException;
import java.time.LocalDateTime;

@JsonDeserialize
public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> implements FormatConstant {

    @Override
    public LocalDateTime deserialize(JsonParser jp, DeserializationContext context) throws IOException {
        String text = jp.getText();
        if (text != null) {
            return LocalDateTime.parse(text, DATE_TIME_FORMATTER);
        }
        return null;
    }
}
