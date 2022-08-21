package com.example.command.api.controllers;

import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.command.api.commands.CreateProductCmd;
import com.example.command.api.models.ProductRestModel;

@RestController
@RequestMapping("/products")
public class ProductCommandController {

	private CommandGateway commandGateway;

	public ProductCommandController(CommandGateway commandGateway) {
		super();
		this.commandGateway = commandGateway;
	}

	@PostMapping
	public String addProduct(@RequestBody ProductRestModel productRestModel) {
		CreateProductCmd createProductCmd = CreateProductCmd.builder()
				.productId(UUID.randomUUID().toString())
				.name(productRestModel.getName())
				.price(productRestModel.getPrice())
				.quantity(productRestModel.getQuantity())
				.build();
		
		String result = commandGateway.sendAndWait(createProductCmd);
		return result;
	}
}
