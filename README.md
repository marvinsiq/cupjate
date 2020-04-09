# CupJate
 CupJate is a Java template engine that can process and generate any type of file.


Usage
-----

A quick example:

```java 
Cupjate cupjate = new Cupjate();
cupjate.put("name", "World!");
String output = cupjate.proccessContent("Hello ${name}");
System.out.println(output); // "Hello World!"
```

Using a template file


Template (`HelloWorld.tpl`) :

```
Hello ${name}
```

Java Code:

```java 
Cupjate cupjate = new Cupjate();
cupjate.put("name", "World");
String output = cupjate.proccess("HelloWorld.tpl");
System.out.println(output); // "Hello World!"
```

Iteration
-----

Template (`List.tpl`) :

```
List:
{ for fruit in fruits }
	${fruit#index} - ${fruit}
{ endfor }
```

Java Code:

```java 
List<String> fruitList = new ArrayList<>();
fruitList.add("Apple");
fruitList.add("Cherries");
fruitList.add("Guava");

Cupjate cupjate = new Cupjate();
cupjate.put("fruits", fruitList);
String output = cupjate.proccess("List.tpl");
```

Output

```
List:
	0 - Apple
	1 - Cherries
	2 - Guava
```

