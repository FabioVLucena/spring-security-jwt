package com.aeon.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aeon.security.entity.Acesso;

public interface UserRepository extends JpaRepository<Acesso, Long> {
	
}
