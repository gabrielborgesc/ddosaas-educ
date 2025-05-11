package com.ddosaas.central.api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ddosaas.central.api.dto.RunCentralDTO;
import com.ddosaas.central.service.CentralService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/ddosaas")
public class CentralController {

	@Autowired
	private CentralService centralService;

	
	@PostMapping("/run")
	public ResponseEntity run(@RequestBody @Valid RunCentralDTO runDTO, BindingResult result) {

		if (result.hasErrors()) {
			// Se houver erros de validação, pegue a mensagem de erro exata
			StringBuilder errorMessages = new StringBuilder("Erros de validação: ");
			result.getAllErrors().forEach(error -> errorMessages.append(error.getDefaultMessage()).append(" "));

			return ResponseEntity.badRequest().body(errorMessages.toString());
		}

		try {
			this.centralService.run(runDTO);
			return ResponseEntity.ok("Ataque Concluído!");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
}
