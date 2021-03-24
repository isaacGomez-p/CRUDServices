package com.empresa.repository;

import java.math.BigInteger;
import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.empresa.entity.Department;
import com.empresa.entity.Employee;

@Repository
public interface IEmployeeRepo extends JpaRepository<Employee, Integer>{

	Employee findByNameIgnoreCase(String name);
	
	Employee findByDocnumber(BigInteger num);
	
	ArrayList<Employee> findByDepartment(Department dep);
	
	ArrayList<Employee> findByState( Boolean state );
	
	ArrayList<Employee> findByGenre( Character genre );
	
	@Query( value = "SELECT * FROM employee WHERE EXTRACT(YEAR FROM age(now() ::::timestamp, birth )) > 18", nativeQuery = true)
	ArrayList<Employee> findAdults();
	
	@Query( value = "SELECT * FROM Employee WHERE NOT (EXTRACT(YEAR FROM age(now() ::::timestamp, birth )) > 18)", nativeQuery = true)
	ArrayList<Employee> findMinors();

}
