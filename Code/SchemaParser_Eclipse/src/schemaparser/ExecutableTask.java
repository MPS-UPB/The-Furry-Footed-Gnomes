/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package schemaparser;

import java.util.ArrayList;

/**
 * A structure associated with an executable.
 * @author Gabriel
 */
public class ExecutableTask {

    private ExecutableInfo execInfo = null;
    private String inputFile = null;
    private String outputFile = null;
    private ArrayList<ExecutableParameter> execParams;

    /**
     * Constructor.
     */
    public ExecutableTask() {
        execParams = new ArrayList<>();
    }

    /**
     * Sets the displayable information.
     * @param info	The displayable information.
     */
    public void setExecInfo(ExecutableInfo info) {
        execInfo = info;
    }

    /**
     * Gets the displayable information.
     * @return	The displayable information.
     */
    public ExecutableInfo getExecInfo() {
        return execInfo;
    }

    /**
     * Sets the task input file.
     * @param file	The input file.
     */
    public void setInputFile(String file) {
        inputFile = file;
    }

    /**
     * Returns the task output file.
     * @param file	The output file.
     */
    public void setOutputFile(String file) {
        outputFile = file;
    }

    /**
     * Adds parameter for a task.
     * @param param	The parameter to be added.
     */
    public void addParameter(ExecutableParameter param) {
        execParams.add(param);
    }

    /**
     * Returns the list of parameters.
     * @return	The list of parameters.
     */
    public ArrayList<ExecutableParameter> getParams() {
        return execParams;
    }

    @Override
    public String toString() {

        String result = "{\n";

        result += "\texecInfo : " + execInfo.toString() + ",\n";
        result += "\tinputFile : " + inputFile + ",\n";
        result += "\toutputFile : " + outputFile + ",\n";

        if (!execParams.isEmpty()) {
            result += "\texecParams : ";
            result += "[";

            for (int i = 0; i < execParams.size(); i++) {
                result += execParams.get(i).getParamName();
                result += " : ";
                result += execParams.get(i).getParamValue();
                result += " | ";
            }

            result += "]\n";
        }

        result += "}";

        return result;
    }

    /**
     * Returns an XML String, ready to be written to file.
     * @return	The XML String.
     */
    public String toXMLString() {

        String XMLString;

        XMLString = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" ?>\n";
        XMLString += "<task>\n";
        XMLString += "\t<inputFile name=\"" + inputFile + "\" />\n";
        XMLString += "\t<outputFile name=\"" + outputFile + "\" />\n";

        for (int i = 0; i < execParams.size(); i++) {
            XMLString += "\t" + execParams.get(i).toXMLElement() + "\n";
        }

        XMLString += "</task>";

        return XMLString;
    }
}
