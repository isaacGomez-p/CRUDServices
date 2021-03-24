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
import com.empresa.entity.Employee;
import com.empresa.service.IEmployeeService;

@PreAuthorize("hasAuthority('Admin')")
@Controller
@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	IEmployeeService eser;
	
	@PostMapping("/create")
	//@ApiOperation(value="Metodo que inserta una Editorial a la BD")
	public ResponseEntity create(@Valid @RequestBody Employee employee){
		eser.create(employee);
		return new ResponseEntity(HttpStatus.CREATED);
	}
	
	@GetMapping("/read")
	//@ApiOperation(value = "Retorna todos los libros")
	public ResponseEntity<ArrayList<Employee>> read() {
		return new ResponseEntity<ArrayList<Employee>>(eser.read(), HttpStatus.OK);
	}

	@PutMapping("/update")
	//@ApiOperation(value = "Actualiza los datos del libro que se requiera n BD")
	public ResponseEntity update(@Valid  @RequestBody Employee employee){
		eser.update(employee);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity delete(@PathVariable @Min(value = 0, message = "There aare not values under 0") int id) {
		eser.delete(id);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/readbyid/{id}")
	public ResponseEntity<Employee> readbyid(@PathVariable int id){
		return new ResponseEntity<Employee>(eser.readById(id) , HttpStatus.OK);
	}	
	
	@GetMapping("/readbydepartment")
	public ResponseEntity<ArrayList<Employee>> readbydepartment(@RequestBody Department dep) {
		return new ResponseEntity<ArrayList<Employee>>(eser.readByDepartment(dep), HttpStatus.OK);
	}
	
	@GetMapping("/readbyactive")
	public ResponseEntity<ArrayList<Employee>> readbyactive() {
		return new ResponseEntity<ArrayList<Employee>>(eser.readByActive(true), HttpStatus.OK);
	}
	
	@GetMapping("/readbynonactive")
	public ResponseEntity<ArrayList<Employee>> readbynonactive() {
		return new ResponseEntity<ArrayList<Employee>>(eser.readByNotActive(false), HttpStatus.OK);
	}
	
	@GetMapping("/readbygenre/{genre}")
	public ResponseEntity<ArrayList<Employee>> readbygenre(@PathVariable Character genre){
		return new ResponseEntity<ArrayList<Employee>>(eser.readByGenre(genre), HttpStatus.OK);
	}	
	
	@GetMapping("/readbyadults")
	public ResponseEntity<ArrayList<Employee>> readbyadults() {
		return new ResponseEntity<ArrayList<Employee>>(eser.readByAdults(), HttpStatus.OK);
	}
	
	@GetMapping("/readbyminors")
	public ResponseEntity<ArrayList<Employee>> readbyminors() {
		return new ResponseEntity<ArrayList<Employee>>(eser.readByMinors(), HttpStatus.OK);
	}	
}