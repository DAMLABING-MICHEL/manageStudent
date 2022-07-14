package com.students.UI;

import org.apache.poi.ss.usermodel.Sheet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JTable;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {
	public void exportTableToExcel(JTable table,File exportFile) throws IOException {
		
				FileOutputStream output = new FileOutputStream(exportFile);
				//Creer un document vide
				XSSFWorkbook wb = new XSSFWorkbook();
				//Créer une feuille de calcul vide
				Sheet sheet = wb.createSheet("new sheet");
				for(int i=0;i<table.getRowCount();i++) {
					//Créer une ligne et y inserer les données
					Row row = sheet.createRow((short)i);
					for(int j=0;j<table.getColumnCount();j++) {
						//Créer une nouvelle cellule et y inserer une valeur
						Cell cell = row.createCell(j);
						cell.setCellValue(table.getValueAt(i, j).toString());
					}
				}
					wb.write(output);
					output.flush();
					output.close();
	}
}
