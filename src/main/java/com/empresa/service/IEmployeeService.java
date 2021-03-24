package com.empresa.service;

import java.util.ArrayList;

import com.empresa.entity.Department;
import com.empresa.entity.Employee;

public interface IEmployeeService extends AbstractServiceCRUD<Employee, Employee, Integer>{
	
	public void create(Employee emp);
	
	public Employee readById(Integer id);
	
	public void update(Employee classDTO);
	
	public ArrayList<Employee> read();
	
	public void delete(Integer id);
	
	public ArrayList<Employee> readByDepartment(Department dep);
	
	public ArrayList<Employee> readByActive(Boolean state);
	
	public ArrayList<Employee> readByNotActive(Boolean state);
	
	public ArrayList<Employee> readByGenre(Character genre);
	
	public ArrayList<Employee> readByAdults();
	
	public ArrayList<Employee> readByMinors(); 
	
}
