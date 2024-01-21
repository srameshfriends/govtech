package com.govtech.project.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@JsonDeserialize
public class LocalDateDeserializer extends JsonDeserializer<LocalDate> implements HttpMessageConverter {
    private final Logger log = LoggerFactory.getLogger(LocalDateDeserializer.class);


    @Override
    public boolean canRead(Class clazz, MediaType mediaType) {
        return false;
    }

    @Override
    public boolean canWrite(Class clazz, MediaType mediaType) {
        return false;
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return null;
    }

    @Override
    public Object read(Class clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    public void write(Object o, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

    }

    @Override
    public LocalDate deserialize(JsonParser jp, DeserializationContext context) throws IOException {
        String text = jp.getText();
        if (text != null) {
            try {
                return LocalDate.parse(text, FormatConstant.DATE_FORMATTER);
            } catch (DateTimeParseException ex) {
                log.warn("LocalDateDeserializer: format invalid, " + ex.getMessage());
            }
        }
        return null;
    }
}
