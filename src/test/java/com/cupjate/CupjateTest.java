package com.cupjate;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.cupjate.aux.Address;
import com.cupjate.aux.Client;
import com.cupjate.aux.Item;
import com.cupjate.aux.Order;
import com.cupjate.exception.SyntaxError;
import com.cupjate.util.FileUtil;

import junit.framework.Assert;

public class CupjateTest {

	@Test
	public void testSimpleVariable() throws IOException {

		Cupjate cupjate = new Cupjate();
		cupjate.put("name", "World");
		String output = cupjate.process("SimpleVariable/HelloWorld.tpl");
		Assert.assertEquals("Hello World", output);

		output = cupjate.processContent("Hello ${name}");
		Assert.assertEquals("Hello World", output);
	}
	
	@Test
	public void testSimpleVariableNullValue() throws IOException {

		Cupjate cupjate = new Cupjate();
		cupjate.put("name", null);
		String output = cupjate.process("SimpleVariable/HelloWorld.tpl");
		Assert.assertEquals("Hello ", output);
	}	

	@Test
	public void testSeveralVariables() throws IOException {

		Cupjate cupjate = new Cupjate();
		cupjate.put("name", "Marcus");
		cupjate.put("occupation", "programmer");
		cupjate.put("age", "35");

		String output = cupjate.process("SimpleVariable/SeveralVariables.tpl");
		System.out.println(output);
	}

	@Test
	public void testForSimpleData() throws IOException {

		List<String> fruitList = new ArrayList<>();
		fruitList.add("Apple");
		fruitList.add("Apple");
		fruitList.add("Cherries");
		fruitList.add("Guava");

		Cupjate cupjate = new Cupjate();
		cupjate.put("fruits", fruitList);
		String output = cupjate.process("ForStatement/SimpleData.tpl");

		String expected = "List:\n\t0 - Apple\n\t1 - Apple\n\t2 - Cherries\n\t3 - Guava";
		Assert.assertEquals(expected, output);
	}
	
	@Test
	public void testForItemNullValue() throws IOException {

		List<String> fruitList = new ArrayList<>();
		fruitList.add(null);		

		Cupjate cupjate = new Cupjate();
		cupjate.put("fruits", fruitList);
		String output = cupjate.process("ForStatement/SimpleData.tpl");

		String expected = "List:\n\t0 - ";
		Assert.assertEquals(expected, output);
	}	
	
	@Test
	public void testForNullValue() throws IOException {

		Cupjate cupjate = new Cupjate();
		cupjate.put("fruits", null);
		String output = cupjate.process("ForStatement/SimpleData.tpl");

		String expected = "List:";
		Assert.assertEquals(expected, output);
	}	

	@Test
	public void testForSyntaxErrorNoEndforFound() throws ParseException, IOException {

		List<String> fruitList = new ArrayList<>();
		fruitList.add("Apple");
		fruitList.add("Apple");

		Cupjate cupjate = new Cupjate();
		cupjate.put("fruits", fruitList);
				
		try {
			cupjate.process("ForStatement/SyntaxError.tpl");
			Assert.fail("Expected SyntaxError");
		} catch (Exception e) {
			Assert.assertTrue(e instanceof SyntaxError);
			Assert.assertEquals("Syntax error, insert \"{ endfor }\" to complete Block.", e.getMessage());
		}		
	}

	@Test
	public void testForSyntaxErrorValueIsNotACollection() throws IOException {
		String fruit = "Apple";
		Cupjate cupjate = new Cupjate();
		cupjate.put("fruits", fruit);
		
		try {
			cupjate.process("ForStatement/SimpleData.tpl");
			Assert.fail("Expected SyntaxError");
		} catch (Exception e) {
			Assert.assertTrue(e instanceof SyntaxError);
			Assert.assertEquals("Value is not a collection.", e.getMessage());
		}
	}

	@Test
	public void testForCompexData() throws ParseException, IOException {

		Client client = generateDummyData();

		Cupjate cupjate = new Cupjate();
		cupjate.put("client", client);
		String output = cupjate.process("ForStatement/ComplexData.tpl");
		System.out.println(output);

		String expected = FileUtil.getFileContent("ForStatement/ComplexDataExpected.txt");
		Assert.assertEquals(expected, output);
	}

	@Test
	public void testForComplexDataSeveralFor() throws ParseException, IOException {

		Client client = generateDummyData();

		Cupjate cupjate = new Cupjate();
		cupjate.put("client", client);
		String output = cupjate.process("ForStatement/ComplexDataSeveralFor.tpl");
		System.out.println(output);

		String expected = FileUtil.getFileContent("ForStatement/ComplexDataSeveralForExpected.txt");
		Assert.assertEquals(expected, output);
	}

	@Test
	public void testEmptyTemplate() throws ParseException, IOException {
		Cupjate cupjate = new Cupjate();
		String output = cupjate.process("EmptyFile.tpl");
		Assert.assertEquals("", output);
	}

	@Test(expected = FileNotFoundException.class)
	public void testFileNotFoundException() throws ParseException, IOException {
		Cupjate cupjate = new Cupjate();
		cupjate.process("FileNotExists.tpl");
	}

	private Client generateDummyData() throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Client client = new Client();
		

		client.setId(1265L);
		client.setName("Marcus");

		Order order1 = generateDummyOrder1(sdf, client);
		client.getOrders().add(order1);
		
		Order order2 = generateDummyOrder2(sdf, client);
		client.getOrders().add(order2);		

		Address address = new Address();
		address.setCity("São Paulo");
		address.setState("SP");
		address.setStreet("Rua Alvorada");

		client.setAddress(address);

		client.getPhones().add("31 123 456 789");
		client.getPhones().add("31 987 654 321");
		client.getPhones().add("31 456 789 123");

		return client;
	}

	private Order generateDummyOrder1(SimpleDateFormat sdf, Client client) throws ParseException {
		Item item;
		Order order = new Order();
		order.setDate(sdf.parse("01/03/2020"));
		order.setId(255L);

		item = new Item();
		item.setId(46532L);
		item.setName("Computer");
		order.getItens().add(item);
		order.setClient(client);

		item = new Item();
		item.setId(79353L);
		item.setName("Monitor");
		order.getItens().add(item);
		order.setClient(client);
		return order;
	}
	
	private Order generateDummyOrder2(SimpleDateFormat sdf, Client client) throws ParseException {
		Item item;
		Order order = new Order();
		order.setDate(sdf.parse("05/03/2020"));
		order.setId(256L);

		item = new Item();
		item.setId(64564L);
		item.setName("Keyboard");
		order.getItens().add(item);
		order.setClient(client);

		item = new Item();
		item.setId(87543L);
		item.setName("Memory Card");
		order.getItens().add(item);
		order.setClient(client);
		
		item = new Item();
		item.setId(53453L);
		item.setName("Headset");
		order.getItens().add(item);
		order.setClient(client);
		
		return order;
	}

}
