package hr.softeng.com;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTabbedPane;

import java.awt.CardLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;




public class jframe extends JFrame {
	
	public jframe() {
		frame();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField id_textField;
	private JTextField points_textField;
	private JTextField class_textField;
	JButton btnSubmit= new JButton("Submit");
	private HRModel model; 
	Connection con = null;
	private JTable table;
	
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					jframe frame = new jframe();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	
	
	private void frame() {
		
		// connect with the database
		// model.dbconnect();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 482, 283);
		getContentPane().setLayout(new CardLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, "name_16536561390079");
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Αξιολόγηση", null, panel, null);
		panel.setLayout(null);
		
		JLabel label_1 = new JLabel("ID Υπαλλήλου");
		label_1.setBounds(12, 29, 195, 24);
		panel.add(label_1);
		
		id_textField = new JTextField();
		id_textField.setToolTipText("");
		id_textField.setBounds(248, 30, 212, 24);
		id_textField.setColumns(10);
		panel.add(id_textField);
		
		JLabel label_2 = new JLabel("Βαθμός Αξιολόγησης");
		label_2.setBounds(12, 58, 195, 24);
		panel.add(label_2);
		
		points_textField = new JTextField();
		points_textField.setBounds(248, 59, 212, 24);
		points_textField.setColumns(10);
		panel.add(points_textField);
		
		JLabel lblNewLabel = new JLabel("Όνομα Τμήματος");
		lblNewLabel.setBounds(12, 94, 195, 15);
		panel.add(lblNewLabel);
		
		class_textField = new JTextField();
		class_textField.setBounds(248, 90, 212, 24);
		class_textField.setColumns(10);
		panel.add(class_textField);
		
		JLabel wrong_input_label = new JLabel("");
		wrong_input_label.setBounds(173, 168, 134, 15);
		panel.add(wrong_input_label);
		
		
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				int x = getID();
				String str = getTname();
				double poin = getPoints();
				if ( x != 0 && str != null && poin !=0){
					wrong_input_label.setText("update. . .");
					model.givePoints(str,x,poin);
					
				}else{
					wrong_input_label.setText("wrong input");
				}
				
				
			}
		});
		btnSubmit.setBounds(16, 163, 117, 25);
		panel.add(btnSubmit);
		
		JButton exit_button = new JButton("Exit");
		exit_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		exit_button.setBounds(343, 163, 117, 25);
		panel.add(exit_button);
		
		
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Show_Table", null, panel_1, null);
		panel_1.setLayout(null);
		
		table = new JTable();
		table.setBounds(12, 39, 451, 180);
		panel_1.add(table);
		
		JButton btnNewButton = new JButton("Load Data");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					
					String query = "select * from test";
					PreparedStatement pst = con.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					
				}catch (Exception ex){
					ex.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(186, 12, 117, 25);
		panel_1.add(btnNewButton);
		
	}

	public int getID()
	{
		int x;
		
		try{
			x = Integer.parseInt(id_textField.getText());
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
}
