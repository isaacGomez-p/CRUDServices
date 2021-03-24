package com.empresa.controller;

import java.util.ArrayList;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.entity.Department;
import com.empresa.service.IDepartmentService;

@PreAuthorize("hasAuthority('Usuarioo')")
@Controller
@RestController
@RequestMapping("/departments")
public class DepartmentController {
	
	@Autowired
	IDepartmentService depSer;
	
	@PostMapping("/create")
	//@ApiOperation(value="Metodo que inserta una Editorial a la BD")
	public ResponseEntity create(@Valid @RequestBody Department depart){
		depSer.create(depart);
		return new ResponseEntity(HttpStatus.CREATED);
	}
	
	@GetMapping("/read")
	//@ApiOperation(value = "Retorna todos los libros")
	public ResponseEntity<ArrayList<Department>> read() {
		return new ResponseEntity<ArrayList<Department>>(depSer.read(), HttpStatus.OK);
	}

	@PutMapping("/update")
	//@ApiOperation(value = "Actualiza los datos del libro que se requiera n BD")
	public ResponseEntity update(@Valid  @RequestBody Department depart){
		depSer.update(depart);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity delete(@PathVariable @Min(value = 0, message = "There are not values under 0") int id) {
		depSer.delete(id);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/readbyid/{id}")
	public ResponseEntity<Department> readbyid(@PathVariable int id){
		return new ResponseEntity<Department>(depSer.readById(id) , HttpStatus.OK);
	}	

}
