package hr.softeng.com;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class hr_jframe extends JFrame {

	
	public hr_jframe() {
		hrjframe();
	}
	
	
	private JPanel contentPane;
	private JTextField create_class_textField;
	private JTextField create_staff_name_textField;
	private JTextField create_staff_surname_textField;
	private JTextField create_staff_class_textField;
	private JTextField set_class_textField;
	private JTextField del_class_textField;
	private JTextField del_staff_class_textField;
	private JTextField del_staff_surname_textField;
	private JTextField del_staff_name_textField;
	private HRModel model; 
	private JTextField set_new_class_textField;

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					hr_jframe frame = new hr_jframe();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/
	/**
	 * Create the frame.
	 */
	private void hrjframe() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 458, 349);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Δημιουργία", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Τμήματος");
		lblNewLabel.setBounds(158, 12, 103, 15);
		panel.add(lblNewLabel);
		
		create_class_textField = new JTextField();
		create_class_textField.setBounds(242, 32, 114, 19);
		panel.add(create_class_textField);
		create_class_textField.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.createTable(create_class_textField.getText());
			}
		});
		btnSubmit.setBounds(242, 63, 114, 25);
		panel.add(btnSubmit);
		
		JLabel label = new JLabel("Υπαλλήλου");
		label.setBounds(152, 108, 94, 15);
		panel.add(label);
		
		create_staff_name_textField = new JTextField();
		create_staff_name_textField.setColumns(10);
		create_staff_name_textField.setBounds(242, 135, 114, 19);
		panel.add(create_staff_name_textField);
		
		
		JButton staff_btnSubmit = new JButton("Submit");
		staff_btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name,surname,tname;
				name = create_staff_name_textField.getText();
				surname = create_staff_surname_textField.getText();
				tname = create_staff_class_textField.getText();
				model.addPerson(tname, name, surname);
			}
		});
		staff_btnSubmit.setBounds(242, 235, 114, 25);
		panel.add(staff_btnSubmit);
		
		JLabel label_1 = new JLabel("Όνομα Τμήματος");
		label_1.setBounds(73, 34, 131, 15);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("Όνομα");
		label_2.setBounds(73, 137, 70, 15);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("Επίθετο");
		label_3.setBounds(73, 166, 70, 15);
		panel.add(label_3);
		
		create_staff_surname_textField = new JTextField();
		create_staff_surname_textField.setColumns(10);
		create_staff_surname_textField.setBounds(242, 164, 114, 19);
		panel.add(create_staff_surname_textField);
		
		JLabel label_4 = new JLabel("Τμήμα");
		label_4.setBounds(73, 197, 70, 15);
		panel.add(label_4);
		
		create_staff_class_textField = new JTextField();
		create_staff_class_textField.setColumns(10);
		create_staff_class_textField.setBounds(242, 195, 114, 19);
		panel.add(create_staff_class_textField);
		
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Επεξεργασία", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel label_5 = new JLabel("Τμήματος");
		label_5.setBounds(158, 17, 103, 15);
		panel_1.add(label_5);
		
		JLabel label_6 = new JLabel("Όνομα Τμήματος");
		label_6.setBounds(73, 39, 151, 15);
		panel_1.add(label_6);
		
		set_class_textField = new JTextField();
		set_class_textField.setColumns(10);
		set_class_textField.setBounds(245, 39, 114, 19);
		panel_1.add(set_class_textField);
		
		JButton set_class_btnSubmit = new JButton("Submit");
		set_class_btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String old, newname;
				old = set_class_textField.getText();
				newname = set_new_class_textField.getName();
				model.changeTable(old, newname);
			}
		});
		set_class_btnSubmit.setBounds(246, 129, 114, 25);
		panel_1.add(set_class_btnSubmit);
		
		JLabel lblNewLabel_1 = new JLabel("Νέο Όνομα Τμήματος");
		lblNewLabel_1.setBounds(73, 77, 151, 15);
		panel_1.add(lblNewLabel_1);
		
		set_new_class_textField = new JTextField();
		set_new_class_textField.setColumns(10);
		set_new_class_textField.setBounds(245, 72, 114, 19);
		panel_1.add(set_new_class_textField);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Διαγραφή", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel label_11 = new JLabel("Τμήματος");
		label_11.setBounds(164, 12, 103, 15);
		panel_2.add(label_11);
		
		JLabel label_12 = new JLabel("Όνομα Τμήματος");
		label_12.setBounds(79, 34, 131, 15);
		panel_2.add(label_12);
		
		del_class_textField = new JTextField();
		del_class_textField.setColumns(10);
		del_class_textField.setBounds(248, 32, 114, 19);
		panel_2.add(del_class_textField);
		
		JButton del_class_btnSubmit = new JButton("Submit");
		del_class_btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.deleteTable(del_class_textField.getText());
			}
		});
		del_class_btnSubmit.setBounds(248, 63, 114, 25);
		panel_2.add(del_class_btnSubmit);
		
		JLabel label_13 = new JLabel("Υπαλλήλου");
		label_13.setBounds(158, 108, 94, 15);
		panel_2.add(label_13);
		
		JLabel label_14 = new JLabel("Όνομα");
		label_14.setBounds(79, 137, 70, 15);
		panel_2.add(label_14);
		
		JLabel label_15 = new JLabel("Επίθετο");
		label_15.setBounds(79, 164, 70, 15);
		panel_2.add(label_15);
		
		JLabel label_16 = new JLabel("Τμήμα");
		label_16.setBounds(79, 191, 70, 15);
		panel_2.add(label_16);
		
		JButton del_staff_btnSubmit = new JButton("Submit");
		del_staff_btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name,surname,tname;
				name = del_staff_name_textField.getText();
				surname = del_staff_surname_textField.getText();
				tname = del_staff_class_textField.getText();
				model.deletePerson(tname, name, surname);
			}
		});
		del_staff_btnSubmit.setBounds(248, 235, 114, 25);
		panel_2.add(del_staff_btnSubmit);
		
		del_staff_class_textField = new JTextField();
		del_staff_class_textField.setColumns(10);
		del_staff_class_textField.setBounds(248, 192, 114, 19);
		panel_2.add(del_staff_class_textField);
		
		del_staff_surname_textField = new JTextField();
		del_staff_surname_textField.setColumns(10);
		del_staff_surname_textField.setBounds(248, 164, 114, 19);
		panel_2.add(del_staff_surname_textField);
		
		del_staff_name_textField = new JTextField();
		del_staff_name_textField.setColumns(10);
		del_staff_name_textField.setBounds(248, 135, 114, 19);
		panel_2.add(del_staff_name_textField);
		
	}
	/*
	public String getID()
	{
		int x;
		
		try{
			x = Integer.parseInt(id_textField.getText());
			System.out.println(x);
			return x;
		}catch (NumberFormatException ex){
			System.err.println("Value parsed error ");
			return 0;
			
		}
		
	}
	
	public String getTname()
	{
		try{
			return class_textField.getText();
		}catch (NumberFormatException ex){
			System.err.println("Ilegal input ");
			return null;
		}
		
	}

	public double getPoints()
	{
		try{
			return Double.parseDouble(points_textField.getText());
		}catch (NumberFormatException ex){
			System.err.println("Value parsed error ");
			return 0;
		}
		
	}
	
	*/
}
