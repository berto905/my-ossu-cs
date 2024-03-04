
/**
 * Write a description of CommonWords here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class CommonWords {
    public String[] getCommon() {
        FileResource resource = new FileResource("data/common.txt");
        String[] common = new String[20];
        int index = 0;
        for (String s : resource.words()) {
            common[index] = s;
            index += 1;
        }
        return common;
    }
    
    public void countWords(FileResource fr, String[] common, int[] counts) {
        for (int k=0; k < common.length; k++) {
            for(String s : fr.words()) {
                if (common[k].equals(s.toLowerCase())) {
                    counts[k] += 1;
                }
            }
        }
    }
    
    void countShakespeare() {
        String[] plays = {"caesar.txt", "errors.txt", "hamlet.txt",
                          "likeit.txt", "macbeth.txt", "romeo.txt"};
        String[] common = getCommon();
        int[] counts = new int[common.length];
        for (int k=0; k < plays.length; k++) {
            FileResource resource = new FileResource("data/" + plays[k]);
            countWords(resource, common, counts);
            System.out.println("done with " + plays[k]);
        }
        
        for (int k=0; k < common.length; k++) {
            System.out.println(common[k] + "=\t" + counts[k]);
        }
    }
}
