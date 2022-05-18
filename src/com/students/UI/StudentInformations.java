package com.students.UI;

import com.students.model.Student;

public class StudentInformations {
	private String str,name,surname,sex,registNumber,branch;
	public StudentInformations(Student student) {
		this.registNumber = student.getRegistNumber();
		this.name = student.getName();
		this.surname = student.getSurname();
		this.sex = student.getSex();
		this.branch = student.getBranch();
	}
	@Override
	public String toString() {
		str = "Voulez-vous supprimer l'etudiant suivant? \n"+
				" Matricule : " +registNumber + "\n" + 
				" Nom : " +name + "\n" + 
				" Pr�nom : " +surname +"\n" +
				" Sexe : " +sex +"\n" + 
				" Fili�re : " +this.branch;
		return str;
	}
}
