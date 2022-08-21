package com.example.query.api.controllers;

import java.util.List;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.command.api.models.ProductRestModel;
import com.example.query.api.queries.GetProductQuery;

@RestController
@RequestMapping("/products")
public class ProductQueryController {

	private QueryGateway queryGateway;

	public ProductQueryController(QueryGateway queryGateway) {
		super();
		this.queryGateway = queryGateway;
	}

	@GetMapping
	public List<ProductRestModel> getAllProducts() {
		GetProductQuery getProductQuery = new GetProductQuery();
		List<ProductRestModel> productRestModels = queryGateway
				.query(getProductQuery, ResponseTypes.multipleInstancesOf(ProductRestModel.class)).join();
		return productRestModels;
	}
}
