/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package schemaparser;

/**
 * A structure that holds parameter data.
 * @author Gabriel
 */
public class ExecutableParameter {
    
    private String paramName;
    private String paramValue;
    
    /**
     * Sets the parameter name.
     * @param name The parameter name.
     */
    public void setParamName(String name) {
        paramName = name;
    }
    
    /**
     * Returns the parameter name.
     * @return	The parameter name.
     */
    public String getParamName() {
        return paramName;
    }
    
    /**
     * Sets the parameter value.
     * @param value	The parameter value.
     */
    public void setParamValue(String value) {
        paramValue = value;
    }
    
    /**
     * Returns the parameter value.
     * @return	The parameter value.
     */
    public String getParamValue() {
        return paramValue;
    }
    
    
    /**
     * Creates an XML element String the be written to an XML file. 
     * @return	The XML element String.
     */
    public String toXMLElement() {
        return "<" + paramName + ">" +
                paramValue +
                "</" + paramName + ">";
    }
}
