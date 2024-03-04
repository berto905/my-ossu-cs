
/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.ArrayList;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void findUnique() {
        myWords.clear();
        myFreqs.clear();
        FileResource fr = new FileResource();
        
        for (String word : fr.words()) {
            word = word.toLowerCase();
            int idx = myWords.indexOf(word);
            if (idx == -1) {
                myWords.add(word);
                myFreqs.add(1);
            } else {
                int freq = myFreqs.get(idx);
                myFreqs.set(idx, freq + 1);
            }
        }
    }
    
    public int findIndexOfMax() {
        int maxFreq = 0;
        int maxIdx = 0;
        for (int i=0; i < myWords.size(); i++) {
            int freq = myFreqs.get(i);
            if ( freq > maxFreq) {
                maxFreq = freq;
                maxIdx = i;
            }
        }
        return maxIdx;
    }

    public void tester() {
        findUnique();
        System.out.println("Number unique words: " + myWords.size());
        for (int i=0; i < myWords.size(); i++) {
            System.out.println(myFreqs.get(i) + "\t" +  myWords.get(i) );
        }
        int maxIdx = findIndexOfMax();
        System.out.println("The word that occurs most often and its count is '"
                           + myWords.get(maxIdx) + "' (" 
                           + myFreqs.get(maxIdx) + " times)");
    }
}
