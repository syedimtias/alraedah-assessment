package com.example.command.api.events;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.example.command.api.data.Product;
import com.example.command.api.data.ProductRepository;

@Component
@ProcessingGroup("Product")
public class ProductEventsHandler {

	private ProductRepository productRepository;

	public ProductEventsHandler(ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}

	@EventHandler
	public void on(ProductCreatedEvent event) throws Exception {
		Product product = new Product();
		BeanUtils.copyProperties(event, product);
		productRepository.save(product);
		throw new Exception("Exception Occured");
	}

	@ExceptionHandler
	public void handle(Exception exception) throws Exception {
		throw exception;
	}
}
