package com.aws.lambda.app;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.aws.lambda.model.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CreateOrderLambda {
	public APIGatewayProxyResponseEvent createOrder(APIGatewayProxyRequestEvent request) throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper =new ObjectMapper();
		
		Order order = objectMapper.readValue(request.getBody(), Order.class);
		
		
		/*
		 * DynamoDB dynamoDB = new
		 * DynamoDB(AmazonDynamoDBClientBuilder.defaultClient());
		 * 
		 * Table table = dynamoDB.getTable(System.getenv("ORDERS_TABLE"));
		 * 
		 * Item item=new Item() .withPrimaryKey("id",order.id) .withString("itemName",
		 * order.itemName) .withInt("quentiry", order.quantity);
		 * 
		 * table.putItem(item);
		 * 
		 */
		return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody("Order ID:"+order.id);
		
		
	}

}
