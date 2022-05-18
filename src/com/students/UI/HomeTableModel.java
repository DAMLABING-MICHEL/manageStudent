package com.students.UI;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import javax.swing.table.AbstractTableModel;

import com.students.model.Student;
import com.students.service.StudentService;
import com.students.service.StudentTableField;

public class HomeTableModel extends AbstractTableModel{
		private String[] headlines = {"ID","matricule","nom","prenom","Sex","filière"};
		private ArrayList<Student> students;
		private StudentService studentService = new StudentService();
		public HomeTableModel() {
			this.students = new ArrayList<Student>();
			for(Student student:studentService.findAll()) {
				students.add(student);
			}
		}
		@Override
		public int getRowCount() {
			return students.size();
		}
		public boolean isCellEditable(int row, int col ) {
			if(col == 0) {
				return false;
			}
			return true;
		}
		@Override
		public int getColumnCount() {
			return headlines.length;
		}
		public String getColumnName(int columnIndex) {
			return headlines[columnIndex];
		}
		public void addStudent(Student student) {
			students.add(student);
			fireTableRowsInserted(students.size()-1, students.size()-1);
		}
		public void updateStudent(int rowIndex,Student student) {
			for(int i=0;i<students.size();i++) {
				if(students.get(i).getId() == student.getId()) {
					students.set(i, student);
				}
			}
			fireTableRowsUpdated(rowIndex, rowIndex);
		}
		public void removeStudent(int rowIndex) {
			students.remove(rowIndex);
			fireTableRowsDeleted(rowIndex, rowIndex);
		}
		public Student getElementAt(int rowIndex) {
			return students.get(rowIndex);
		}
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			switch (columnIndex){
				case 0:
					return students.get(rowIndex).getId();
				case 1:
					return students.get(rowIndex).getRegistNumber();
				case 2:
					return students.get(rowIndex).getName();
				case 3:
					return students.get(rowIndex).getSurname();
				case 4:
					return students.get(rowIndex).getSex();
				case 5:
					return students.get(rowIndex).getBranch();
				default:
					return null;
			}
		}
		@Override
		public void setValueAt(Object aValue,int rowIndex,int columnIndex) {
			if(aValue != null) {
				Student student = students.get(rowIndex);
				switch (columnIndex) {
				case 0:
					student.setId((int) getValueAt(rowIndex, 0));
				case 1:
					student.setRegistNumber((String) aValue);
					studentService.updateRow(StudentTableField.REGISTNUMBER, (String) aValue, student.getId());
					break;
				case 2:
					student.setName((String) aValue);
					studentService.updateRow(StudentTableField.NAME, (String) aValue, student.getId());
					break;
				case 3:
					student.setSurname((String) aValue);
					studentService.updateRow(StudentTableField.SURNAME, (String) aValue, student.getId());
					break;
				case 4:
						student.setSex((String) aValue);
						studentService.updateRow(StudentTableField.SEX, (String) aValue, student.getId());
					break;
				case 5:
					student.setBranch((String) aValue);
					studentService.updateRow(StudentTableField.BRANCH, (String) aValue, student.getId());
					break;
				default:
					throw new IllegalArgumentException("Unexpected value: " + columnIndex);
				}
			}
		}
}
