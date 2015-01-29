package hr.softeng.com;


public class HRMain { 

	public static void main(String[] args) {
		
		HRModel cModel = new HRModel();
		HRView cView = new HRView();
		
		
		cModel.createPointTable();
		cModel.createAccTable();
		
		cView.frame.setVisible(true);
		cModel.addObserver(cView);
		
		HRController cController = new HRController();
		cController.addModel(cModel);
		cController.addView(cView);
		cView.addController(cController);

	}

}
