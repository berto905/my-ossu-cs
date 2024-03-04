
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class Part3 {
    public int countString(String c, String s) {
        int count = 0;
        int index = 0;
        while (true) {
            int pos = s.indexOf(c, index);
            if (pos == -1) break;
            count += 1;
            index = pos + 1; 
        }
        return count;
    }
    
    public float cgRatio(String dna) {
        float ratio = (float) (countString("C", dna) + countString("G", dna)) / 
                      dna.length();
    return ratio;
    }
        
    public void processGenes(StorageResource sr) {
        System.out.println("Strings with longer than 9 characters:");
        int count9 = 0;
        for (String s : sr.data()) {
            if (s.length() > 9) {
                System.out.println(s);
                count9 += 1;
            }
        }
        System.out.println();
        System.out.println("Number of strings with longer than 9 characters: " + count9);    
        System.out.println();
        
        
    }
}
