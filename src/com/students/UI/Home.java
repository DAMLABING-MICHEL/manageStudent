package com.students.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.students.model.Student;
import com.students.service.StudentService;


public class Home extends JFrame{
	private Container container;
	private JMenuBar menuBar = new JMenuBar();
	private JMenu manage = new JMenu("Gestion");
	private JMenuItem student = new JMenuItem("Etudiant");
	private JMenuItem exit = new JMenuItem("Quitter");
	private JLabel lbHome = new JLabel("GesEtudiants");
	private JDesktopPane desktop;
	private StudentService studentService = new StudentService();
	private StudentForm sf = new StudentForm();
	public Home() {
		this.setTitle("Gestion des étudiants");
		this.setSize(1200,750);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Font font = new Font("Arial",Font.BOLD,60);
		lbHome.setFont(font);
		lbHome.setHorizontalAlignment(JLabel.CENTER);
		lbHome.setVerticalAlignment(JLabel.CENTER);
//		container.add(lbHome);
		container = this.getContentPane();
		container.add(lbHome);
		initMenu();
	}
	public void initMenu() {
		student.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				container.removeAll();
				desktop = new JDesktopPane();
				desktop.add(new ManageStudent());
				container.add(desktop);
				container.revalidate();
				container.repaint();
//				desktop.add(new ManageStudent());
//				lbHome.setText("");
			}
		});
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
//		this.getContentPane().add(desktop);
		manage.add(student);
		menuBar.add(manage);
		exit.setMnemonic('x');
		manage.add(exit);
		this.setJMenuBar(menuBar);
	}
	private class ManageStudent extends JInternalFrame{
		private JPanel manageContainer = new JPanel();
		private JPanel panSearch = new JPanel();
		private JMenuBar IMenuBar = new JMenuBar();
		private Panneau pan = new Panneau();
		private JPanel panHome = new JPanel();
		private JPanel panTitle = new JPanel();
		private JTabbedPane onglet = new JTabbedPane();
		private JLabel searchLabel = new JLabel("Rechercher");
		private JList<String> list = new JList<String>();
		private JTextField searchField = new JTextField();
		private JPanel panTable = new JPanel();
		private JPanel panContentTable = new JPanel();
		private JPanel panButtons = new JPanel();
		private JPanel panUpdateButton = new JPanel();
		private JPanel panUpdateContent = new JPanel();
		private JPanel panData = new JPanel();
		private JPanel addFieldPan = new JPanel();
		private JPanel submitPan = new JPanel();
		private JLabel lbStudentList = new JLabel("liste des étudiants");
		private JButton addButton = new JButton("Ajouter");
		private JButton deleteButton = new JButton("Supprimer");
		private JButton updateButton = new JButton("Modifier");
		private HomeTableModel homeTableModel = new HomeTableModel();
		private StudentForm sfp = new StudentForm(panUpdateContent);
		private JTable table;
		public ManageStudent() {
			addInternalFrameListener(new InternalFrameListener() {
				
				@Override
				public void internalFrameOpened(InternalFrameEvent e) {}
				
				@Override
				public void internalFrameIconified(InternalFrameEvent e) {}
				
				@Override
				public void internalFrameDeiconified(InternalFrameEvent e) {}
				
				@Override
				public void internalFrameDeactivated(InternalFrameEvent e) {}
				
				@Override
				public void internalFrameClosing(InternalFrameEvent e) {}
				
				@Override
				public void internalFrameClosed(InternalFrameEvent e) {
					container.removeAll();
					container.add(lbHome);
					container.revalidate();
					container.repaint();
				}
				
				@Override
				public void internalFrameActivated(InternalFrameEvent e) {}
			});
			this.setSize(1190,750);
			this.setTitle("fenêtre étudiants");
			this.setClosable(true);
			this.setResizable(true);
			initComponent();
			this.setJMenuBar(IMenuBar);
//			initComponent();
		}
		public void initComponent() {
			//composants du menu interne
			IMenuBar.setLayout(new BorderLayout());
			searchField.setPreferredSize(new Dimension(200,20));
			panSearch.add(searchLabel);
			panSearch.add(searchField);
			list.setBackground(Color.red);
			panSearch.setBackground(Color.white);
			list.setBounds(100, 100, 200, 200);
			panSearch.add(list);
			IMenuBar.add(panSearch,BorderLayout.EAST);
			manageContainer.setLayout(new BorderLayout());
			panTable.setBackground(Color.white);
			panTitle.setPreferredSize(new Dimension(750,30));
			panTitle.setBackground(Color.white);
			panContentTable.setPreferredSize(new Dimension(750,475));
			panContentTable.setBackground(Color.white);
			panContentTable.setLayout(new BorderLayout());
			panButtons.setPreferredSize(new Dimension(750,40));
			panButtons.setBackground(Color.white);
			panData.setBackground(Color.white);
			table = new JTable(homeTableModel) {
				public TableCellEditor getCellEditor(int row, int column)
	            {
					int col = convertColumnIndexToModel(column);
					 if (col == 5){
		                    return new DefaultCellEditor(sf.getBranch());
		              }
					 else if(col == 4) {
						 return new DefaultCellEditor(sf.getSexList());
					 }
		                else
		                    return super.getCellEditor(row, column);
	            }
			};
			table.setRowHeight(30);
			table.setRowHeight(2, 50);
			table.addMouseListener(new MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent event) {
						showMessage();
						updateButton.setEnabled(true);
						deleteButton.setEnabled(true);
				}
			});
			TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
			table.setRowSorter(sorter);
			sorter.setSortable(3, false);
			sorter.setSortsOnUpdates(true);

	        searchField.getDocument().addDocumentListener(new DocumentListener()
	    {
	            @Override
	            public void insertUpdate(DocumentEvent e) {
	                String str = searchField.getText();
	                if (str.trim().length() == 0) {
	                    sorter.setRowFilter(null);
	                } else {
	                    //(?i) recherche insensible à la casse
	                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + str));
	                }
	            }
	            @Override
	            public void removeUpdate(DocumentEvent e) {
	                String str = searchField.getText();
	                if (str.trim().length() == 0) {
	                    sorter.setRowFilter(null);
	                } else {
	                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + str));
	                }
	            }
	            @Override
	            public void changedUpdate(DocumentEvent e) {}
	        });
			//table.setAutoCreateRowSorter(true);
			panContentTable.add(new JScrollPane(table));
			lbStudentList.setFont(new Font("Arial", Font.BOLD, 20));
			panTitle.add(lbStudentList);
			addButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					StudentForm studentForm = new StudentForm(null, "Informations Etudiant",null, true);
					boolean valid = false;
					if(studentForm.isSubmit()) {
						Student student =  new Student(studentForm.getRegistNumberField().getText(),
								studentForm.getNameField().getText(), 
								studentForm.getSurnameField().getText(),
								studentForm.getSex(),(String) studentForm.getBranch().getSelectedItem());
							for(Student std:studentService.findAll()) {
								if(!student.getRegistNumber().equals("")) {
									 if(std.getRegistNumber().equalsIgnoreCase(student.getRegistNumber())) {
										 System.out.println(student.getRegistNumber());
									 }
									 else if(!std.getRegistNumber().equalsIgnoreCase(student.getRegistNumber())) {
										 valid = true;
									 }
								}
							}
							if(valid == true) {
								if(studentService.create(student) != null) {
									homeTableModel.addStudent(student);
								}
							}
					}
				}
			});
			deleteButton.setEnabled(false);
			updateButton.setEnabled(false);
			updateButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					int select = table.getSelectedRow();
					int selectedRow = table.getRowSorter().convertRowIndexToModel(select);
					Student student = homeTableModel.getElementAt(selectedRow);
					StudentForm studentForm = new StudentForm(null, "Informations Etudiant",student, true);
					if(studentForm.isSubmit()) {
						if(	studentService.update(new Student((int) homeTableModel.getValueAt(selectedRow, 0),
								studentForm.getRegistNumberField().getText(),
								studentForm.getNameField().getText(),
								studentForm.getSurnameField().getText(),
								studentForm.getSex(),
								(String) studentForm.getBranch().getSelectedItem())) != null) {
							homeTableModel.updateStudent(selectedRow, studentService.findById((int) homeTableModel.getValueAt(selectedRow, 0)));
							sfp.getNameField().setText(studentForm.getNameField().getText());
							sfp.getSurnameField().setText(studentForm.getSurnameField().getText());
							if(studentForm.getMale().isSelected()) {
								sfp.getSexField().setText("Homme");
							}
							else if(studentForm.getFemale().isSelected()) {
								sfp.getSexField().setText("Femme");
							}
							sfp.getRegistNumberField().setText(studentForm.getRegistNumberField().getText());
							sfp.getBranchField().setText((String) studentForm.getBranch().getSelectedItem());
						}
					}
				}
			});
			deleteButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					int select = table.getSelectedRow();
					int selectedRow = table.getRowSorter().convertRowIndexToModel(select); 
					StudentInformations si = new StudentInformations(homeTableModel.getElementAt(selectedRow));
					JOptionPane jop = new JOptionPane();
					int option = JOptionPane.showConfirmDialog(null, si.toString(), "Etudiant à supprimer", 
							JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
					if(option == JOptionPane.OK_OPTION && studentService.delete(homeTableModel.getElementAt(selectedRow)) != null) {
						homeTableModel.removeStudent(selectedRow);
						clear();
						JOptionPane.showMessageDialog(null, "suppression réussie!", 
								"message de suppression", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			});
			panButtons.add(addButton);
			panButtons.add(updateButton);
			panButtons.add(deleteButton);
			panTable.add(panTitle,BorderLayout.NORTH);
			panTable.add(panContentTable,BorderLayout.CENTER);
			panTable.add(panButtons,BorderLayout.SOUTH);
			panUpdateButton.setPreferredSize(new Dimension(400,100));
			panUpdateButton.setLayout(new BorderLayout());
			sfp.updateComponent();
			panData.add(sfp.getContent());
			panHome.setLayout(new BorderLayout());
			panHome.add(panTable,BorderLayout.CENTER);
			panHome.add(panData,BorderLayout.EAST);
			panHome.setBackground(Color.blue);
			onglet.addTab("Accueil", panHome);
			this.manageContainer.add(onglet);
			manageContainer.setBackground(Color.white);
			this.setContentPane(manageContainer);
			this.setVisible(true);
		}
		public void showMessage() {
			int select = table.getSelectedRow();
			int selectedRow = table.getRowSorter().convertRowIndexToModel(select);
			sfp.getNameField().setText(studentService.findById((int) homeTableModel.getValueAt(selectedRow, 0)).getName());
			sfp.getSurnameField().setText(studentService.findById((int) homeTableModel.getValueAt(selectedRow, 0)).getSurname());
			sfp.getSexField().setText(studentService.findById((int) homeTableModel.getValueAt(selectedRow, 0)).getSex());;
			sfp.getRegistNumberField().setText(studentService.findById(
					(int) homeTableModel.getValueAt(selectedRow, 0)).getRegistNumber());
			sfp.getBranchField().setText(studentService.findById((int) homeTableModel.getValueAt(selectedRow, 0)).getBranch());
		}
		public void clear() {
			sfp.getNameField().setText("");
			sfp.getSurnameField().setText("");
			sfp.getRegistNumberField().setText("");
			sfp.getSexField().setText("");
			sfp.getBranchField().setText("");
		}
	}
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(Exception e) {
			e.printStackTrace();
		}
		Home home = new Home();
		home.setVisible(true);
	}
}
