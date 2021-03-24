package com.empresa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.empresa.entity.Department;

@Repository
public interface IDepartmentRepo extends JpaRepository<Department, Integer>{	
	
	Department findByNameIgnoreCase(String name);
}
