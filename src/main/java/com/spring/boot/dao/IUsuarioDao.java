package com.spring.boot.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.spring.boot.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long> {
	
	@Query("select u from Usuario u where u.username=?1 and u.password=?2")
	public Usuario findByUsernameAndPassword(String username, String password);

}
