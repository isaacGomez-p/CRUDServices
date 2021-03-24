package com.empresa.service;

import java.util.ArrayList;

import com.empresa.entity.Department;

public interface IDepartmentService extends AbstractServiceCRUD<Department, Department, Integer>{
	
	public void create(Department dep);
	
	public Department readById(Integer id);
	
	public void update(Department classs);
	
	public ArrayList<Department> read();
	
	public void delete(Integer id);


}