package com.knauer.dscommerce.dto;

import com.knauer.dscommerce.entities.Product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ProductDTO {

	private Long id;
	
	@NotBlank(message = "Nome não pode estar em branco")
	@Size(min = 3, max = 80, message = "Nome precisa ter de 3 a 80 caracteres")
	private String name;
	
	@NotBlank(message = "Nome não pode estar em branco")
	@Size(min = 10, message = "Descrição precisa ter no minimo 10 caracteres")
	private String description;
	
	@NotNull(message = "Valor não pode ser nulo")
	@Positive(message = "O preço precisa ser positivo")
	private Double price;
	private String imgUrl;

	public ProductDTO() {

	}

	public ProductDTO(Long id, String name, String description, Double price, String imgUrl) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
	}

	public ProductDTO(Product entity) {
		id = entity.getId();
		name = entity.getName();
		description = entity.getDescription();
		price = entity.getPrice();
		imgUrl = entity.getImgUrl();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Double getPrice() {
		return price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

}
