/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package schemaparser;

/**
 * A structure that holds displayable information.
 * @author Gabriel
 */
public class ExecutableInfo {

    private String execName = null;
    private String execType = null;
    private String execDescription = null;

    /**
     * Sets the executable name.
     * @param name	The executable name.
     */
    public void setExecName(String name) {
        execName = name;
    }

    /**
     * Sets the executable type.
     * @param type	Executable type.
     */
    public void setExecType(String type) {
        execType = type;
    }

    /**
     * Sets the executable description.
     * @param description	The executable description.
     */
    public void setExecDescription(String description) {
        execDescription = description;
    }

    /**
     * Returns the executable name.
     * @return	The executable name.
     */
    public String getExecName() {
        return execName;
    }

    /**
     * Returns the executable type.
     * @return	The executable type.
     */
    public String getExecType() {
        return execType;
    }

    /**
     * Returns the executable description.
     * @return	The executable description.
     */
    public String getExecDescription() {
        return execDescription;
    }
    
    @Override
    public String toString() {
        return "[" + execName + " | " + execType + " | " + execDescription + "]";
    }
}