package com.students.model;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;

public class Student {
	private String string = "";
	private int id;
	private String name,surname,sex,branch,registNumber;
	public Student() {}
	public Student(int id,String registNumber,String name,String surname,String sex,String branch) {
		this.id = id;
		this.registNumber = registNumber;
		this.name = name;
		this.surname = surname;
		this.sex = sex;
		this.branch = branch;
	}
	public Student(String registNumber,String name,String surname,String sex,String branch) {
		this.name = name;
		this.registNumber = registNumber;
		this.surname = surname;
		this.sex = sex;
		this.branch = branch;
	}
	
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRegistNumber() {
		return this.registNumber;
	}
	public void setRegistNumber(String registNumber) {
		this.registNumber = registNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
}

