package com.aws.lambda.model;

public class Order {
	public int id;
	public String itemName;
	public int quantity;
	public Order(int id, String itemName, int quantity) {
		super();
		this.id = id;
		this.itemName = itemName;
		this.quantity = quantity;
	}
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
