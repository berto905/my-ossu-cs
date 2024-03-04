
/**
 * Write a description of BatchGrayscale here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;

public class BatchImageConversion {
    public ImageResource makeGray(ImageResource inImage) {
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for (Pixel pix : outImage.pixels()) {
            Pixel inPix = inImage.getPixel(pix.getX(), pix.getY());
            int average = (inPix.getRed() + inPix.getGreen() + inPix.getBlue()) / 3;
            pix.setRed(average);
            pix.setGreen(average);
            pix.setBlue(average);
            }
        return outImage;
    }
    
    public void testMakeGray() {
        ImageResource ir = new ImageResource();
        ImageResource gray = makeGray(ir);
        gray.draw();
    }
    
    public void doSave(ImageResource img, String fname, String modifType) {
        System.out.println(fname);
        String newName = modifType + "-" + fname;
        System.out.println(newName);
        img.setFileName("assignment_results/"+ newName);
        System.out.println(img.getFileName());
        img.draw();
        img.save();
    }
    
    public void batchGrayscale() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource img = new ImageResource(f);
            ImageResource gray = makeGray(img);
            String fname = img.getFileName();
            doSave(gray, fname, "gray");
        }
    }
    
    public ImageResource makeInvert(ImageResource inImage) {
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for (Pixel pix : outImage.pixels()) {
            Pixel inPix = inImage.getPixel(pix.getX(), pix.getY());
            pix.setRed(255 - inPix.getRed());
            pix.setGreen(255 - inPix.getGreen());
            pix.setBlue(255 - inPix.getBlue());
            }
        return outImage;
    }
   
    public void batchInvert() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource img = new ImageResource(f);
            String fname = img.getFileName();
            ImageResource invert = makeInvert(img);
            doSave(invert, fname, "inverted");
        }
    }
}
