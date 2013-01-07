import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JMenuBar;
import java.awt.SystemColor;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JLabel;

import schemaparser.ExecutableTask;
import schemaparser.ReturnXSDs;
import schemaparser.SchemaParser;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.util.ArrayList;


public class GUI extends JFrame {
	private JPanel mainWindow;
	public StageSelector ss; 
	public Setup setupDialog;
	public String exeDir = new String();
	public String schemaDir = new String();
	public String outDir = new String();
	private ArrayList<ExecutableTask> execTasks;

	
	public void setExeDir (String name) {
		exeDir = name;
	}
	
	public void setStageSelector(StageSelector ss) {
		this.ss = ss;
	}
	
	public void setSetup (Setup setup) {
		this.setupDialog = setup;
	}
	
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					ss = new StageSelector();
					setupDialog = new Setup();
					
					frame.setVisible(true);
					ss.setLocation(400, 250);
					setupDialog.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public GUI() {
		execTasks = new ArrayList<ExecutableTask>();
		setTitle("Document Processing Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0,0,screenSize.width, screenSize.height-40);


	}
	
	
	public void getExecTasks() {
		
		ArrayList<String> XSDs = ReturnXSDs.getXSDs(schemaDir);
		
		for (String xsd : XSDs) {
			System.out.println(schemaDir + xsd);
			SchemaParser schemaParser = new SchemaParser(schemaDir + "\\" +xsd);

	        schemaParser.parse();
	        execTasks.add(schemaParser.getResult());
	        
	        System.out.println(schemaParser.getResult().toXMLString());
		}
	}
}
