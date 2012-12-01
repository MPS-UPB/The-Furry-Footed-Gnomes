/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Gabriel
 */
import schemaparser.*;

public class MainClass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        SchemaParser schemaParser = new SchemaParser("crop.xsd");

        schemaParser.parse();

        ExecutableTask execTask = schemaParser.getResult();
       
		// Set input and output files.
		// These will be normally set through the UI.
        execTask.setInputFile("phototest.tif");
        execTask.setOutputFile("phototest_out.tif");
        
		// Note: No parameters have been set, they will show up as null.
		// Parameters will be set through the UI.
		
		// Generate XML String.
        System.out.println(execTask.toXMLString());
    }
}
