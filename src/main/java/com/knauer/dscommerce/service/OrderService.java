package com.knauer.dscommerce.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knauer.dscommerce.dto.OrderDTO;
import com.knauer.dscommerce.entities.Order;
import com.knauer.dscommerce.entities.OrderItem;
import com.knauer.dscommerce.entities.OrderStatus;
import com.knauer.dscommerce.entities.Product;
import com.knauer.dscommerce.entities.User;
import com.knauer.dscommerce.repositories.OrderItemRepository;
import com.knauer.dscommerce.repositories.OrderRepository;
import com.knauer.dscommerce.repositories.ProductRepository;
import com.knauer.dscommerce.service.exceptions.ResourceNotFoundException;

@Service
public class OrderService {


	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Transactional(readOnly = true)
	public OrderDTO findById(Long id) {
		return new OrderDTO(repository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Recurso não encontrado")));
	}

	@Transactional
	public OrderDTO insert(OrderDTO dto) {
		Order order = new Order();
		order.setMoment(Instant.now());
		order.setStatus(OrderStatus.WAITING_PAYMENT);
		User user = userService.authenticated();
		order.setClient(user);
		
		dto.getItems().forEach(itemDto -> {
			Product product = productRepository.getReferenceById(itemDto.getProductId());
			OrderItem orderItem = new OrderItem(order, product, itemDto.getQuantity(), product.getPrice());
			order.getItems().add(orderItem);
		});
		
		repository.save(order);
		orderItemRepository.saveAll(order.getItems());
		
		return new OrderDTO(order);
	}
}
