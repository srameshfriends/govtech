package com.govtech.project.utils;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

public class IntConverter implements AttributeConverter<Integer, Object> {

    @Override
    public Object convertToDatabaseColumn(Integer aNumber) {
        return aNumber;
    }

    @Override
    public Integer convertToEntityAttribute(Object obj) {
        return obj == null ? 0 : (Integer)obj;
    }
}
