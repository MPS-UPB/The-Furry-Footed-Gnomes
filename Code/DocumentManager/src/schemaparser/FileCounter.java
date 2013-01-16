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
public class FileCounter {

    public static ArrayList<String> getFileNames(String foldername) {

        ArrayList<String> fileNames = new ArrayList<>();

        File folder = new File(foldername);
        File[] fileList = folder.listFiles();
        
        for (int i = 0; i < fileList.length; i++) {
            if (fileList[i].isFile()) {
                fileNames.add(fileList[i].getName());
            }
        }
        return fileNames;
    }
}
