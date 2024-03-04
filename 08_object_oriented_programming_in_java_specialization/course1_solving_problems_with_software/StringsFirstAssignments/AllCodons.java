
/**
 * Write a description of AllCodons here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class AllCodons {
    // find stopCodon starting from (startIndex + 3), currIndex
    public int findStopCodon(String dnaStr, int startIndex, String stopCodon) {
        int currIndex = dnaStr.indexOf(stopCodon, startIndex + 3);
        // as long as currIndex is not equal to -1
        while (currIndex != -1) {
            // check if currIndex - startIndex is a multiple of 3
            if ((currIndex - startIndex) % 3 == 0) {
                // if so, currIndex is answer, return it
                return currIndex;
            // if not, update currIndex, looking for stopCodon again
            } else {
                currIndex = dnaStr.indexOf(stopCodon, currIndex + 1);
            }
        }
        // if if we exit loop, no stopCodon, return dnaStr.length()
        return -1;
    }
    
    public String findGene(String dna, int where) {
        // find first occurence of "ATG", startIndex
        int startIndex = dna.indexOf("ATG", where);
        // if startIndex is -1, return ""
        if (startIndex == -1) return "";
        // use taaIndex to store findStopCodon(dna, startIndex, "TAA")
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        // use tagIndex to store findStopCodon(dna, startIndex, "TAG")
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        // use tgaIndex to store findStopCodon(dna, startIndex, "TGA")
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        // store in minIndex the smallest of these three characters
        // int minIndex = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));
        // if minIndex is dna.length()? nothing found, return ""
        // if (minIndex == dna.length()) return "";
        // otherwise answer is text from startIndex to minIndex
        // else return dna.substring(startIndex, minIndex);
        int minIndex = 0;
        if (taaIndex == -1 || (tgaIndex != -1 && tgaIndex < taaIndex)) {
            minIndex = tgaIndex;
        } else {
            minIndex = taaIndex;
        }
        if (minIndex == -1 || (tagIndex != -1 && tagIndex < minIndex)) {
            minIndex = tagIndex;
        }
        if (minIndex == -1) return "";
        else return dna.substring(startIndex, minIndex + 3);
    }
    
    public void printAllGenes(String dna) {
        // Set startIndex to 0
        int startIndex = 0;
        // As long as there are more genes after startIndex
        while (true) {
            // Find the next gene after startIndex
            String gene = findGene(dna, startIndex);
            // if no gene was found, leave this loop
            if (gene.isEmpty()) break;
            // Print that gene out
            System.out.println(gene);
            // Set start index to just past the end of the gene
            startIndex = dna.indexOf(gene, startIndex) + gene.length();
        }
    }
    
    public void testFindStop() {
        //            01234567890123456789012345
        String dna = "xxxyyyzzzTAAxxxyyyzzzTAAxx";
        int dex = findStopCodon(dna, 0, "TAA");
        if (dex != 9) System.out.println("error on 9");
        dex = findStopCodon(dna, 9, "TAA");
        if (dex != 21) System.out.println("error on 21");
        dex = findStopCodon(dna, 1, "TAA");
        if (dex != -1) System.out.println("error on 26");
        dex = findStopCodon(dna, 1, "TAG");
        if (dex != -1) System.out.println("error on 26 TAG");
        System.out.println("tests finished");
    }
    
    public void testFindGene() {
        String dna = "ATGCCCGGGAAATAACCC";
        String gene = findGene(dna, 0);
        System.out.println(gene);
        if (! gene.equals("ATGCCCGGGAAATAA")) {
            System.out.println("error");
        }
        System.out.println("tests finished");
    }
    
    public void testFindAllGenes() {
        String dna = "CGATGATCGCATGATTCATGCTTAAATAAAGCTCA";
        printAllGenes(dna);
        //     ATGv  TAAv  ATG   v  V  TGA
        dna = "ATGATCTAATTTATGCTGCAACGGTGAAGA";
        printAllGenes(dna);
        
        dna = "";
        printAllGenes(dna);
    }
}