
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

public class Setup extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField executablesField;
    private JTextField schemasField;
    private JTextField outputField;
    private GUI parent;
    private JFileChooser browser;
    private JButton acceptButton;
    private JButton cancelButton;
    private File configFile;

    /**
     * Create the dialog.
     */
    public Setup(GUI parentFrame) {

        parent = parentFrame;

        configFile = new File("config.ini");

        if (configFile.exists()) {
            readConfigFile();
            parent.getStageSelector().setVisible(true);
            parent.populateTasks();
            setVisible(false);
        } else {
            createInterface();
            setVisible(true);
        }
        
    }

    private void createInterface() {
        
        setBackground(SystemColor.activeCaptionBorder);
        setTitle("Path Setter");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(200, 80, screenSize.width - 450, screenSize.height - 200);

        getContentPane().setLayout(new BorderLayout());

        contentPanel.setBackground(SystemColor.activeCaptionBorder);
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        JLabel messageLabel = new JLabel("Please select your configuration directories");
        messageLabel.setForeground(SystemColor.menuText);
        messageLabel.setFont(new Font("Microsoft YaHei", Font.BOLD, 18));
        messageLabel.setBackground(SystemColor.controlHighlight);

        JSeparator separator = new JSeparator();
        separator.setForeground(SystemColor.windowText);

        // Create and Configure Browser
        browser = new JFileChooser();
        browser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        browser.setAcceptAllFileFilterUsed(false);


        // Executables.
        JLabel executablesLabel = new JLabel("Executables directory");
        executablesLabel.setFont(new Font("Tahoma", Font.BOLD, 15));

        executablesField = new JTextField();
        executablesField.setColumns(10);
        executablesField.setEditable(false);

        JButton executablesBrowse = new JButton("Browse");
        executablesBrowse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (browser.showDialog(Setup.this, "Select Directory") == 0) {
                    executablesField.setText(browser.getSelectedFile().toString());
                    parent.setExecutablesDir(browser.getSelectedFile().toString());
                }
                checkFields();
            }
        });
        executablesBrowse.setFont(new Font("Tahoma", Font.BOLD, 11));



        // Schemas.
        JLabel schemasLabel = new JLabel("XML Schemas Directory");
        schemasLabel.setFont(new Font("Tahoma", Font.BOLD, 15));

        schemasField = new JTextField();
        schemasField.setColumns(10);
        schemasField.setEditable(false);

        JButton schemasBrowse = new JButton("Browse");
        schemasBrowse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (browser.showDialog(Setup.this, "Select Directory") == 0) {
                    schemasField.setText(browser.getSelectedFile().toString());
                    parent.setSchemasDir(browser.getSelectedFile().toString());
                }
                checkFields();
            }
        });
        schemasBrowse.setFont(new Font("Tahoma", Font.BOLD, 11));



        // Output.
        JLabel outputLabel = new JLabel("Output directory");
        outputLabel.setFont(new Font("Tahoma", Font.BOLD, 15));

        outputField = new JTextField();
        outputField.setColumns(10);
        outputField.setEditable(false);

        JButton outputBrowse = new JButton("Browse");
        outputBrowse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (browser.showDialog(Setup.this, "Select Directory") == 0) {
                    outputField.setText(browser.getSelectedFile().toString());
                    parent.setOutputDir(browser.getSelectedFile().toString());
                }
                checkFields();
            }
        });


        outputBrowse.setFont(new Font("Tahoma", Font.BOLD, 11));
        GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
        gl_contentPanel.setHorizontalGroup(
                gl_contentPanel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPanel.createSequentialGroup()
                .addGap(54)
                .addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
                .addComponent(outputLabel, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
                .addGroup(gl_contentPanel.createSequentialGroup()
                .addComponent(outputField, GroupLayout.PREFERRED_SIZE, 447, GroupLayout.PREFERRED_SIZE)
                .addGap(49)
                .addComponent(outputBrowse, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))
                .addComponent(schemasLabel)
                .addGroup(gl_contentPanel.createSequentialGroup()
                .addComponent(schemasField, GroupLayout.PREFERRED_SIZE, 447, GroupLayout.PREFERRED_SIZE)
                .addGap(49)
                .addComponent(schemasBrowse, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))
                .addComponent(executablesLabel)
                .addGroup(gl_contentPanel.createSequentialGroup()
                .addComponent(executablesField, GroupLayout.PREFERRED_SIZE, 447, GroupLayout.PREFERRED_SIZE)
                .addGap(49)
                .addComponent(executablesBrowse, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))
                .addComponent(messageLabel, GroupLayout.PREFERRED_SIZE, 476, GroupLayout.PREFERRED_SIZE)
                .addComponent(separator, GroupLayout.PREFERRED_SIZE, 502, GroupLayout.PREFERRED_SIZE))
                .addGap(170)));
        gl_contentPanel.setVerticalGroup(
                gl_contentPanel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPanel.createSequentialGroup()
                .addGap(29)
                .addComponent(messageLabel, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(separator, GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(executablesLabel)
                .addGap(7)
                .addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
                .addComponent(executablesField, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                .addComponent(executablesBrowse, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
                .addGap(42)
                .addComponent(schemasLabel)
                .addGap(7)
                .addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
                .addComponent(schemasField, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                .addGroup(gl_contentPanel.createSequentialGroup()
                .addGap(2)
                .addComponent(schemasBrowse, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)))
                .addGap(40)
                .addComponent(outputLabel, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
                .addGap(7)
                .addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
                .addComponent(outputField, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                .addGroup(gl_contentPanel.createSequentialGroup()
                .addGap(2)
                .addComponent(outputBrowse, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)))
                .addGap(106)));
        contentPanel.setLayout(gl_contentPanel);


        // Lower buttons.
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        // Accept button.
        acceptButton = new JButton("Accept");
        acceptButton.setEnabled(false);
        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                writeConfigFile();
                parent.setExecutablesDir(executablesField.getText());
                parent.setSchemasDir(schemasField.getText());
                parent.setOutputDir(outputField.getText());
                parent.getStageSelector().setVisible(true);
                parent.populateTasks();

                dispose();
            }
        });
        acceptButton.setActionCommand("Accept");
        buttonPane.add(acceptButton);


        // Cancel button.
        cancelButton = new JButton("Exit");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.exit(Frame.NORMAL);
            }
        });
        cancelButton.setActionCommand("Exit");
        buttonPane.add(cancelButton);


        getRootPane().setDefaultButton(acceptButton);
    }

    private void checkFields() {

        boolean enabled = true;

        enabled &= !executablesField.getText().equals("");
        enabled &= !schemasField.getText().equals("");
        enabled &= !outputField.getText().equals("");

        acceptButton.setEnabled(enabled);

    }

    private void readConfigFile() {

        String path;
        String[] keyValue;

        FileReader fileReader = null;
        BufferedReader bufferedReader = null;

        try {

            fileReader = new FileReader(configFile);
            bufferedReader = new BufferedReader(fileReader);

            for (int i = 0; i < 3; i++) {
                path = bufferedReader.readLine();
                keyValue = path.split("=");
                switch (keyValue[0]) {
                    case "ExecutablesDir":
                        parent.setExecutablesDir(keyValue[1]);
                        break;
                    case "SchemasDir":
                        parent.setSchemasDir(keyValue[1]);
                        break;
                    case "OutputDir":
                        parent.setOutputDir(keyValue[1]);
                        break;
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(Setup.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                bufferedReader.close();
                fileReader.close();
            } catch (Exception ex) {
                Logger.getLogger(Setup.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void writeConfigFile() {

        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;

        try {

            fileWriter = new FileWriter(configFile);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("ExecutablesDir=" + parent.getExecutablesDir() + "\n");
            bufferedWriter.write("SchemasDir=" + parent.getSchemasDir() + "\n");
            bufferedWriter.write("OutputDir=" + parent.getOutputDir() + "\n");

        } catch (Exception ex) {
            Logger.getLogger(Setup.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                bufferedWriter.close();
                fileWriter.close();
            } catch (Exception ex) {
                Logger.getLogger(Setup.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
