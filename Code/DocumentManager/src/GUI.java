
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import schemaparser.ExecutableInfo;
import schemaparser.ExecutableTask;
import schemaparser.ReturnXSDs;
import schemaparser.SchemaParser;

public class GUI extends JFrame {

    private JPanel mainWindow;
    private StageSelector stageSelector;
    private Setup setupDialog;
    private String executablesDir = new String();
    private String schemasDir = new String();
    private String outputDir = new String();
    private ArrayList<ExecutableTask> execTasks;
    private ArrayList<ExecutableInfo> execInfo;

    public void setExecutablesDir(String name) {
        executablesDir = name;
    }
    
    public String getExecutablesDir () {
        return executablesDir;
    }

    public void setSchemasDir(String name) {
        schemasDir = name;
    }
    
    public String getSchemasDir() {
        return schemasDir;
    }

    public void setOutputDir(String name) {
        outputDir = name;
    }
    
    public String getOutputDir() {
        return outputDir;
    }

    public void setStageSelector(StageSelector stage) {
        stageSelector = stage;
    }

    public StageSelector getStageSelector() {
        return stageSelector;
    }

    public void setSetupDialog(Setup setup) {
        setupDialog = setup;
    }

    public Setup getSetupDialog() {
        return setupDialog;
    }

    /**
     * Create the frame.
     */
    public GUI() {

        execTasks = new ArrayList<>();

        setTitle("Document Processing Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(false);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, screenSize.width, screenSize.height - 40);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mnExecute = new JMenu("Execute");
        menuBar.add(mnExecute);

        JMenuItem mntmStart = new JMenuItem("Start");
        mnExecute.add(mntmStart);

        mntmStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {

                ArrayList<ExecutableTask> tasksToProcess = new ArrayList<>();

                JRadioButton btn = (JRadioButton) mainWindow.getComponent(9);
                if (btn.isSelected()) {
                    ExecutableTask et = getExeByName(btn.getText());
                    for (int i = 11; i <= 17; i += 2) {
                        JTextField text = (JTextField) mainWindow.getComponent(i);
                        et.setSingleParam(((JLabel) mainWindow.getComponent(i - 1)).getText(), text.getText());
                    }
                    tasksToProcess.add(et);
                } else if (((JRadioButton) mainWindow.getComponent(18)).isSelected()) {
                    ExecutableTask et = getExeByName(((JRadioButton) mainWindow.getComponent(18)).getText());
                    et.setSingleParam(((JLabel) mainWindow.getComponent(19)).getText(), ((JTextField) mainWindow.getComponent(20)).getText());
                    tasksToProcess.add(et);
                }

                if (((JRadioButton) mainWindow.getComponent(36)).isSelected()) {
                    ExecutableTask et = getExeByName(((JRadioButton) mainWindow.getComponent(36)).getText());

                    tasksToProcess.add(et);
                }


                InputFileSelector ifs = new InputFileSelector(tasksToProcess, outputDir);
                ifs.setVisible(true);
            }
        });
        mnExecute.add(mntmStart);
    }

    // Gets ExecutableTask by name
    private ExecutableTask getExeByName(String exeName) {
        for (ExecutableTask et : execTasks) {
            if (exeName.compareTo(et.getExecInfo().getExecName()) == 0) {
                return et;
            }
        }
        return null;
    }

    void customizeWorkflow(String start, String end) {

        execTasks = new ArrayList<>();
        execInfo = new ArrayList<>();
        ArrayList<String> tasks = new ArrayList<>();
        ArrayList<ArrayList<Object>> Preprocessing = new ArrayList<>();
        ArrayList<ArrayList<Object>> Binarization = new ArrayList<>();
        ArrayList<ArrayList<Object>> Layout = new ArrayList<>();
        ArrayList<ArrayList<Object>> Paging = new ArrayList<>();
        ArrayList<ArrayList<Object>> OCR = new ArrayList<>();
        ArrayList<ArrayList<Object>> Hierarchy = new ArrayList<>();
        ArrayList<ArrayList<Object>> PDFexporter = new ArrayList<>();
        ButtonGroup preprocessingGroup = new ButtonGroup();
        ButtonGroup binarizationgGroup = new ButtonGroup();
        ButtonGroup layoutGroup = new ButtonGroup();
        ButtonGroup pagingGroup = new ButtonGroup();
        ButtonGroup ocrGroup = new ButtonGroup();
        ButtonGroup hierarchyGroup = new ButtonGroup();
        ButtonGroup pdfexporterGroup = new ButtonGroup();

        tasks.add("Preprocessing");
        tasks.add("Binarization");
        tasks.add("Layout");
        tasks.add("Paging");
        tasks.add("OCR");
        tasks.add("Hierarchy");
        tasks.add("PDF-exporter");
        int rows = tasks.indexOf(end) - tasks.indexOf(start) + 1;
        populateTasks();
        int max = 0;
        for (int i = 0; i < execTasks.size(); i++) {
            execInfo.add(execTasks.get(i).getExecInfo());

            if (execTasks.get(i).getParams().size() > max) {
                max = execTasks.get(i).getParams().size();
            }

            if (tasks.indexOf(start) == 0 && (execInfo.get(i).getExecType().contains("preprocessing") || execInfo.get(i).getExecType().contains("support"))) {
                ArrayList<Object> aux = new ArrayList<>();
                JRadioButton d = new JRadioButton(execInfo.get(i).getExecName());
                preprocessingGroup.add(d);
                aux.add(d);
                for (int j = 0; j < execTasks.get(i).getParams().size(); j++) {
                    aux.add(new JLabel(execTasks.get(i).getParams().get(j).getParamName()));
                    aux.add(new JTextField());
                }
                rows++;
                Preprocessing.add(aux);
            }
            if (tasks.indexOf(start) <= 1 && tasks.indexOf(end) >= 1 && execInfo.get(i).getExecType().contains("binarization")) {
                ArrayList<Object> aux = new ArrayList<>();
                JRadioButton d = new JRadioButton(execInfo.get(i).getExecName());
                binarizationgGroup.add(d);
                aux.add(d);
                for (int j = 0; j < execTasks.get(i).getParams().size(); j++) {
                    aux.add(new JLabel(execTasks.get(i).getParams().get(j).getParamName()));
                    aux.add(new JTextField());
                }
                Binarization.add(aux);
                rows++;
            }
            if (tasks.indexOf(start) <= 2 && tasks.indexOf(end) >= 2 && execInfo.get(i).getExecType().contains("layout")) {
                ArrayList<Object> aux = new ArrayList<>();
                JRadioButton d = new JRadioButton(execInfo.get(i).getExecName());
                aux.add(d);
                for (int j = 0; j < execTasks.get(i).getParams().size(); j++) {
                    aux.add(new JLabel(execTasks.get(i).getParams().get(j).getParamName()));
                    aux.add(new JTextField());
                }
                Layout.add(aux);
                rows++;
            }
            if (tasks.indexOf(start) <= 3 && tasks.indexOf(end) >= 3 && execInfo.get(i).getExecType().contains("paging")) {
                ArrayList<Object> aux = new ArrayList<>();
                JRadioButton d = new JRadioButton(execInfo.get(i).getExecName());
                pagingGroup.add(d);
                aux.add(d);
                for (int j = 0; j < execTasks.get(i).getParams().size(); j++) {
                    aux.add(new JLabel(execTasks.get(i).getParams().get(j).getParamName()));
                    aux.add(new JTextField());
                }
                Paging.add(aux);
                rows++;
            }
            if (tasks.indexOf(start) <= 4 && tasks.indexOf(end) >= 4 && execInfo.get(i).getExecType().contains("ocr")) {
                ArrayList<Object> aux = new ArrayList<>();
                JRadioButton d = new JRadioButton(execInfo.get(i).getExecName());
                ocrGroup.add(d);
                aux.add(d);
                for (int j = 0; j < execTasks.get(i).getParams().size(); j++) {
                    aux.add(new JLabel(execTasks.get(i).getParams().get(j).getParamName()));
                    aux.add(new JTextField());
                }
                OCR.add(aux);
                rows++;
            }
            if (tasks.indexOf(start) <= 5 && tasks.indexOf(end) >= 5 && execInfo.get(i).getExecType().contains("hierarchy")) {
                ArrayList<Object> aux = new ArrayList<>();
                JRadioButton d = new JRadioButton(execInfo.get(i).getExecName());
                hierarchyGroup.add(d);
                aux.add(d);
                for (int j = 0; j < execTasks.get(i).getParams().size(); j++) {
                    aux.add(new JLabel(execTasks.get(i).getParams().get(j).getParamName()));
                    aux.add(new JTextField());
                }
                Hierarchy.add(aux);
                rows++;
            }
            if (tasks.indexOf(end) == 6 && execInfo.get(i).getExecType().contains("pdf-exporter")) {
                ArrayList<Object> aux = new ArrayList<>();
                JRadioButton d = new JRadioButton(execInfo.get(i).getExecName());
                pdfexporterGroup.add(d);
                aux.add(d);
                for (int j = 0; j < execTasks.get(i).getParams().size(); j++) {
                    aux.add(new JLabel(execTasks.get(i).getParams().get(j).getParamName()));
                    aux.add(new JTextField());
                }
                PDFexporter.add(aux);
                rows++;
            }


        }

        System.out.println(rows);
        System.out.println(start + end);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, screenSize.width, screenSize.height - 40);

        mainWindow = new JPanel(new GridLayout(rows, 2 * max + 1));
        setContentPane(mainWindow);
        int y = 0;
        for (int i = tasks.indexOf(start); i <= tasks.indexOf(end); i++) {
            // seteaza titlul sectiune (ex: Preprocesare)
            mainWindow.add(new JLabel(tasks.get(i)));
            for (int j = 0; j < 2 * max; j++) {
                mainWindow.add(new JLabel());
            }
            switch (i) {
                case 0:
                    for (int j = 0; j < Preprocessing.size(); j++) {
                        // Radio BUtton cu Text
                        mainWindow.add((JRadioButton) Preprocessing.get(j).get(0));
                        for (int k = 1; k < Preprocessing.get(j).size(); k++) {
                            // Adauga parametrii
                            mainWindow.add((JLabel) Preprocessing.get(j).get(k));
                            mainWindow.add((JTextField) Preprocessing.get(j).get(k + 1));
                            k++;
                        }
                        for (int k = Preprocessing.get(j).size(); k < 2 * max + 1; k++) {
                            mainWindow.add(new JLabel());
                        }
                    }
                    break;
                case 1:
                    for (int j = 0; j < Binarization.size(); j++) {
                        mainWindow.add((JRadioButton) Binarization.get(j).get(0));
                        for (int k = 1; k < Binarization.get(j).size(); k++) {
                            mainWindow.add((JLabel) Binarization.get(j).get(k));
                            mainWindow.add((JTextField) Binarization.get(j).get(k + 1));
                            k++;
                        }
                        for (int k = Binarization.get(j).size(); k < 2 * max + 1; k++) {
                            mainWindow.add(new JLabel());
                        }
                    }
                    break;
                case 2:
                    for (int j = 0; j < Layout.size(); j++) {
                        mainWindow.add((JRadioButton) Layout.get(j).get(0));
                        for (int k = 1; k < Layout.get(j).size(); k++) {
                            mainWindow.add((JLabel) Layout.get(j).get(k));
                            mainWindow.add((JTextField) Layout.get(j).get(k + 1));
                            k++;
                        }
                        for (int k = Layout.get(j).size(); k < 2 * max + 1; k++) {
                            mainWindow.add(new JLabel());
                        }
                    }
                    break;
                case 3:
                    for (int j = 0; j < Paging.size(); j++) {
                        mainWindow.add((JRadioButton) Paging.get(j).get(0));
                        for (int k = 1; k < Paging.get(j).size(); k++) {
                            mainWindow.add((JLabel) Paging.get(j).get(k));
                            mainWindow.add((JTextField) Paging.get(j).get(k + 1));
                            k++;
                        }
                        for (int k = Paging.get(j).size(); k < 2 * max + 1; k++) {
                            mainWindow.add(new JLabel());
                        }
                    }
                    break;
                case 4:
                    for (int j = 0; j < OCR.size(); j++) {
                        mainWindow.add((JRadioButton) OCR.get(j).get(0));
                        for (int k = 1; k < OCR.get(j).size(); k++) {
                            mainWindow.add((JLabel) OCR.get(j).get(k));
                            mainWindow.add((JTextField) OCR.get(j).get(k + 1));
                            k++;
                        }
                        for (int k = OCR.get(j).size(); k < 2 * max + 1; k++) {
                            mainWindow.add(new JLabel());
                        }
                    }
                    break;
                case 5:
                    for (int j = 0; j < Hierarchy.size(); j++) {
                        mainWindow.add((JRadioButton) Hierarchy.get(j).get(0));
                        for (int k = 1; k < Hierarchy.get(j).size(); k++) {
                            mainWindow.add((JLabel) Hierarchy.get(j).get(k));
                            mainWindow.add((JTextField) Hierarchy.get(j).get(k + 1));
                            k++;
                        }
                        for (int k = Hierarchy.get(j).size(); k < 2 * max + 1; k++) {
                            mainWindow.add(new JLabel());
                        }
                    }
                    break;
                case 6:
                    for (int j = 0; j < PDFexporter.size(); j++) {
                        mainWindow.add((JRadioButton) PDFexporter.get(j).get(0));
                        for (int k = 1; k < PDFexporter.get(j).size(); k++) {
                            mainWindow.add((JLabel) PDFexporter.get(j).get(k));
                            mainWindow.add((JTextField) PDFexporter.get(j).get(k + 1));
                            k++;
                        }
                        for (int k = PDFexporter.get(j).size(); k < 2 * max + 1; k++) {
                            mainWindow.add(new JLabel());
                        }
                    }
                    break;
            }
        }
        //mainWindow.add(new JLabel("aedsfvca"));
/*		JButton btn_execute = new JButton();
         btn_execute.setText("Execute");
         mainWindow.add(btn_execute);
         */ System.out.println(mainWindow.toString());
        setVisible(true);
        //mainWindow=new JPanel();setContentPane(mainWindow);

        //mainWindow.add(new JButton("ccas"));
    }

    public void populateTasks() {

        ArrayList<String> XSDs = ReturnXSDs.getXSDs(schemasDir);

        for (String xsd : XSDs) {
            System.out.println(schemasDir + "\\" + xsd);
            SchemaParser schemaParser = new SchemaParser(schemasDir + "\\" + xsd);

            schemaParser.parse();
            ExecutableTask task = schemaParser.getResult();
            task.setExecDir(executablesDir);
            execTasks.add(schemaParser.getResult());

            System.out.println(schemaParser.getResult().toXMLString());
        }
    }
}
