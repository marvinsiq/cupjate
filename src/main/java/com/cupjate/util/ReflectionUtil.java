package com.cupjate.util;

import java.lang.reflect.Field;

public class ReflectionUtil {

	private ReflectionUtil() {
		// Util Class
	}

	public static Field getField(Object object, String fieldName) throws NoSuchFieldException {
		return object.getClass().getDeclaredField(fieldName);
	}

	public static Object getFieldValue(Object obj, Field field) throws IllegalAccessException {
		field.setAccessible(true);
		return field.get(obj);
	}

	public static Object getFieldValue(Object obj, String fieldName)
			throws IllegalAccessException, NoSuchFieldException {
		Field field = getField(obj, fieldName);
		return getFieldValue(obj, field);
	}

	public static Object getFieldValueIfExists(Object obj, String fieldName) {
		try {
			return getFieldValue(obj, fieldName);
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			return null;
		}
	}
}
