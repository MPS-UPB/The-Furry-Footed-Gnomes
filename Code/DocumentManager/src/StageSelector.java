
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

public class StageSelector extends JDialog {

    private final GUI parent;
    private final JPanel contentPanel;
    private final JComboBox startStageComboBox;
    private final JComboBox endStageComboBox;
    private final static String[] stages = {
        "Preprocessing",
        "Binarization",
        "Layout",
        "Paging",
        "OCR",
        "Hierarchy",
        "PDF Exporter"
    };

    /**
     * Create the dialog.
     */
    public StageSelector(GUI parentFrame) {

        parent = parentFrame;

        setTitle("Stage Selector");
        setBounds(100, 100, 450, 257);
        setLocation(400, 250);
        getContentPane().setLayout(new BorderLayout());

        contentPanel = new JPanel();
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPanel.setLayout(null);

        getContentPane().add(contentPanel, BorderLayout.CENTER);

        // Start stage.
        JLabel startStageLabel = new JLabel("Start stage");
        startStageLabel.setFont(new Font("Georgia", Font.PLAIN, 14));
        startStageLabel.setBounds(85, 58, 105, 26);
        contentPanel.add(startStageLabel);

        startStageComboBox = new JComboBox();
        startStageComboBox.setBounds(35, 95, 158, 26);
        for (int i = 0; i < stages.length; i++) {
            startStageComboBox.addItem(stages[i]);
        }
        contentPanel.add(startStageComboBox);

        // End stage.
        JLabel endStageLabel = new JLabel("End Stage");
        endStageLabel.setFont(new Font("Georgia", Font.PLAIN, 14));
        endStageLabel.setBounds(290, 58, 134, 26);
        contentPanel.add(endStageLabel);

        endStageComboBox = new JComboBox();
        endStageComboBox.setBounds(244, 95, 158, 26);
        for (int i = 0; i < stages.length; i++) {
            endStageComboBox.addItem(stages[i]);
        }
        contentPanel.add(endStageComboBox);


        startStageComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                Object selectedStartItem = startStageComboBox.getSelectedItem();
                
                if (selectedStartItem != null) {
                    String selectedStartStage = selectedStartItem.toString();
                    
                    int k = 0;
                    while (!selectedStartStage.equals(stages[k])) {
                        k++;
                    }
                    
                    endStageComboBox.removeAllItems();
                    while (k < stages.length) {
                        endStageComboBox.addItem(stages[k++]);
                    }
                }
            }
        });


        // Some separators for good measure.
        JSeparator separator1 = new JSeparator();
        separator1.setBounds(35, 79, 158, 9);
        contentPanel.add(separator1);

        JSeparator separator2 = new JSeparator();
        separator2.setBounds(244, 79, 158, 9);
        contentPanel.add(separator2);


        // Buttons.
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        // OK button.
        JButton acceptButton = new JButton("OK");
        acceptButton.setActionCommand("Accept");
        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.customizeWorkflow(
                        startStageComboBox.getSelectedItem().toString(),
                        endStageComboBox.getSelectedItem().toString());
                dispose();
            }
        });
        buttonPane.add(acceptButton);

        // Cancel button.
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setActionCommand("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        buttonPane.add(cancelButton);

        getRootPane().setDefaultButton(acceptButton);
    }
}
