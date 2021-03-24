package com.empresa.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.empresa.entity.Usuario;
import com.empresa.exception.ObjectAlreadyFoundHandler;
import com.empresa.exception.ObjectNotFoundExceptionHandler;
import com.empresa.repository.IUsuarioRepo;
import com.empresa.service.IUsuarioService;

@Service
public class UsuarioServiceImp implements IUsuarioService, UserDetailsService {

	@Autowired
	IUsuarioRepo repo;
	
	@Autowired
	private BCryptPasswordEncoder byc;
	
	@Override
	public void create(Usuario classs) {
		// TODO Auto-generated method stub
		if(repo.findByNick(classs.getNick()) != null){
			throw new ObjectAlreadyFoundHandler("This nick is alreaady in use!");
		}
		else {
			classs.setClave(byc.encode(classs.getClave()));
			repo.save(classs);
		}
	}

	@Override
	public Usuario readById(Integer id) {
		// TODO Auto-generated method stub
		if(repo.findById(id) == null) {
			throw new ObjectNotFoundExceptionHandler(" User not found ");
		}
		else {
			return repo.findById(id).get();
		}
	}	

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}
	
	@Override
	public ArrayList<Usuario> read() {
		// TODO Auto-generated method stub
		if(repo.findAll() == null) {
			throw new ObjectNotFoundExceptionHandler("There are not users created");
		}
		else {
			return (ArrayList<Usuario>) repo.findAll();
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Usuario usuario = repo.findByNick(username);
		if(usuario == null) 
			throw new ObjectNotFoundExceptionHandler("----Nickname does not exist");
		if(usuario.isEstado() == false)
			throw new ObjectAlreadyFoundHandler("----Nickname is not active");
		
		List<GrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority(usuario.getRol().getName()));
		
		UserDetails udt = new User(usuario.getNick(), usuario.getClave(), roles);
		return udt;			
	}
	
	@Override
	public void update(Usuario classDTO) {
		// TODO Auto-generated method stub
		
	}	

}
