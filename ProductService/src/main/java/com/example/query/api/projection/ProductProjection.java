package com.example.query.api.projection;

import java.util.List;
import java.util.stream.Collectors;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import com.example.command.api.data.Product;
import com.example.command.api.data.ProductRepository;
import com.example.command.api.models.ProductRestModel;
import com.example.query.api.queries.GetProductQuery;

@Component
public class ProductProjection {

	private ProductRepository productRepository;

	public ProductProjection(ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}

	@QueryHandler
	public List<ProductRestModel> handle(GetProductQuery getProductQuery) {
		List<Product> products = productRepository.findAll();

		List<ProductRestModel> productRestModels = products.stream().map(product -> ProductRestModel.builder()
				.name(product.getName()).price(product.getPrice()).quantity(product.getQuantity()).build())
				.collect(Collectors.toList());
		return productRestModels;

	}
}
