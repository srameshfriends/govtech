package com.govtech.project.utils;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

public class DoubleConverter implements AttributeConverter<Double, Object> {

    @Override
    public Object convertToDatabaseColumn(Double aDouble) {
        return aDouble;
    }

    @Override
    public Double convertToEntityAttribute(Object obj) {
        return obj == null ? 0D : (Double)obj;
    }
}
