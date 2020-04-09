package com.cupjate.util;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import junit.framework.Assert;

public class FileUtilTest {

	@Test
	public void testGetFileContent() throws IOException {
		Assert.assertEquals("Hello ${name}", FileUtil.getFileContent("SimpleVariable/HelloWorld.tpl"));
	}

	@Test(expected = IOException.class)
	public void testGetFileContentNotExists() throws IOException {
		Assert.assertEquals("Hello ${name}", FileUtil.getFileContent("FileNotExists"));
	}

	@Test
	public void testPathFromResources() throws FileNotFoundException {
		Assert.assertNotNull(FileUtil.getPathFromResources("SimpleVariable/HelloWorld.tpl"));
	}

	@Test(expected = FileNotFoundException.class)
	public void testPathFromResourcesFileNotFoundException() throws FileNotFoundException {
		FileUtil.getPathFromResources("FileNotExists");
	}

}
