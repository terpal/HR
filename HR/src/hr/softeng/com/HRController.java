package hr.softeng.com;


public class HRController implements java.awt.event.ActionListener {
	
	HRModel model;
	HRView view;
	
	public void actionPerformed( java.awt.event.ActionEvent e){
		
		String Username = view.getUsername();
        String Pass = String.valueOf(view.getPass());
		
		model.Login(Username, Pass);
	}
	
	public void addModel(HRModel m){
		this.model = m;
	}
	
	public void addView(HRView v){
		this.view = v;
	}
	
}