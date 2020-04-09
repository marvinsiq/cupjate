package com.cupjate;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.cupjate.exception.SyntaxError;
import com.cupjate.util.FileUtil;
import com.cupjate.util.ReflectionUtil;

public class Cupjate {

	private HashMap<String, Object> vars = new HashMap<>();

	private Pattern patternFor = Pattern.compile("\\{\\s*for\\s*(.+)\\s*in\\s(.+((\\.\\w+)*))\\s*\\}");
	private Pattern patternEndFor = Pattern.compile("\\{\\s*endfor\\s*\\}");

	public void put(String key, Object value) {
		vars.put(key, value);
	}

	public String process(String templateFile) throws IOException {

		String content = FileUtil.getFileContent(templateFile);

		if (StringUtils.isEmpty(content)) {
			return "";
		}
		return processContent(content);

	}

	public String processContent(String content) {

		for (Entry<String, Object> entry : vars.entrySet()) {

			String key = entry.getKey();
			Object data = entry.getValue();

			content = processLoop(content, key, data);
			content = processIfStatements(content, key, data);
			content = processVariable(content, key, data);

		}

		content = processPredefined(content);

		return content;
	}

	private String processPredefined(String content) {
		return processVariable(content, "now", new Date());
	}

	private String processIfStatements(String content, String key, Object data) {
		// TODO Auto-generated method stub
		return content;
	}

	private String processLoop(String content, String key, Object data) {

		Pattern keyPatternFor = Pattern.compile("\\{\\s*for\\s*(.+)\\s*in\\s(" + key + "((\\.\\w+)*))\\s*\\}");

		Matcher matcher = keyPatternFor.matcher(content);

		while (matcher.find()) {

			int startIndex = matcher.end();
			String before = content.substring(0, startIndex - matcher.group(0).length() - 1);

			String var = matcher.group(1).trim();
			String list = matcher.group(2).trim();

			String endString = content.substring(matcher.end());

			Matcher matcherEndFor = getEndForMatcher(endString);

			if (matcherEndFor == null) {
				throw new SyntaxError("Syntax error, insert \"{ endfor }\" to complete Block.");
			}

			int endIndex = startIndex + matcherEndFor.start();

			String after = content.substring(endIndex + matcherEndFor.group(0).length());

			String blockString = endString.substring(0, matcherEndFor.start());

			content = processLoopBlock(data, var, list, before, after, blockString);

			matcher = keyPatternFor.matcher(content);
		}

		return content;
	}

	private Matcher getEndForMatcher(String content) {

		// procura pelo fechamento do bloco
		Matcher matcherEndFor = patternEndFor.matcher(content);

		if (matcherEndFor.find()) {
			int indexEnd = matcherEndFor.start();

			// Verifica se astá abrindo outro bloco for
			String newContent = content.substring(0, indexEnd);

			return getEndForMatcher(newContent, matcherEndFor);

		} else {
			return null;
		}

	}

	private Matcher getEndForMatcher(String newContent, Matcher matcherEndFor) {
		Matcher newMatcher = patternFor.matcher(newContent);
		if (newMatcher.find()) {

			int newMatcherIndex = newMatcher.start();

			matcherEndFor.find();

			String newContent2 = newContent.substring(newMatcherIndex + newMatcher.group(0).length());
			return getEndForMatcher(newContent2, matcherEndFor);

		} else {
			return matcherEndFor;
		}
	}

	private String processLoopBlock(Object data, String var, String list, String before, String after,
			String blockString) {

		StringBuilder processLoop = new StringBuilder();

		StringBuilder loopBlock = new StringBuilder();

		if (data != null) {

			Object dataValue = getLastObjectData(list, data);

			@SuppressWarnings("rawtypes")
			Collection collection = getValueAsCollection(dataValue);

			int index = 0;
			for (Object collectionValue : collection) {

				String processBlock = blockString;
				processBlock = processVariable(processBlock, var + "#index", String.valueOf(index++));
				processBlock = processVariable(processBlock, var + "#count", String.valueOf(index));

				processBlock = processVariable(processBlock, var, collectionValue);

				// internal fors
				processBlock = processLoop(processBlock, var, collectionValue);

				loopBlock.append(processBlock);
			}

			processLoop.append(before).append(loopBlock).append(after);
		} else {
			processLoop.append(before).append(loopBlock).append(after);
		}

		return processLoop.toString();
	}

	private Object getLastObjectData(String list, Object dataValue) {
		if (list.contains(".")) {
			String[] props = list.split("\\.");

			for (int i = 1; i < props.length; i++) {
				dataValue = ReflectionUtil.getFieldValueIfExists(dataValue, props[i]);
			}

		}
		return dataValue;
	}

	@SuppressWarnings("rawtypes")
	private Collection getValueAsCollection(Object value) {
		Collection collection;

		try {
			collection = (Collection) value;
		} catch (Exception e) {
			throw new SyntaxError("Value is not a collection.");
		}
		return collection;
	}

	private String processVariable(String content, String variable, Object data) {

		String regex = "\\$\\{" + variable + "((\\.\\w+)*)\\}";

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(content);

		while (matcher.find()) {

			Object dataValue = (data == null) ? "" : data;

			if (!StringUtils.isEmpty(matcher.group(1))) {

				regex = "\\$\\{" + variable + matcher.group(1) + "\\}";
				String variableProps = matcher.group(1);

				String[] props = variableProps.split("\\.");
				for (int i = 1; i < props.length; i++) {
					dataValue = ReflectionUtil.getFieldValueIfExists(dataValue, props[i]);
				}
			}

			content = content.replaceAll(regex, dataValue.toString());
		}

		return content;
	}
}
