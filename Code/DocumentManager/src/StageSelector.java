import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;


public class StageSelector extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			StageSelector dialog = new StageSelector();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public StageSelector() {
		setTitle("Stage Selector");
		setBounds(100, 100, 450, 257);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		
		comboBox.setBounds(35, 95, 158, 26);
		comboBox.addItem("Preprocessing");
		comboBox.addItem("Binarization");
		comboBox.addItem("Layout");
		comboBox.addItem("Paging");
		comboBox.addItem("OCR");
		comboBox.addItem("Hierarchy");
		comboBox.addItem("PDF-exporter");
		contentPanel.add(comboBox);
		
		JLabel lblStartingStage = new JLabel("Start stage");
		lblStartingStage.setFont(new Font("Georgia", Font.PLAIN, 14));
		lblStartingStage.setBounds(85, 58, 105, 26);
		contentPanel.add(lblStartingStage);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(244, 95, 158, 26);
		comboBox_1.addItem("Preprocessing");
		comboBox_1.addItem("Binarization");
		comboBox_1.addItem("Layout");
		comboBox_1.addItem("Paging");
		comboBox_1.addItem("OCR");
		comboBox_1.addItem("Hierarchy");
		comboBox_1.addItem("PDF-exporter");

		contentPanel.add(comboBox_1);
		
		JLabel lblEndStage = new JLabel("End Stage");
		lblEndStage.setFont(new Font("Georgia", Font.PLAIN, 14));
		lblEndStage.setBounds(290, 58, 134, 26);
		contentPanel.add(lblEndStage);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(35, 79, 158, 9);
		contentPanel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(244, 79, 158, 9);
		contentPanel.add(separator_1);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
