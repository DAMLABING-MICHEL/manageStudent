package com.students.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.students.model.Student;

public class StudentService extends IDao<Student> {
	private ArrayList<Student> studentsList = new ArrayList<Student>();
	@Override
	public ArrayList<Student> findAll() {
		Student student = new Student();
		try {
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
					ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT * FROM student");
			while(result.next()) {
				student = new Student(result.getInt("s_id"),result.getString("registNumber"),result.getString("name"),
						result.getString("surname"),result.getString("sex"),
						result.getString("branch"));
				studentsList.add(student);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return studentsList;
	}
	@Override
	public Student create(Student student) {
		int id = 0;
		try {
			String query = "INSERT INTO student(registNumber,name,surname,sex,branch) "
					+ "VALUES(?,?,?,?,?)";
			PreparedStatement prepare = this.connect.prepareStatement(query);
			prepare.setString(1, student.getRegistNumber());
			prepare.setString(2, student.getName());
			prepare.setString(3, student.getSurname());
			prepare.setString(4, student.getSex());
			prepare.setString(5, (String) student.getBranch());
			prepare.executeUpdate();
			ResultSet result = this.connect.createStatement().executeQuery("SELECT * FROM student ORDER BY s_id DESC LIMIT 1");
			while(result.next()) {
				id = result.getInt("s_id");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		student.setId(id);
		return student;
	}
	@Override
	public Student delete(Student student) {
		String query = "DELETE from student WHERE s_id =" + student.getId();
		try {
			this.connect.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)
			.executeUpdate(query);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return student;
	}
	@Override
	public Student findById(int id) {
		Student student = new Student();
		try {
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
					ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT * FROM student WHERE s_id =" +id);
			if(result.first()) {
				student = new Student(result.getInt("s_id"),result.getString("registNumber"),result.getString("name"),
						result.getString("surname"),result.getString("sex"),
						result.getString("branch"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return student;
	}
	@Override
	public Student update(Student student) {
		String query = "UPDATE student SET name = ?,registNumber = ?,surname = ?,sex = ?,branch = ? WHERE s_id = ?";
		try {
			PreparedStatement prepare = this.connect.prepareStatement(query);
			prepare.setString(1, student.getName());
			prepare.setString(2, student.getRegistNumber());
			prepare.setString(3, student.getSurname());
			prepare.setString(4, student.getSex());
			prepare.setString(5, (String) student.getBranch());
			prepare.setInt(6, student.getId());
			prepare.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return student;
	}
	public boolean updateRow(StudentTableField name,String value,int id) {
		String querry = "UPDATE student SET " +name + " = ? " +" WHERE s_id = " +id;
		try {
			PreparedStatement prepare = this.connect.prepareStatement(querry);
			prepare.setString(1, value);
			prepare.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return true;
	}
}
