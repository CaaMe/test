package com.spring.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.boot.dao.IUsuarioDao;
import com.spring.boot.entity.Usuario;

@Service
public class UserServiceImpl implements IUsuarioService{
	
	@Autowired
	private IUsuarioDao repository;

	@Override
	@Transactional(readOnly=true)
	public List<Usuario> findAll() {
		return (List<Usuario>) repository.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Usuario findById(Long id) {
		return repository.findById(id).orElse(null);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Usuario findByUsernameAndPassword(String username, String password) {
		return repository.findByUsernameAndPassword(username, password);
	}

	@Override
	@Transactional
	public Usuario save(Usuario usuario) {
		return repository.save(usuario);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		repository.deleteById(id);		
	}
}
