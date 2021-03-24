package com.empresa.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.empresa.entity.Usuario;

public interface IUsuarioService extends AbstractServiceCRUD<Usuario, Usuario, Integer>{
	
	public void create(Usuario classs);
	
	public Usuario readById(Integer id);
	
	public void update(Usuario classDTO);
	
	public ArrayList<Usuario> read();
	
	public void delete(Integer id);
	
}
