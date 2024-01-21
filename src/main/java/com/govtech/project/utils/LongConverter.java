package com.govtech.project.utils;

import jakarta.persistence.AttributeConverter;

public class LongConverter implements AttributeConverter<Long, Object> {

    public LongConverter() {
    }

    @Override
    public Object convertToDatabaseColumn(Long aLong) {
        return aLong;
    }

    @Override
    public Long convertToEntityAttribute(Object obj) {
        return obj == null ? 0L : (Long)obj;
    }
}
