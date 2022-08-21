package com.example.command.api.models;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductRestModel {

	private String name;
	private BigDecimal price;
	private Integer quantity;
}
