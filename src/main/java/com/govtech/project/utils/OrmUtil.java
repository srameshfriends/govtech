package com.govtech.project.utils;

import com.govtech.project.entity.*;
import jakarta.persistence.AttributeConverter;
import lombok.Getter;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public  class OrmUtil {

    private static final Map<Class<?>, Map<String, FieldClassSet>> reflectionMap = new HashMap<>();
    private static final Map<Class<?>, AttributeConverter<?, ?>> converterMap = createConverterMap();

    private static final Set<Class<?>> entitySet = createEntitySet();

    public static <T> T as(Class<T> typeClass, String metadata, Object[] values) {
        List<String> list = Arrays.asList(metadata.split(","));
        String[] array = new String[list.size()];
        for (int idx = 0; idx < list.size(); idx++) {
            array[idx] = list.get(idx).trim();
        }
        return as(typeClass, array, values);
    }

    @SuppressWarnings("unchecked")
    public static <T> T as(Class<T> typeClass, String[] metadata, Object[] values) {
        if(values == null || 0 == values.length) {
            return null;
        }
        if(!reflectionMap.containsKey(typeClass)) {
            Map<String, FieldClassSet> map = new HashMap<>();
            readReflectionMap(map, typeClass);
            reflectionMap.put(typeClass, map);
        }
        Map<String, FieldClassSet> fieldMap = reflectionMap.get(typeClass);
        if(fieldMap == null) {
            throw new NullPointerException("Reflection field map should not be null.");
        }
        Object entity = null;
        try {
            entity = typeClass.getDeclaredConstructor().newInstance();
            for(int idx = 0; idx < metadata.length; idx++) {
                String fieldName = metadata[idx];
                Object value = null;
                if(idx < values.length) {
                    value = values[idx];
                }
                if(!fieldMap.containsKey(fieldName) || value == null) {
                    continue;
                }
                FieldClassSet classSet = fieldMap.get(fieldName);
                if(converterMap.containsKey(classSet.getType())) {
                    AttributeConverter<Object, Object> converter = (AttributeConverter<Object, Object>)converterMap.get(classSet.getType());
                    fieldMap.get(fieldName).getField().set(entity, converter.convertToEntityAttribute(value));
                } else if(entitySet.contains(classSet.getType())) {
                    BaseEntity baseEntity = BaseEntity.class.getDeclaredConstructor().newInstance();
                    baseEntity.setId(new LongConverter().convertToEntityAttribute(value));
                } else {
                    fieldMap.get(fieldName).getField().set(entity, value);
                }
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return (T)entity;
    }

    private static void readReflectionMap(Map<String, FieldClassSet> fieldNameMap, Class<?> typeClass) {
        Field[] fields = typeClass.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            fieldNameMap.put(field.getName(), new FieldClassSet(field, field.getType()));
        }
        Class<?> superClass = typeClass.getSuperclass();
        if(superClass != null) {
            readReflectionMap(fieldNameMap, superClass);
        }
    }

    private static Map<Class<?>, AttributeConverter<?, ?>> createConverterMap() {
        Map<Class<?>, AttributeConverter<?, ?>> map = new HashMap<>();
        map.put(LocalTime.class, new LocalTimeConverter());
        map.put(LocalDate.class, new LocalDateConverter());
        map.put(LocalDateTime.class, new LocalDateTimeConverter());
        map.put(Long.class, new LongConverter());
        map.put(Double.class, new DoubleConverter());
        map.put(Integer.class, new IntConverter());
        return map;
    }

    private static Set<Class<?>> createEntitySet() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(UserInfo.class);
        classes.add(GatheringEvent.class);
        classes.add(Restaurant.class);
        classes.add(EventParticipant.class);
        return classes;
    }

    @Getter
    static class FieldClassSet {
        final Field field;
        final Class<?> type;

        public FieldClassSet(Field field, Class<?> type) {
            this.field = field;
            this.type = type;
        }
    }
}
