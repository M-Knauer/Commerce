package com.knauer.dscommerce.dto;

import com.knauer.dscommerce.entities.OrderItem;

public class OrderItemDTO {

	private Long productId;
	private String name;
	private Double price;
	private Integer quantity;
	
	public OrderItemDTO(Long productId, String name, Double price, Integer quantity) {
		this.productId = productId;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}
	
	public OrderItemDTO(OrderItem entity) {
		this.productId = entity.getProduct().getId();
		this.name = entity.getProduct().getName();
		this.price = entity.getPrice();
		this.quantity = entity.getQuantity();
	}

	public Long getProductId() {
		return productId;
	}

	public String getName() {
		return name;
	}

	public Double getPrice() {
		return price;
	}

	public Integer getQuantity() {
		return quantity;
	}
	
	public Double getSubTotal() {
		return quantity * price;
	}
	
}
