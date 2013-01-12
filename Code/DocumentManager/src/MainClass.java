
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			GUI frame = new GUI();
			frame.setStageSelector(new StageSelector(frame));
			frame.setSetup(new Setup(frame));
			
			frame.setVisible(true);
			frame.ss.setLocation(400, 250);
			frame.setupDialog.setVisible(true);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
