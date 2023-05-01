package com.aeon.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aeon.security.entity.Acesso;

public interface AcessoRepository extends JpaRepository<Acesso, Long> {
	
}
