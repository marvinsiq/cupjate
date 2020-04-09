package com.cupjate.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.cupjate.Cupjate;

public class FileUtil {
	
	private FileUtil() {
		// Util Class
	}

	public static String getFileContent(String fileName) throws IOException {
		if (!new File(fileName).exists()) {
			fileName = getPathFromResources(fileName);
		}
		return getFileContent(fileName, StandardCharsets.UTF_8);
	}

	public static String getFileContent(String path, Charset encoding) throws IOException {
		List<String> lines = Files.readAllLines(Paths.get(path), encoding);
		return StringUtils.join(lines, System.lineSeparator());
	}	
	
	public static String getPathFromResources(String fileName) throws FileNotFoundException {
		ClassLoader classLoader = Cupjate.class.getClassLoader();
		URL url = classLoader.getResource(fileName);
		if (url == null) {
			throw new FileNotFoundException(fileName);
		}
		File file = new File(url.getFile());
		return file.getAbsolutePath();
	}


}