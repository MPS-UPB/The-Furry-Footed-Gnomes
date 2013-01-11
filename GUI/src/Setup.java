import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;

import schemaparser.ReturnXSDs;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.nio.channels.AcceptPendingException;
import java.util.ArrayList;


public class Setup extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private int enable_button;
	private JButton okButton;
	private final GUI parent;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			Setup dialog = new Setup();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	/**
	 * Get the values of text fields
	 */
	public void getTextFields(String tf1, String tf2, String tf3) {
		tf1 = textField.getText();
		tf2 = textField.getText();
		tf3 = textField.getText();
	}

	/**
	 * Create the dialog.
	 */
	public Setup(final GUI parent) {
		this.parent = parent;
		this.enable_button = 0;
		setBackground(SystemColor.activeCaptionBorder);
		setTitle("Path Setter");
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(200,80,screenSize.width-450, screenSize.height-200);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.activeCaptionBorder);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblPlease = new JLabel("Please select your configuration directories");
		lblPlease.setForeground(SystemColor.menuText);
		lblPlease.setFont(new Font("Microsoft YaHei", Font.BOLD, 18));
		lblPlease.setBackground(SystemColor.controlHighlight);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(SystemColor.windowText);
		
		// Create and Configure Browser
		final JFileChooser browser = new JFileChooser();
		browser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		browser.setAcceptAllFileFilterUsed(false);

		
		JLabel label = new JLabel("Executables directory");
		label.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JButton button = new JButton("Browse");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (browser.showDialog(Setup.this, "Select Directory") == 0) {
					textField.setText(browser.getSelectedFile().toString());
					parent.setExeDir(browser.getSelectedFile().toString());
				}
				checkFields();
			}
		});
		button.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblXmlSchemasDirectory = new JLabel("XML Schemas Directory");
		lblXmlSchemasDirectory.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JButton button_1 = new JButton("Browse");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (browser.showDialog(Setup.this, "Select Directory") == 0) { 
					textField_1.setText(browser.getSelectedFile().toString());
					parent.schemaDir = browser.getSelectedFile().toString();
				}
				checkFields();
			}
		});
		button_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblOutputDirectory = new JLabel("Output directory");
		lblOutputDirectory.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		JButton button_2 = new JButton("Browse");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (browser.showDialog(Setup.this, "Select Directory") == 0) { 
					textField_2.setText(browser.getSelectedFile().toString());
					parent.outDir = browser.getSelectedFile().toString();
				}
				checkFields();
			}
		});
		
		
		textField.setEditable(false);
		textField_1.setEditable(false);
		textField_2.setEditable(false);

		
		button_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(54)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblOutputDirectory, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 447, GroupLayout.PREFERRED_SIZE)
							.addGap(49)
							.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblXmlSchemasDirectory)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 447, GroupLayout.PREFERRED_SIZE)
							.addGap(49)
							.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))
						.addComponent(label)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 447, GroupLayout.PREFERRED_SIZE)
							.addGap(49)
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblPlease, GroupLayout.PREFERRED_SIZE, 476, GroupLayout.PREFERRED_SIZE)
						.addComponent(separator, GroupLayout.PREFERRED_SIZE, 502, GroupLayout.PREFERRED_SIZE))
					.addGap(170))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(29)
					.addComponent(lblPlease, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label)
					.addGap(7)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addGap(42)
					.addComponent(lblXmlSchemasDirectory)
					.addGap(7)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(2)
							.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)))
					.addGap(40)
					.addComponent(lblOutputDirectory, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addGap(7)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(2)
							.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)))
					.addGap(106))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("Accept");
				okButton.setEnabled(false);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						parent.exeDir = textField.getText();
						parent.schemaDir = textField_1.getText();
						parent.outDir = textField_2.getText();
						parent.ss.setVisible(true);
						parent.ss.numehaha=textField.getText();
						parent.ss.numehaha1=textField_1.getText();
						parent.ss.numehaha2=textField_2.getText();
						parent.getExecTasks();
						
						dispose();
					}
				});
				okButton.setActionCommand("Accept");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Exit");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						System.exit(Frame.NORMAL);
					}
				});
				cancelButton.setActionCommand("Exit");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	private void checkFields ()
	{
		if (!textField.getText().equals("") && !textField_1.getText().equals("") && !textField_2.getText().equals(""))
			okButton.setEnabled(true);
	}
}
