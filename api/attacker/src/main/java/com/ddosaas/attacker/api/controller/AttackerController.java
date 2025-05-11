package com.ddosaas.attacker.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ddosaas.attacker.api.dto.RunAttackerDTO;
import com.ddosaas.attacker.service.AttackerService;

@RestController
@RequestMapping("/api/ddosaas")
public class AttackerController {

	@Autowired
	private AttackerService attackerService;
    
	@PostMapping("/run")
	public ResponseEntity run(@RequestBody RunAttackerDTO runDTO) {

		try {
			this.attackerService.run(runDTO);
			return ResponseEntity.ok("Ataque Concluído!");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}    

}
