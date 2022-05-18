package com.students.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.students.model.Student;


public class StudentForm extends JDialog{
	private JPanel content = new JPanel();
	private JPanel panRegistNumber = new JPanel();
	private JPanel panName = new JPanel();
	private JPanel panSurname = new JPanel();
	private JPanel panSex = new JPanel();
	private JPanel panBranch = new JPanel();
	private JPanel control = new JPanel();
	private JLabel registNumberLabel = new JLabel("matricule");
	private JLabel nameLabel = new JLabel("nom :");
	private JLabel surnameLabel = new JLabel("prénom :");
	private JLabel sexeLabel = new JLabel("sexe :");
	private JLabel branchLabel = new JLabel("Filière :");
	private JTextField nameField = new JTextField();
	private JTextField surnameField = new JTextField();
	private JTextField registNumberField = new JTextField();
	private JTextField sexField,branchField;
	private JRadioButton female = new JRadioButton("Feminin");
	private JRadioButton male = new JRadioButton("Masculin");
	private JComboBox<String> branch = new JComboBox<String>();
	private JComboBox<String> sexList = new JComboBox<String>();
	private JButton okButton = new JButton("ok");
	private JButton reset = new JButton("Annuler");
	private boolean submit = false;
	private boolean update = false;
	private String name;
	HomeTableModel htm = new HomeTableModel();
	public StudentForm(JPanel pan) {}
	public StudentForm() {
		initComponent();
	}
	public StudentForm(JFrame parent,String title,Student student,boolean modal) {
		super(parent,title,true);
		this.setSize(550, 300);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		if(student != null) {
			nameField.setText(student.getName());
			surnameField.setText(student.getSurname());
			if(student.getSex().equals("Homme")) {
				male.setSelected(true);
			}
			else if(student.getSex().equals("Femme")) {
				female.setSelected(true);
			}
			branch.setSelectedItem(student.getBranch());
			registNumberField.setText(student.getRegistNumber());
		}
		initComponent();
		this.setVisible(true);
	}
	public void initComponent() {
		sexList.addItem("Homme");
		sexList.addItem("Femme");
		sexList.addItem("Inconnu");
		content.setBackground(Color.white);
		//nom
		panName.setBorder(BorderFactory.createTitledBorder("Nom Etudiant"));
		panName.setBackground(Color.white);
		panName.setPreferredSize(new Dimension(220, 60));
		nameField.setPreferredSize(new Dimension(100,25));
		panName.setBorder(BorderFactory.createTitledBorder("Nom Etudiant"));
		//ajout du label nom
		panName.add(nameLabel);
		panName.add(nameField);
		content.add(panName);
		//prenom
		panSurname.setBackground(Color.white);
		panSurname.setPreferredSize(new Dimension(220, 60));
		surnameField.setPreferredSize(new Dimension(100,25));
		panSurname.setBorder(BorderFactory.createTitledBorder("Prénom Etudiant"));
		//ajout du label prénom
		panSurname.add(surnameLabel);
		panSurname.add(surnameField);
		content.add(panSurname);
		//sexe
		panSex.setBackground(Color.white);
		panSex.setPreferredSize(new Dimension(440, 50));
		panSex.setBorder(BorderFactory.createTitledBorder("Sexe Etudiant"));
		ButtonGroup bg = new ButtonGroup();
		bg.add(female);
		bg.add(male);
		
		panSex.add(female);
		panSex.add(male);
		content.add(panSex);
		//filière
		panBranch.setBackground(Color.white);
		panBranch.setPreferredSize(new Dimension(220, 60));
		panBranch.setBorder(BorderFactory.createTitledBorder("Filière Etudiant"));
		branch.setPreferredSize(new Dimension(90,25));
		branch.addItem("reseau");
		branch.addItem("GL");
		branch.addItem("medecine");
		branch.addItem("comptabilié");
		branch.addItem("maintenance");
//		ajout du label filière
		panBranch.add(branchLabel);
		panBranch.add(branch);
		content.add(panBranch);
		//matricule
		panRegistNumber.setBackground(Color.white);
		panRegistNumber.setPreferredSize(new Dimension(240, 60));
		registNumberField.setPreferredSize(new Dimension(100,25));
		panRegistNumber.setBorder(BorderFactory.createTitledBorder("Matricule Etudiant"));
		//ajout du label matricule
		panRegistNumber.add(registNumberLabel);
		panRegistNumber.add(registNumberField);
		content.add(panRegistNumber);
		//boutons de contrôle
		okButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				submit = true;
				setVisible(false);
			}
		});
		JButton cancelButton = new JButton("annuler");
		cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		control.add(okButton);
		control.add(cancelButton);
		this.getContentPane().add(content,BorderLayout.CENTER);
		this.getContentPane().add(control,BorderLayout.SOUTH);
	}
	public void updateComponent() {
		JLabel lbInfos = new JLabel("informations Etudiant");
		lbInfos.setHorizontalAlignment(JLabel.CENTER);
		lbInfos.setVerticalAlignment(JLabel.CENTER);
		control.setBackground(Color.white);
		content.setPreferredSize(new Dimension(400,500));
		content.setBorder(BorderFactory.createLineBorder(Color.black));
		GridLayout gl = new GridLayout(7,1);
		gl.setVgap(5);
		content.setLayout(gl);
		content.setBackground(Color.white);
		//label titre
		content.add(lbInfos);
		//nom
		panName.setBackground(Color.white);
		nameField = new JTextField();
		nameLabel.setPreferredSize(new Dimension(100,40));
		nameField.setPreferredSize(new Dimension(250,40));
		nameField.setBorder(BorderFactory.createLineBorder(Color.white));
		//ajout du label nom
		panName.add(nameLabel);
		panName.add(nameField);
		content.add(panName);
		//prenom
		panSurname.setBackground(Color.white);
		surnameLabel.setPreferredSize(new Dimension(100,40));
		surnameField = new JTextField();
		surnameField.setPreferredSize(new Dimension(250,40));
		surnameField.setBorder(BorderFactory.createLineBorder(Color.white));
		//ajout du label prénom
		panSurname.add(surnameLabel);
		panSurname.add(surnameField);
		content.add(panSurname);
		//sexe
		sexField = new JTextField();
		sexeLabel.setPreferredSize(new Dimension(100,40));
		panSex.setBackground(Color.white);
		sexField.setPreferredSize(new Dimension(250, 40));
		sexField.setBorder(BorderFactory.createLineBorder(Color.white));
		panSex.add(sexeLabel);
		panSex.add(sexField);
		content.add(panSex);
		//filière
		branchField = new JTextField();
		panBranch.setBackground(Color.white);
		branchLabel.setPreferredSize(new Dimension(100, 40));
		branchField.setPreferredSize(new Dimension(250, 40));
		branchField.setBorder(BorderFactory.createLineBorder(Color.white));
//		ajout du label filière
		panBranch.add(branchLabel);
		panBranch.add(branchField);
		content.add(panBranch);
		//matricule
		panRegistNumber.setBackground(Color.white);
		registNumberLabel.setPreferredSize(new Dimension(100, 40));
		registNumberField = new JTextField();
		registNumberField.setPreferredSize(new Dimension(250,40));
		registNumberField.setBorder(BorderFactory.createLineBorder(Color.white));
		//ajout du label matricule
		panRegistNumber.add(registNumberLabel);
		panRegistNumber.add(registNumberField);
		content.add(panRegistNumber);
		content.add(control);
	}
	public JPanel getContent() {
		return this.content;
	}
	public JPanel getControl() {
		return this.control;
	}
	public String getSex() {
		String sexValue;
		sexValue = (male.isSelected()) ? "Homme" : (female.isSelected()) ? "Femme" : "Inconnu";
		return sexValue;
	}
	public boolean isSubmit() {
		return submit;
	}
	public JTextField getNameField() {
		return nameField;
	}
	public JTextField getRegistNumberField() {
		return registNumberField;
	}
	public JTextField getSurnameField() {
		return surnameField;
	}
	public JComboBox<String> getBranch() {
		return branch;
	}
	public JComboBox<String> getSexList() {
		return sexList;
	}
	public JRadioButton getMale() {
		return this.male;
	}
	public JRadioButton getFemale() {
		return this.female;
	}
	public JTextField getBranchField() {
		return branchField;
	}
	public JTextField getSexField() {
		return sexField;
	}
}
