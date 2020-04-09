package com.cupjate.util;

import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

public class StringUtil {
	
	private StringUtil() {
		// Util Class
	}

	public static final Locale LOCALE_BRASIL = new Locale("pt", "BR");

	
	public static String removeDuplicateWhitespace(String str) {
		return str.replaceAll("\\s+", " ");
	}

	/**
	 * camelCase
	 * 
	 * @param str
	 * @return
	 */
	public static String camelCase(String str) {
		return StringUtils.uncapitalize(removeSeparateCharAndCapitalizeAfter(str));
	}

	/**
	 * PascalCase
	 * 
	 * @param str
	 * @return
	 */
	public static String pascalCase(String str) {
		return StringUtils.capitalize(removeSeparateCharAndCapitalizeAfter(str));
	}

	/**
	 * snake_case
	 * 
	 * @param str
	 * @return
	 */
	public static String snakeCase(String str) {

		str = StringUtils.lowerCase(str);
		str = str.replaceAll("-", " ");
		str = removeDuplicateWhitespace(str);
		str = str.replaceAll(" ", "_");

		return str;
	}

	/**
	 * kebab-case
	 * 
	 * @param str
	 * @return
	 */
	public static String kebabCase(String str) {
		return snakeCase(str).replaceAll("_", "-");
	}
	
	
	protected static String removeSeparateCharAndCapitalizeAfter(String str) {
		str = removeDuplicateWhitespace(str);
		str = removeAndCapitalizeAfter(" ", str);
		str = removeAndCapitalizeAfter("-", str);
		str = removeAndCapitalizeAfter("_", str);
		return str;
	}

	protected static String removeAndCapitalizeAfter(String c, String str) {
		String out = str;
		int index = out.indexOf(c);
		while (index > 0) {
			if (index == (out.length() - 1)) {
				out = out.replace(c, "");
			} else {
				out = out.substring(0, index) + out.substring(index + 1, index + 2).toUpperCase(LOCALE_BRASIL)
						+ out.substring(index + 2);
			}
			index = out.indexOf(c);
		}
		return out;
	}

}
