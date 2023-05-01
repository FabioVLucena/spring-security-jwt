package com.aeon.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aeon.security.entity.Acesso;
import com.aeon.security.repository.AcessoRepository;

@Service
public class AcessoService {

	@Autowired
	private AcessoRepository acessoRepo;
	
	@Transactional(readOnly = true)
	public Acesso findById(Long id) throws Exception {
		
		Acesso acesso = acessoRepo.findById(id)
				.orElseThrow(() -> new Exception("Acesso de ID: " + id + " não encontrado"));
		
		return acesso;
	}
	
	@Transactional(readOnly = true)
	public List<Acesso> listAll() {
		
		List<Acesso> acessoList = acessoRepo.findAll();
		
		return acessoList;
	}
	
	@Transactional(readOnly = false)
	public Acesso cadastrarAcesso(Acesso acesso) {
		
		Acesso acessoSalvo = acessoRepo.save(acesso);
		
		return acessoSalvo;
	}
	
}
