
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public int findStopCodon(String dna,int startIndex, String stopCodon) {
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
        while (currIndex != -1) {
            if ((currIndex - startIndex) % 3 == 0) {
                return currIndex;
            } else {
                currIndex = dna.indexOf(stopCodon, currIndex + 1);
            }
        }
        return dna.length();
    }
    
    public String findGene(String dna, int where) {
        int startIndex = dna.indexOf("ATG", where);
        if (startIndex == -1) return "";
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int minIndex = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));
        if (minIndex == dna.length()) return "";
        else return dna.substring(startIndex, minIndex + 3);
    }
    
    public void printAllGenes(String dna) {
        int startIndex = 0;
        while (true) {
            String gene = findGene(dna, startIndex);
            if (gene.isEmpty()) break;
            System.out.println(gene);
            startIndex = dna.indexOf(gene, startIndex) + gene.length();
        }
    }
    
    public int countGenes(String dna) {
        int count = 0;
        int startIndex = 0;
        while (true) {
            String gene = findGene(dna, startIndex);
            if (gene.isEmpty()) break;
            count += 1;
            startIndex = dna.indexOf(gene, startIndex) + gene.length();
        }
        return count;
    }
    
    public void testFindStopCodon() {
        //            01234567890123456789012345
        String dna = "xxxyyyzzzTAAxxxyyyzzzTAAxx";
        int dex = findStopCodon(dna, 0, "TAA");
        if (dex != 9) System.out.println("error on 9");
        dex = findStopCodon(dna, 9, "TAA");
        if (dex != 21) System.out.println("error on 21");
        dex = findStopCodon(dna, 1, "TAA");
        if (dex != 26) System.out.println("error on 26");
        dex = findStopCodon(dna, 1, "TAG");
        if (dex != 26) System.out.println("error on 26 TAG");
        System.out.println("tests finished");
    }
    
    public void testFindGene() {
        System.out.println("test 1");
        String dna = "ATGCCCGGGAAATAACCC";
        String gene = findGene(dna, 0);
        System.out.println(gene);
        if (! gene.equals("ATGCCCGGGAAATAA")) {
            System.out.println("error");
        }
        System.out.println();
        
        System.out.println("test 2");
        dna = "CCCGGGAAATAACCC";
        gene = findGene(dna, 0);
        System.out.println(gene);
        if (! gene.equals("")) {
            System.out.println("error");
        }
        System.out.println();
        
        System.out.println("test 3");
        dna = "ATGCCCGGGAAAATAACCC";
        gene = findGene(dna, 0);
        System.out.println(gene);
        if (! gene.equals("")) {
            System.out.println("error");
        }
        System.out.println();
        
        System.out.println("test 4");
        dna = "ATGCCCGGGAAATGATAACCC";
        gene = findGene(dna, 0);
        System.out.println(gene);
        if (! gene.equals("ATGCCCGGGAAATGA")) {
            System.out.println("error");
        }
        System.out.println();
        
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
        
        System.out.println("tests finished");
    }
    
    public void testCountGenes() {
        System.out.println("test 1");
        int count = countGenes("CGATGATCGCATGATTCATGCTTAAATAAAGCTCA");
        if (count != 2) {
            System.out.println("error");
        }
        System.out.println();
        
        System.out.println("test 2");
        count = countGenes("");
        if (count != 0) {
            System.out.println("error");
        }
        System.out.println();
    }
}
