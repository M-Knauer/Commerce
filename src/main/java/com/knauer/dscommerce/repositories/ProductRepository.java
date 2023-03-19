package com.knauer.dscommerce.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.knauer.dscommerce.dto.ProductMinDTO;
import com.knauer.dscommerce.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	@Query("SELECT new com.knauer.dscommerce.dto.ProductMinDTO(obj.id, obj.name, obj.price, obj.imgUrl) "
			+ "FROM Product obj "
			+ "WHERE UPPER(obj.name) LIKE UPPER(CONCAT('%', :name, '%'))")
	Page<ProductMinDTO> searchByName(String name, Pageable pageable);

}
