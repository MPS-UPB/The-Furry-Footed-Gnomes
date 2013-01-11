package schemaparser;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.*;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.*;
import org.xml.sax.SAXException;

/**
 * The class used to validate an xml file against a xsd.
 */
public class XmlValidator {

    private static String info;

    /**
     * @param xml	The name of the file which contains the .xml document
     * @param xsd	The name of the file which contains the .xsd document
     */
    public static boolean validateAgainstXSD(String xml, String xsd) {
        try {
            SchemaFactory factory =
                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new StreamSource(xsd));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(xml));
            info = xml + " is valid";
            return true;
        } catch (IOException | SAXException ex) {
            Logger.getLogger(XmlValidator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public String getInfo() {
        return info;
    }
}
