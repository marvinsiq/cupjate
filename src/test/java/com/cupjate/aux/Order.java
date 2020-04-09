package com.cupjate.aux;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
	
	private Long id;
	private Date date;
	private List<Item> itens = new ArrayList<>();
	private Client client;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public List<Item> getItens() {
		return itens;
	}
	public void setItens(List<Item> itens) {
		this.itens = itens;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", date=" + date + ", itens=" + itens.size() + ", client=" + (client != null ? client.getId() : "null") + "]";
	}		
}
