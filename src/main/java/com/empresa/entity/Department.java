package com.empresa.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table( name = "department")
public class Department implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//@ApiModelProperty(value = "ID del libro", required = true)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name", nullable = false)
	@NotEmpty(message = "Name is a required field")
	@Size(min = 2,  max = 10, message = "Name must have between 2 and 10 characters")
	//@ApiModelProperty(value = "Nombre del libro", required = true)
	private String name;
	
	@Column(name = "address", nullable = false)
	@NotEmpty(message = "Address is a required field")
	@Size(min = 2, max = 30, message = "Address must have between 2 and 10 characters")
	private String address;
	
	@Column(name = "pnumber", nullable = false)
	private BigInteger pnumber;
	
	@OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Employee> empList;	

	public Department() {
		
	}	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigInteger getPnumber() {
		return pnumber;
	}

	public void setPnumber(BigInteger pnumber) {
		this.pnumber = pnumber;
	}
	
	@JsonIgnore
	public List<Employee> getEmpList() {
		return empList;
	}

	public void setEmpList(List<Employee> empList) {
		this.empList = empList;
	}
		

}
