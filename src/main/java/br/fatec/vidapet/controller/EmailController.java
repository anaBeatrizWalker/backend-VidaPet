package br.fatec.vidapet.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.fatec.vidapet.dto.EmailDTO;
import br.fatec.vidapet.service.EmailService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/email")
public class EmailController {
	
	@Autowired
	private EmailService service;

	@PostMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	@Operation(summary = "Envio de E-mail HTML")
	public ResponseEntity<?> sendHtmlEmail(@RequestBody EmailDTO email) {
		try {
			service.sendHtmlEmail(email);
			return ResponseEntity.ok("E-mail enviado.");
		} catch (MailException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (MessagingException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
}
