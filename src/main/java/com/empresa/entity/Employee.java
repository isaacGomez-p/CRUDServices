package com.empresa.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table( name = "employee")
public class Employee implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name", nullable = false)
	@NotEmpty(message = "This is a required field")
	@Size(min = 2,  max = 10, message = "Name must have between 2 and 10 characters")
	//@ApiModelProperty(value = "Nombre del libro", required = true)
	private String name;
	
	@Column(name = "pnumber", nullable = false)
	@Digits(integer = 10, fraction = 0)
	private BigInteger pnumber;
	
	@Column(name = "genre", nullable = false)
	private Character genre;
	
	@Column(name = "state", nullable = false)
	private Boolean state;
	
	@Column(name = "birth", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss") 	
    private Calendar birth;
	
	@Column(name = "docnumber", nullable = false)
	private BigInteger docnumber;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@ManyToOne
	@JoinColumn(name = "id_department", nullable = false, foreignKey = @ForeignKey(name = "FK_department_employee"))
	private Department department;
	
	public Employee() {
		
	}
	
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department deparment) {
		this.department = deparment;
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

	public BigInteger getPnumber() {
		return pnumber;
	}

	public void setPnumber(BigInteger pnumber) {
		this.pnumber = pnumber;
	}

	public Character getGenre() {
		return genre;
	}

	public void setGenre(Character genre) {
		this.genre = genre;
	}

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	public Calendar getBirth() {
		return birth;
	}

	public void setBirth(Calendar birth) {
		this.birth = birth;
	}
	
	public BigInteger getDocnumber() {
		return docnumber;
	}

	public void setDocnumber(BigInteger docnumber) {
		this.docnumber = docnumber;
	}

}