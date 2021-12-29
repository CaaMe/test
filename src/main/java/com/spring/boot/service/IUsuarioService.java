package com.spring.boot.service;

import java.util.List;

import com.spring.boot.entity.Usuario;

public interface IUsuarioService {

public List<Usuario> findAll();
	
	public Usuario findById(Long id);

	public Usuario save(Usuario usuario);
	
	public void delete(Long id);
	
	public Usuario findByUsernameAndPassword(String username, String password);
}
