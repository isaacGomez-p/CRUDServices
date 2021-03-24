package com.empresa.service.imp;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.entity.Department;
import com.empresa.entity.Employee;
import com.empresa.exception.ObjectAlreadyFoundHandler;
import com.empresa.exception.ObjectNotFoundExceptionHandler;
import com.empresa.repository.IEmployeeRepo;
import com.empresa.service.IEmployeeService;

@Service
public class EmployeeServiceImp implements IEmployeeService{

	@Autowired
	IEmployeeRepo irep;
		
	@Override
	public void create(Employee emp) {
		// TODO Auto-generated method stub
		if(irep.findByDocnumber(emp.getDocnumber()) != null) {
			throw new ObjectAlreadyFoundHandler("Employee with document "+ emp.getDocnumber() + " already registered!");
		}
		else {
			irep.save(emp);	
		}			
	}

	@Override
	public Employee readById(Integer id) {
		// TODO Auto-generated method stub
		if(!irep.findById(id).isPresent()) {
			throw new ObjectNotFoundExceptionHandler("Employee id not found ");
		}
		else {
			return irep.findById(id).get();
		}
	}

	@Override
	public void update(Employee emp) {
		// TODO Auto-generated method stub
		if(!irep.findById(emp.getId()).isPresent() ) {
			throw new ObjectNotFoundExceptionHandler(" Employee not found. "); 
		}
		else {
			if(irep.findByDocnumber(emp.getDocnumber()) == null) {
				irep.save(emp);
			}	
			else {
				if(irep.findByDocnumber(emp.getDocnumber()).getId() == emp.getId()) {								
					irep.save(emp);
				}
				else {
					throw new ObjectAlreadyFoundHandler("Employee with document "+ emp.getDocnumber() + " already registered.");
				}
			}
		}
		
	}

	@Override
	public ArrayList<Employee> read() {
		// TODO Auto-generated method stub
		ArrayList<Employee> eList = new ArrayList<Employee>();
		if( irep.findAll().isEmpty() ) {
			throw new ObjectNotFoundExceptionHandler("There are not employees added yet!");
		}
		else {
			eList = (ArrayList<Employee>) irep.findAll();
		}		
		return eList;		 
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		if(!irep.findById(id).isPresent()) {
			throw new ObjectNotFoundExceptionHandler("Employee id not found ");
		}
		else {
			irep.delete(irep.findById(id).get());
		}
	}

	@Override
	public ArrayList<Employee> readByDepartment(Department dep) {
		// TODO Auto-generated method stub
		if(irep.findByDepartment(dep) == null) {
			throw new ObjectNotFoundExceptionHandler(" This department does not exist! ");
		}
		else {
			if( irep.findByDepartment(dep).isEmpty()) {
				throw new ObjectNotFoundExceptionHandler(" There are not employees for this department. ");
			}
			else {
				return irep.findByDepartment(dep);
			}			 
		}		
	}

	@Override
	public ArrayList<Employee> readByActive(Boolean state) {
		// TODO Auto-generated method stub
		if(irep.findByState(state) == null) {
			throw new ObjectNotFoundExceptionHandler("There are not active employees");
		}
		else {
			if(irep.findByState(state).isEmpty()) {
				throw new ObjectNotFoundExceptionHandler("There are not active employees");
			}
			else {
				return irep.findByState(state);
			}			
		}
	}

	@Override
	public ArrayList<Employee> readByNotActive(Boolean state) {
		// TODO Auto-generated method stub
		if(irep.findByState(state) == null) {
			throw new ObjectNotFoundExceptionHandler("There are not non-active employees");
		}
		else {
			if(irep.findByState(state).isEmpty()) {
				throw new ObjectNotFoundExceptionHandler("There are not non-active employees");
			}
			else {
				return irep.findByState(state);
			}			
		}
	}

	@Override
	public ArrayList<Employee> readByGenre(Character genre) {
		// TODO Auto-generated method stub
		if(irep.findByGenre(genre) == null) {
			throw new ObjectNotFoundExceptionHandler("There is not register of the genre: " + genre);
		}
		else {
			if(irep.findByGenre(genre).isEmpty()) {
				throw new ObjectNotFoundExceptionHandler("There is not register of the genre: " + genre);
			}
			else {
				return irep.findByGenre(genre);
			}			
		}		
	}

	@Override
	public ArrayList<Employee> readByAdults() {
		if(irep.findAdults() == null) {
			throw new ObjectNotFoundExceptionHandler(" There are not adults registered ");
		}
		else {
			if(irep.findAdults().isEmpty()) {
				throw new ObjectNotFoundExceptionHandler(" There are not adults registered ");
			}
			else {
				return irep.findAdults();				
			}
		}
	}

	@Override
	public ArrayList<Employee> readByMinors() {
		// TODO Auto-generated method stub
		if(irep.findMinors() == null) {
			throw new ObjectNotFoundExceptionHandler(" There are not minors registered ");
		}
		else {
			if(irep.findMinors().isEmpty()) {
				throw new ObjectNotFoundExceptionHandler(" There are not minors registered ");
			}
			else {
				return irep.findMinors();
			}
		}
	}
}
