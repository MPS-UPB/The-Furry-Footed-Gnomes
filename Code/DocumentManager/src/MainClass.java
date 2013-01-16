
public class MainClass {

    public static void main(String[] args) {
        
        GUI frame = new GUI();
        
        frame.setStageSelector(new StageSelector(frame));
        
        frame.setSetupDialog(new Setup(frame));
    }
}
