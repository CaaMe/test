package com.spring.boot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.dto.LoginUsuario;
import com.spring.boot.entity.Usuario;
import com.spring.boot.service.IUsuarioService;


@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = {  "*", "http://localhost:4200"})
public class AuthController {

	@Autowired
	private IUsuarioService usuarioService;

	@PostMapping("/login")
	public ResponseEntity<?> login (@Valid @RequestBody LoginUsuario login, BindingResult result) {
		Map<String, Object> response = new HashMap<>();
		Usuario usuario = null;
		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			usuario = usuarioService.findByUsernameAndPassword(login.getUsername(), login.getPassword());
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("mensaje", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (usuario == null) {
			response.put("mensaje",
					"El Usuario o el Password son incorrectos favor de verificar");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		response.put("ok", true);
		response.put("mensaje", "El usuario se ha logueado con éxito!");
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
