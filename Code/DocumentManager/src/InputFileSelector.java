import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;

import executor.WorkflowExecutor;

import schemaparser.ExecutableTask;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.awt.Color;


public class InputFileSelector extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JButton okButton;
	private ArrayList<ExecutableTask> tasksToProcess;
	private String outputDir;

/*	*//**
	 * Launch the application.
	 *//*
	public static void main(String[] args) {
		try {
			InputFileSelector dialog = new InputFileSelector();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
*/
	/**
	 * Create the dialog.
	 */
	public InputFileSelector(ArrayList<ExecutableTask> ttp, String outDir) {
		
		tasksToProcess = ttp;
		outputDir = outDir;
		
		setTitle("Input File Selector");
		setBounds(100, 100, 579, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		textField = new JTextField();
		textField.setBackground(Color.WHITE);
		textField.setEditable(false);
		textField.setBounds(48, 100, 364, 32);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		
		// Create and Configure Browser
		final JFileChooser browser = new JFileChooser();
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (browser.showDialog(InputFileSelector.this, "Select File") == 0) {
					textField.setText(browser.getSelectedFile().toString());
					okButton.setEnabled(true);
					
					startExecution();
				}
			}
		});
		btnBrowse.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBrowse.setBounds(435, 100, 89, 32);
		contentPanel.add(btnBrowse);
		
		JLabel lblChoseAnInput = new JLabel("Choose an input picture");
		lblChoseAnInput.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblChoseAnInput.setBounds(48, 65, 176, 14);
		contentPanel.add(lblChoseAnInput);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(48, 87, 176, 2);
		contentPanel.add(separator);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.setEnabled(false);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	
	private void startExecution() {
		WorkflowExecutor we = new WorkflowExecutor(tasksToProcess);
		
		ArrayList<String> filesInput = new ArrayList<>();
		filesInput.add(textField.getText());
		ArrayList<String> filesOutput = new ArrayList<>();
		
		File f = new File(textField.getText());
		filesOutput.add(outputDir + "\\" + "res_" + f.getName());
		
		//we.execute(filesInpout, filesOutput);
		
		//dispose();

	}
}
