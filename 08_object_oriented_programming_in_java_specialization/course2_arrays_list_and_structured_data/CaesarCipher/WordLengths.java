
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class WordLengths {
    public int getLength(String word) {
        int length = word.length();
        if (!Character.isLetter(word.charAt(length-1))) {
            length -= 1;
        }
        if (!Character.isLetter(word.charAt(0))) {
            length -= 1;
        }
        return length;
    }
    
    public void countWordLengths(FileResource resource, int[] counts) {
        for (String word : resource.words()) {
            int maxLength = counts.length - 1;
            int length = getLength(word);
            if (length >= maxLength) {
                counts[maxLength] += 1;
            } else {
                counts[length] += 1;
            }
        }
    }

    
    public int indexOfMax(int[] values) {
        int maxVal = 0;
        int maxInd = 0;
        for (int i=0; i < values.length; i++) {
            if (values[i] > maxVal) {
                maxVal = values[i];
                maxInd = i;
            }
        }
        return maxInd;
    }
    
    public void testCountWordLengths() {
        FileResource fr = new FileResource("data/manywords.txt");
        int[] counts = new int[31];
        countWordLengths(fr, counts);
        for (int i=0; i < counts.length; i++) {
            if (counts[i] != 0) {
                System.out.println("length " + i + ": " + counts[i]);
            }
        }
        System.out.println("Index with largest value: " + indexOfMax(counts));
    }
    
    public void tester() {
        //System.out.println(getLength("And"));
        //System.out.println(getLength("And,"));
        //System.out.println(getLength("blue-jeans,"));
        //System.out.println(getLength("\"Hello,\""));

    }
}
