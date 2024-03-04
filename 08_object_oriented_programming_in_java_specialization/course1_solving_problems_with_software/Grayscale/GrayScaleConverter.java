
/**
 * Write a description of GrayScaleConverter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;

public class GrayScaleConverter {
    public ImageResource makeGray(ImageResource inImage) {
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for (Pixel pix : outImage.pixels()) {
            Pixel inPix = inImage.getPixel(pix.getX(), pix.getY());
            // Get the average RGB value in that pixel (average)
            int average = (inPix.getRed() + inPix.getGreen() + inPix.getBlue()) / 3;
            // Set the pixel's RGB to average
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
    
    public void selectAndConvert() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource img = new ImageResource(f);
            ImageResource gray = makeGray(img);
            gray.draw();
        }
    }
} 

