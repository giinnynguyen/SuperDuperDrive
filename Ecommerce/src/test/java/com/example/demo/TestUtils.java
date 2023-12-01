package com.example.demo;

import java.lang.reflect.Field;

public class TestUtils {

    public static void injectObjects(Object target, String fieldName, Object toInject) {
        try {
            Field field = target.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(target, toInject);
        } catch (NoSuchFieldException e) {
            throw new IllegalArgumentException("Field '" + fieldName + "' not found in class " + target.getClass().getName());
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Error injecting object into field '" + fieldName + "' in class " + target.getClass().getName(), e);
        }
    }
}