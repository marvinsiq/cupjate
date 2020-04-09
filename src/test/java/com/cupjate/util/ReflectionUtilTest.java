package com.cupjate.util;

import java.lang.reflect.Field;

import org.junit.Test;

import com.cupjate.aux.Item;

import junit.framework.Assert;

public class ReflectionUtilTest {

	@Test
	public void testGetField() throws NoSuchFieldException {

		Item item = new Item();
		item.setName("Monitor");

		Assert.assertNotNull(ReflectionUtil.getField(item, "name"));
	}

	@Test(expected=NoSuchFieldException.class)
	public void testGetFieldNoSuchFieldException() throws NoSuchFieldException {

		Item item = new Item();
		item.setName("Monitor");

		Assert.assertNotNull(ReflectionUtil.getField(item, "field"));
	}

	@Test
	public void testGetFieldValueObjectField() throws NoSuchFieldException, IllegalAccessException {

		String expected = "Monitor";

		Item item = new Item();
		item.setName(expected);

		Field field = ReflectionUtil.getField(item, "name");

		Assert.assertEquals(expected, ReflectionUtil.getFieldValue(item, field));
	}

	@Test
	public void testGetFieldValueObjectString() throws IllegalAccessException, NoSuchFieldException {

		String expected = "Monitor";

		Item item = new Item();
		item.setName(expected);

		Assert.assertEquals(expected, ReflectionUtil.getFieldValue(item, "name"));

	}

	@Test
	public void testGetFieldValueIfExists() {

		String expected = "Monitor";

		Item item = new Item();
		item.setName(expected);

		Assert.assertEquals(expected, ReflectionUtil.getFieldValueIfExists(item, "name"));
		Assert.assertNull(expected, ReflectionUtil.getFieldValueIfExists(item, "field"));
	}

}
