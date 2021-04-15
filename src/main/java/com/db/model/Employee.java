package com.db.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Entity
public class Employee {

	@Id
	@GeneratedValue
	private int id;

	@NotBlank(message = "Name is mandatory")
	private String name;

	@Positive(message = "Age should be positive")
	private int age;

	private String email;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return String.format("Employee [id=%d, name=%s, age=%d, email=%s]", id, name, age, email);
	}

}