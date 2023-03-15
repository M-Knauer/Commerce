package com.knauer.dscommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.knauer.dscommerce.dto.ProductDTO;
import com.knauer.dscommerce.entities.Product;
import com.knauer.dscommerce.repositories.ProductRepository;
import com.knauer.dscommerce.service.exceptions.IntegrityViolationException;
import com.knauer.dscommerce.service.exceptions.ResourceNotFoundException;

import javax.persistence.EntityNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		return new ProductDTO(repository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Recurso não encontrado")));
	}

	@Transactional(readOnly = true)
	public Page<ProductDTO> findAll(String name, Pageable pageable) {
		Page<Product> entities = repository.searchByName(name, pageable);
		
		return entities.map(dto -> new ProductDTO(dto));
	}
	
	@Transactional
	public ProductDTO insert(ProductDTO dto) {
		Product entity = new Product();
		
		toEntity(dto, entity);
		
		return new ProductDTO(repository.save(entity));
	}

	@Transactional
	public ProductDTO update(Long id, ProductDTO dto) {
		try {
			Product entity = repository.getReferenceById(id);
	
			toEntity(dto, entity);
			
			return new ProductDTO(repository.save(entity));
		} 
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Recurso não encontrado");
		}
	}
	
	private void toEntity(ProductDTO dto, Product entity) {
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setImgUrl(dto.getImgUrl());
		entity.setPrice(dto.getPrice());
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Recurso não encontrado");
		}
		catch(DataIntegrityViolationException e) {
			throw new IntegrityViolationException("Falha de integridade referencial");
		}
	}
}
