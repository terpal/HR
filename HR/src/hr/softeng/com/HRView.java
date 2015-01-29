package hr.softeng.com;

//import java.awt.EventQueue;

import java.util.Observer;
import javax.swing.JFrame;
//import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Observable;
//import java.util.Observer;

public class HRView extends JFrame implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JFrame frame;
	private JTextField username_textField;
	private JPasswordField passwordField;
	JButton btnLogin;
	private JButton btnCancel;
	private JLabel wrong_label;

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HRView window = new HRView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/
	/**
	 * Create the application.
	 */
	public HRView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 437, 273);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel username_label = new JLabel("Username");
		username_label.setBounds(53, 33, 130, 27);
		frame.getContentPane().add(username_label);
		
		JLabel pass_label = new JLabel("Password");
		pass_label.setBounds(53, 75, 130, 27);
		frame.getContentPane().add(pass_label);
		
		username_textField = new JTextField();
		username_textField.setBounds(211, 33, 174, 27);
		frame.getContentPane().add(username_textField);
		username_textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(211, 75, 174, 27);
		frame.getContentPane().add(passwordField);
		
		btnLogin = new JButton("Login");
		/*
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		}); */
		
		btnLogin.setBounds(53, 145, 117, 25);
		frame.getContentPane().add(btnLogin);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnCancel.setBounds(268, 145, 117, 25);
		frame.getContentPane().add(btnCancel);
		
		wrong_label = new JLabel("");
		wrong_label.setBounds(92, 182, 245, 41);
		frame.getContentPane().add(wrong_label);
	}
	
	public char[] getPass()
	{
		return passwordField.getPassword();
	}
	
	public String getUsername()
	{
		return username_textField.getText();
	}
	
	public void addController(ActionListener controller){
		btnLogin.addActionListener(controller);
		//System.out.println("add_HRView");
	}
	
	@Override
	public void update(Observable obs, Object obj) {
		
		String clas = (String)obj;
		int i  = clas.length();
		
		// an to to length to clas einai 2 tote o xristis einai hr, an einai megalytero
		// tote einai proistamenos
		if (i == 2)
		{
			frame.dispose();
			jframe newWindow = new jframe();
			newWindow.setVisible(true);
		}else if (i >= 5){
			frame.dispose();
			hr_jframe newWindow = new hr_jframe();
			newWindow.setVisible(true);
		}else{
			wrong_label.setText("Wrong username or password\n");
		}
	}
}
