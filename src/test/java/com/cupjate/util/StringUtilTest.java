package com.cupjate.util;

import org.junit.Test;

import com.cupjate.util.StringUtil;

import junit.framework.Assert;

public class StringUtilTest {

	@Test
	public void testRemoveAndCapitalizeAfter() {		
		Assert.assertEquals("loremIpsumDolor", StringUtil.removeAndCapitalizeAfter(" ", "lorem ipsum dolor"));
		Assert.assertEquals("loremIpsumDolor", StringUtil.removeAndCapitalizeAfter("-", "lorem-ipsum-dolor"));
		Assert.assertEquals("loremIpsumDolor", StringUtil.removeAndCapitalizeAfter("_", "lorem_ipsum_dolor"));
		
		Assert.assertEquals("loremIpsumDolor", StringUtil.removeAndCapitalizeAfter("_", "lorem_ipsum_dolor_"));
	}

	@Test
	public void testRemoveDuplicateWhitespace() {
		Assert.assertEquals("Lorem ipsum dolor", StringUtil.removeDuplicateWhitespace("Lorem     ipsum  dolor"));
		Assert.assertEquals("Lorem ipsum dolor", StringUtil.removeDuplicateWhitespace("Lorem ipsum         dolor"));
		Assert.assertEquals(" Lorem ipsum dolor ", StringUtil.removeDuplicateWhitespace("   Lorem ipsum         dolor "));
	}

	@Test
	public void testRemoveSeparateCharAndCapitalizeAfter() {
		Assert.assertEquals("loremIpsumDolor", StringUtil.removeSeparateCharAndCapitalizeAfter("lorem ipsum dolor"));
		Assert.assertEquals("loremIpsumDolor", StringUtil.removeSeparateCharAndCapitalizeAfter("lorem-ipsum-dolor"));
		Assert.assertEquals("loremIpsumDolor", StringUtil.removeSeparateCharAndCapitalizeAfter("lorem_ipsum_dolor"));
	}

	@Test
	public void testCamelCase() {
		Assert.assertEquals("loremIpsumDolor", StringUtil.camelCase("Lorem ipsum dolor"));
		Assert.assertEquals("loremIpsumDolor", StringUtil.camelCase("Lorem   ipsum    dolor"));
		Assert.assertEquals("loremIpsumDolor", StringUtil.camelCase("Lorem Ipsum Dolor"));
		Assert.assertEquals("loremIpsumDolor", StringUtil.camelCase("lorem-ipsum-dolor"));
		Assert.assertEquals("loremIpsumDolor", StringUtil.camelCase("lorem_ipsum_dolor"));
	}

	@Test
	public void testPascalCase() {
		Assert.assertEquals("LoremIpsumDolor", StringUtil.pascalCase("Lorem ipsum dolor"));
		Assert.assertEquals("LoremIpsumDolor", StringUtil.pascalCase("Lorem   ipsum    dolor"));
		Assert.assertEquals("LoremIpsumDolor", StringUtil.pascalCase("Lorem Ipsum Dolor"));
		Assert.assertEquals("LoremIpsumDolor", StringUtil.pascalCase("lorem-ipsum-dolor"));
		Assert.assertEquals("LoremIpsumDolor", StringUtil.pascalCase("lorem_ipsum_dolor"));
	}

	@Test
	public void testSnakeCase() {
		Assert.assertEquals("lorem_ipsum_dolor", StringUtil.snakeCase("Lorem ipsum dolor"));
		Assert.assertEquals("lorem_ipsum_dolor", StringUtil.snakeCase("Lorem   ipsum    dolor"));
		Assert.assertEquals("lorem_ipsum_dolor", StringUtil.snakeCase("Lorem Ipsum Dolor"));
		Assert.assertEquals("lorem_ipsum_dolor", StringUtil.snakeCase("lorem-ipsum-dolor"));
		Assert.assertEquals("lorem_ipsum_dolor", StringUtil.snakeCase("lorem_ipsum_dolor"));
	}

	@Test
	public void testKebabCase() {
		Assert.assertEquals("lorem-ipsum-dolor", StringUtil.kebabCase("Lorem ipsum dolor"));
		Assert.assertEquals("lorem-ipsum-dolor", StringUtil.kebabCase("Lorem   ipsum    dolor"));
		Assert.assertEquals("lorem-ipsum-dolor", StringUtil.kebabCase("Lorem Ipsum Dolor"));
		Assert.assertEquals("lorem-ipsum-dolor", StringUtil.kebabCase("lorem-ipsum-dolor"));
		Assert.assertEquals("lorem-ipsum-dolor", StringUtil.kebabCase("lorem_ipsum_dolor"));
	}

}
