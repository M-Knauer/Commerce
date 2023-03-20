package com.knauer.dscommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knauer.dscommerce.dto.OrderDTO;
import com.knauer.dscommerce.repositories.OrderRepository;
import com.knauer.dscommerce.service.exceptions.ResourceNotFoundException;

@Service
public class OrderService {


	@Autowired
	private OrderRepository repository;
	
	@Transactional(readOnly = true)
	public OrderDTO findById(Long id) {
		return new OrderDTO(repository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Recurso não encontrado")));
	}
}
