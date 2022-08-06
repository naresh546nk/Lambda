package com.aws.lambda.app;

import java.util.List;
import java.util.stream.Collectors;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.aws.lambda.model.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ReadOrderLambda {
	public APIGatewayProxyResponseEvent getOrder(APIGatewayProxyRequestEvent request)
			throws JsonMappingException, JsonProcessingException {

		System.out.println("Executing the ReadOrderLambda method: ");
		ObjectMapper objectMapper = new ObjectMapper();

		Order order = new Order();
		order.id = 123;
		order.itemName = "Mack books 13 pro";
		order.quantity = 100;

		
		  AmazonDynamoDB dynamoDB = AmazonDynamoDBClientBuilder.defaultClient();
		  ScanResult scan = dynamoDB.scan(new
		  ScanRequest().withTableName(System.getenv("ORDERS_TABLE")));
		  
		  List<Order> Orders = scan.getItems().stream().map(item->new Order(
		  Integer.parseInt(item.get("id").getN()), 
		  item.get("itemName").getS(),
		  Integer.parseInt(item.get("quantity").getN())))
		  .collect(Collectors.toList());
		 

		String orderJson = objectMapper.writeValueAsString(Orders);

		return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody(orderJson);

	}

}