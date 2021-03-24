package com.empresa.service.imp;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.entity.Department;
import com.empresa.exception.ObjectAlreadyFoundHandler;
import com.empresa.exception.ObjectNotFoundExceptionHandler;
import com.empresa.repository.IDepartmentRepo;
import com.empresa.service.IDepartmentService;

@Service
public class DepartmentServiceImp implements IDepartmentService {

	@Autowired
	private IDepartmentRepo irep;

	@Override
	public void create(Department dep) {
		if(irep.findByNameIgnoreCase(dep.getName()) != null) {
			throw new ObjectAlreadyFoundHandler("Department name "+ dep.getName() + " is already registered!");
		}
		else {
			irep.save(dep);	
		}	
	}

	@Override
	public Department readById(Integer id) {
		// TODO Auto-generated method stub
		if(irep.findById(id) == null) {
			throw new ObjectNotFoundExceptionHandler(" Department not found. ");
		}
		else {
			return irep.findById(id).get();
		}
	}

	@Override
	public void update(Department dep) {
		// TODO Auto-generated method stub
		if(irep.findById(dep.getId()) == null ) {
			throw new ObjectNotFoundExceptionHandler(" Department not found. "); 
		}
		else {
			if(irep.findByNameIgnoreCase(dep.getName()) == null) {
				Department dit = irep.findById(dep.getId()).get();
				dit.setName(dep.getName());
				dit.setAddress(dep.getAddress());
				dit.setPnumber(dep.getPnumber());
				//dit.setEmpList(dep.getEmpList());				
				irep.save(dit);
			}
			else {
				if(irep.findByNameIgnoreCase(dep.getName()).getId() == dep.getId()) {
					Department dit = irep.findById(dep.getId()).get();
					dit.setName(dep.getName());
					dit.setAddress(dep.getAddress());
					dit.setPnumber(dep.getPnumber());
					//dit.setEmpList(dep.getEmpList());				
					irep.save(dit);
				}
				else {
					throw new ObjectAlreadyFoundHandler("Deparment name "+ dep.getName() + " already registered.");
				}	
			}					
		}
	}

	@Override
	public ArrayList<Department> read() {
		// TODO Auto-generated method stub
		ArrayList<Department> depList = new ArrayList<Department>();
		if( irep.findAll().isEmpty() ) {
			throw new ObjectNotFoundExceptionHandler("There are not departments added yet!");
		}
		else {
			depList = (ArrayList<Department>) irep.findAll();
		}		
		return depList;		 
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		if(!irep.findById(id).isPresent()) {
			throw new ObjectNotFoundExceptionHandler("Department id not found ");
		}
		else {
			irep.delete(irep.findById(id).get());
		}		
	}
}
