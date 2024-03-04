
/**
 * Write a description of CopyOfPart2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class CopyOfPart2 {
    public String findSimpleGene(String dna, int startCodon, int stopCodon) {
        return dna.substring(startCodon, stopCodon+3);
    }

    
    public void testSimpleGene() {
        
        
        String[] strands = {"AATCCGATCATAA", "AACATGACTACG", "AATATGATCTGATAAATC",
                                "AATATGATCTGAATAAATC"};
        List<String> dnas = Arrays.asList(strands);
        
        for (String dna : dnas) {
            System.out.println("DNA strand is " + dna);
            int start = dna.indexOf("ATG");
            // System.out.println("start = " + start);
            int stop = dna.indexOf("TAA", start+3);
            // System.out.println("stop = " + stop);
            if (start == -1) {
                System.out.println("Error!: no ATG");
                System.out.println();
            } else if (stop == -1) {
                System.out.println("Error!: no TAA");
                System.out.println();
            } else if ((stop - start) % 3 != 0) {
                System.out.println("Error!: length of gene != 3");
                System.out.println();
            } else {
                String gene = findSimpleGene(dna, start, stop);
                System.out.println("Gene is " + gene);
                System.out.println();
            }
        }
    }
}

