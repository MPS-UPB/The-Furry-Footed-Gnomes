

import java.util.ArrayList;
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

        SchemaParser schemaParser = new SchemaParser("crop.xsd");

        schemaParser.parse();

        ExecutableTask execTask = schemaParser.getResult();
       
		// Set input and output files.
		// These will be normally set through the UI.
        execTask.setInputFile("phototest.tif");
        execTask.setOutputFile("phototest_out.tif");
        
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
            }
        }
        // Set the new parameters.
        execTask.setParams(execParams);
		
		// Generate XML String. It must be validated afterwards for correctness!
        System.out.println(execTask.toXMLString());
    }
}
