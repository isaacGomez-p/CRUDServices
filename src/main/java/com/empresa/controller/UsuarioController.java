package com.empresa.controller;

import java.util.ArrayList;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.entity.Usuario;
import com.empresa.service.imp.UsuarioServiceImp;

@Controller
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	UsuarioServiceImp serv;
	
	@PostMapping("/create")
	//@ApiOperation(value="Metodo que inserta una Editorial a la BD")
	public ResponseEntity create(@Valid @RequestBody Usuario usuario){
		serv.create(usuario);
		return new ResponseEntity(HttpStatus.CREATED);
	}
	
	@GetMapping("/read")
	//@ApiOperation(value = "Retorna todos los libros")
	public ResponseEntity<ArrayList<Usuario>> read() {
		return new ResponseEntity<ArrayList<Usuario>>(serv.read(), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity delete(@PathVariable @Min(value = 0, message = "There aare not values under 0") int id) {
		serv.delete(id);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/readbyid/{id}")
	public ResponseEntity<Usuario> readbyid(@PathVariable int id){
		return new ResponseEntity<Usuario>(serv.readById(id) , HttpStatus.OK);
	}	
}
