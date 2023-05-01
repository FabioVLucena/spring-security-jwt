package com.aeon.security.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aeon.security.dto.AcessoCadastroRequestDTO;
import com.aeon.security.entity.Acesso;
import com.aeon.security.service.AcessoService;

@RestController
@RequestMapping("/acessos")
public class AcessoController {

	@Autowired
	private AcessoService acessoService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	public ResponseEntity<List<Acesso>> getAcessos() {
		
		List<Acesso> acessoList = acessoService.listAll();
		
		return ResponseEntity.status(HttpStatus.OK).body(acessoList);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Acesso> getAcesso(@PathVariable Long id) throws Exception {

		Acesso acesso = acessoService.findById(id);

		return ResponseEntity.status(HttpStatus.OK).body(acesso);
	}
	
	@PostMapping
	public ResponseEntity<Acesso> cadastrarAcesso(@RequestBody AcessoCadastroRequestDTO acessoDTO) {
		Acesso acesso = modelMapper.map(acessoDTO, Acesso.class);
		
		Acesso acessoSalvo = acessoService.cadastrarAcesso(acesso);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(acessoSalvo);
	}
	
}
