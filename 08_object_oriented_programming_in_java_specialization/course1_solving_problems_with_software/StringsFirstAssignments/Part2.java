
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Part2 {
    public String findSimpleGene(String dna, String startCodon, String stopCodon) {
        String result = "";
        
        char d = dna.charAt(0);
        if (Character.isLowerCase(d)) {
            startCodon = startCodon.toLowerCase();
            stopCodon = stopCodon.toLowerCase();
        }
        
        int start = dna.indexOf(startCodon);
        // System.out.println("start = " + start);
        if (start == -1) {
            return "Error!: no ATG";
        }
        int stop = dna.indexOf(stopCodon, start+3);
        // System.out.println("stop = " + stop);
        if (stop == -1) {
            return "Error!: no TAA";
        }
        if ((stop - start) % 3 == 0) {
            result = dna.substring(start, stop+3);
            return result;
        } else {
            return ("Error!: length of gene != 3");
        }
    }
    
    public void testSimpleGene() {
        String startCodon = "ATG";
        String stopCodon = "TAA";
        
        String[] strands = {"AATCCGATCATAA", "AACATGACTACG", "AATATGATCTGATAAATC",
                                "AATATGATCTGAATAAATC","aatatgatctgataaatc"};
        List<String> dnas = Arrays.asList(strands);
        for (String dna : dnas) {
            System.out.println("DNA strand is " + dna);
            String gene = findSimpleGene(dna, startCodon, stopCodon);
            System.out.println("Gene is " + gene);
            System.out.println();
        }
    }
}

