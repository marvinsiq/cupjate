package com.cupjate.aux;

import java.util.ArrayList;
import java.util.List;

public class Client {

	private Long id;
	private String name;

	private boolean special = true;
	private boolean admin = false;

	private Address address;

	private List<String> phones = new ArrayList<>();

	private List<Order> orders = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isSpecial() {
		return special;
	}

	public void setSpecial(boolean special) {
		this.special = special;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<String> getPhones() {
		return phones;
	}

	public void setPhones(List<String> phones) {
		this.phones = phones;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", special=" + special + ", admin=" + admin + ", address="
				+ address + ", phones=" + phones.size() + ", orders=" + orders.size() + "]";
	}
	
	
}