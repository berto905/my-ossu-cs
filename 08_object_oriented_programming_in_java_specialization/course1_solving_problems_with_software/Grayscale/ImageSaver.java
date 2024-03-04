
/**
 * Write a description of ImageSaver here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;

public class ImageSaver {
    public void doSave() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource img = new ImageResource(f);
            String fname = img.getFileName();
            String newName = "copy-" + fname;
            img.setFileName(newName);
            img.draw();
            img.save();
        }
    }
    
    
}
