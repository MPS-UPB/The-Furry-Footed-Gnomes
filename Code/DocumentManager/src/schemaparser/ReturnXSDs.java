/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package schemaparser;
import java.io.File;
import java.util.ArrayList;
/**
 *
 * @author Vlad
 */
public class ReturnXSDs {
    
    public static ArrayList<String> getXSDs(String foldername){
    	ArrayList<String> XSDs = new ArrayList<String>();
        File folder = new File(foldername);
        File[] listOfFiles = folder.listFiles();
        for(int i=0;i<listOfFiles.length;i++){
            if(listOfFiles[i].isFile() && listOfFiles[i].getName().contains(".xsd")){
                XSDs.add(listOfFiles[i].getName());
            }
        }
        return XSDs;
    }
}
