package com.knauer.dscommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.knauer.dscommerce.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);

}
