
import executor.*;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.*;
import schemaparser.*;

/**
 *
 * @author Gabriel
 */
public class MainClass {

    /**
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        
        String execPath = "../../execs/";
        String xsdPath = execPath + "xml_schemas/";
        String xmlPath = execPath + "xml/";
        String imgInPath = "../../data/img_in/";
        String imgOutPath = "../../data/img_out/";
        
        String xmlWinPath = "..\\..\\execs\\xml\\";
        
        String xsdName = "rotate.xsd";
        String xmlName = "rotate.xml";
        String imgInName = "phototest.tif";
        String imgOutName = "phototest.tif";

        SchemaParser schemaParser = new SchemaParser(xsdPath + xsdName);

        schemaParser.parse();

        ExecutableTask execTask = schemaParser.getResult();

        // We only get the name (e.g. "rotate.exe") and must add the path.
        ExecutableInfo execInfo = execTask.getExecInfo();
        String execName = execInfo.getExecName();
        execName = execPath + execName;
        execInfo.setExecName(execName);
        execTask.setExecInfo(execInfo);
        
        // Set input and output files.
        // These will be normally set through the UI.
        execTask.setInputFile(imgInPath + imgInName);
        execTask.setOutputFile(imgOutPath + imgOutName);

        // Parameters will be set through the UI. Here they are set manually
        // for exemplification purposes.
        ArrayList<ExecutableParameter> execParams = execTask.getParams();
        for (int i = 0; i < execParams.size(); i++) {
            ExecutableParameter execParam = execParams.get(i);
            switch (execParam.getParamName()) {
                case "top":
                    execParam.setParamValue("2");
                    break;
                case "bottom":
                    execParam.setParamValue("16");
                    break;
                case "left":
                    execParam.setParamValue("12");
                    break;
                case "right":
                    execParam.setParamValue("25");
                    break;
                case "angle":
                    execParam.setParamValue("180");
                    break;
            }
        }
        // Set the new parameters.
        execTask.setParams(execParams);

        // Generate XML String. It must be validated afterwards for correctness!
        System.out.println(execTask.toXMLString());
        
        try {
            FileWriter fw = new FileWriter(xmlPath + xmlName);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(execTask.toXMLString());
            bw.close();
            fw.close();
            
        } catch (IOException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Execute a task
        // exemplification purposes only
        TaskExecutor executor = new TaskExecutor(execTask);
        // Execute task without any arguments
        executor.execute();

        // Execute task with an array of arguments
        String[] argsArray = new String[1];
        // Just one argument for now
        argsArray[0] = xmlWinPath + xmlName;
        executor.execute(argsArray);
        
        System.out.println(executor.getTaskError());

        // Validate an .xml against a schema (.xsd)
        // exemplification
        boolean isValid = XmlValidator.validateAgainstXSD(xmlPath + xmlName, xsdPath + xsdName);
        if (isValid) {
            System.out.println(".xml file is valid");
        } else {
            System.out.println(".xml file is NOT valid");
        }
    }
}
