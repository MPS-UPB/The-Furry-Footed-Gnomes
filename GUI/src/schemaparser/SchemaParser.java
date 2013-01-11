/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package schemaparser;

import com.sun.xml.xsom.*;
import com.sun.xml.xsom.parser.*;
import java.io.*;
import java.util.logging.*;
import org.xml.sax.*;

/**
 * The class used to load an XML Schema from a file and create a tree from its information.
 * @author Gabriel
 */
public class SchemaParser {

    private String fileName;
    ExecutableTask execTask;

    /**
     * Constructor.
     * @param fileName	The file from which the Schema tree is loaded.
     */
    public SchemaParser(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Builds the XML Schema tree into memory.
     * 
     */
    public void parse() {
        try {

            File file = new File(fileName);

            XSOMParser schemaParser = new XSOMParser();
            schemaParser.parse(file);
            XSSchemaSet schemaSet = schemaParser.getResult();
            XSSchema schema = schemaSet.getSchema(1);
            
            this.createResult(schema);

        } catch (SAXException | IOException ex) {
            Logger.getLogger(SchemaParser.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    
    /**
     * Creates the ExecutableTask from the Schema tree.
     */
    private void createResult(XSSchema schema) {

        execTask = new ExecutableTask();

        // Get the "task" xs:element.
        XSElementDecl task = schema.getElementDecl("task");

        // We know it's an xs:complexType.
        XSComplexType taskType = task.getType().asComplexType();

        // Get the xs:sequence of elements of the anonymous xs:complexType.
        // A model group can be xs:all, xs:choice or xs:sequence.
        XSParticle taskParticle = taskType.getContentType().asParticle();
        XSModelGroup taskSequence = taskParticle.getTerm().asModelGroup();

        // Get the direct children in the xs:sequence.
        XSParticle taskElemParticles[] = taskSequence.getChildren();
        for (XSParticle taskElemParticle : taskElemParticles) {

            XSElementDecl taskElem = taskElemParticle.getTerm().asElementDecl();

            if (taskElem.getName().equals("execInfo")) {    // Displayable info for the UI.


                // We have the "execInfo" element.
                ExecutableInfo execInfo = new ExecutableInfo();

                // We know it's an xs:complexType.
                XSComplexType infoType = taskElem.getType().asComplexType();

                XSParticle infoParticle = infoType.getContentType().asParticle();
                XSModelGroup infoSequence = infoParticle.getTerm().asModelGroup();

                XSParticle infoElemParticles[] = infoSequence.getChildren();
                for (XSParticle infoElemParticle : infoElemParticles) {

                    XSElementDecl infoElem = infoElemParticle.getTerm().asElementDecl();
                    XSSimpleType simpleType = infoElem.getType().asSimpleType();

                    XSRestrictionSimpleType restrictionSimple = simpleType.asRestriction();
                    
                    XSFacet facet = restrictionSimple.getDeclaredFacets().iterator().next();
                    switch (infoElem.getName()) {
                        case "name":
                            execInfo.setExecName(facet.getValue().toString());
                            break;
                        case "type":
                            execInfo.setExecType(facet.getValue().toString());
                            break;
                        case "description":
                            execInfo.setExecDescription(facet.getValue().toString());
                            break;
                    }

                }

                execTask.setExecInfo(execInfo);
                
            } else {
                
                XSType taskElemType = taskElem.getType();
                if (taskElemType.isSimpleType()) {
                    
                    // We do not check constraints. They will be validated later.
                    ExecutableParameter param = new ExecutableParameter();
                    param.setParamName(taskElem.getName());
                    
                    execTask.addParameter(param);
                    
                } else if (taskElemType.isComplexType()) {
                    // Refers to inputFile and outputFile.
                    // No need to check these since they are always the same.
                }
            }
        }

    }
    
    /**
     * Returns the ExecutableTask created from the Schema tree.
     * @return	The structure to be used to display UI information and to build XML structures after population.
     */
    public ExecutableTask getResult() {
        return execTask;
    }
}
