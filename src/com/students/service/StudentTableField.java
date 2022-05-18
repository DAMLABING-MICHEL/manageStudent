package com.students.service;

public enum StudentTableField {
	ID("s_id"),
	REGISTNUMBER("registNumber"),
	NAME("name"),
	SURNAME("surname"),
	SEX("sex"),
	BRANCH("branch");
	private String name;
	StudentTableField(String name){
		this.name = name;
	}
	public String toString() {
		return this.name;
	}
}
