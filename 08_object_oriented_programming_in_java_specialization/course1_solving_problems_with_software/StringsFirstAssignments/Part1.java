
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Part1 {
    public String findSimpleGene(String dna) {
        String result = "";
        int start = dna.indexOf("ATG");
        // System.out.println("start = " + start);
        if (start == -1) {
            return "Error!: no ATG";
        }
        int stop = dna.indexOf("TAA", start+3);
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
        String[] strands = {"AATCCGATCATAA", "AACATGACTACG", "AATATGATCTGATAAATC",
                                "AATATGATCTGAATAAATC"};
        List<String> dnas = Arrays.asList(strands);
        for (String dna : dnas) {
            System.out.println("DNA strand is " + dna);
            String gene = findSimpleGene(dna);
            System.out.println("Gene is " + gene);
            System.out.println();
        }
    }
}
