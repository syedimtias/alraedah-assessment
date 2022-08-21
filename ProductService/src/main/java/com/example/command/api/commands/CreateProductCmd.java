package com.example.command.api.commands;

import java.math.BigDecimal;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateProductCmd {

	@TargetAggregateIdentifier
	private String productId;
	private String name;
	private BigDecimal price;
	private Integer quantity;
}
