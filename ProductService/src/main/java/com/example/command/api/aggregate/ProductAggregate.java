package com.example.command.api.aggregate;

import java.math.BigDecimal;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import com.example.command.api.commands.CreateProductCmd;
import com.example.command.api.events.ProductCreatedEvent;

import lombok.Data;

@Aggregate
@Data
public class ProductAggregate {

	@AggregateIdentifier
	private String productId;
	private String name;
	private BigDecimal price;
	private Integer quantity;

	public ProductAggregate() {

	}

	@CommandHandler
	public ProductAggregate(CreateProductCmd createProductCmd) {
		// You can perform all the validations
		ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent();
		// Populate event
		BeanUtils.copyProperties(createProductCmd, productCreatedEvent);
		// Publish the event
		AggregateLifecycle.apply(productCreatedEvent);
	}
	
	// Event Handler
	@EventSourcingHandler
	public void on(ProductCreatedEvent productCreatedEvent) {
		this.productId = productCreatedEvent.getProductId();
		this.name = productCreatedEvent.getName();
		this.quantity = productCreatedEvent.getQuantity();
		this.price = productCreatedEvent.getPrice();
	}
}
