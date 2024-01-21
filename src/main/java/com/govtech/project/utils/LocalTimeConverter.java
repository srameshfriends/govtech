package com.govtech.project.utils;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Optional;

@Converter(autoApply = true)
public class LocalTimeConverter implements AttributeConverter<LocalTime, Time> {

    @Override
    public Time convertToDatabaseColumn(LocalTime dateTime) {
        return Optional.ofNullable(dateTime)
                .map(Time::valueOf)
                .orElse(null);
    }

    @Override
    public LocalTime convertToEntityAttribute(Time date) {
        return Optional.ofNullable(date)
                .map(Time::toLocalTime)
                .orElse(null);
    }
}
