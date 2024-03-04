/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class Part1 {
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
        dna = dna.toUpperCase();
        int startIndex = dna.indexOf("ATG", where);
        if (startIndex == -1) {
            return "";
        }
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int minIndex = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));
        if (minIndex == dna.length()) { 
            return "";
        }
        else {
            return dna.substring(startIndex, minIndex + 3);
        }
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
    
    public StorageResource getAllGenes(String dna) {
        dna = dna.toUpperCase();
        // create an empty StorageResource, geneList
        StorageResource geneList = new StorageResource();
        // set startIndex to 0
        int startIndex = 0;
        while (true) {
            String gene = findGene(dna, startIndex);
            if (gene.isEmpty()) {
                break;
            }
            geneList.add(gene);
            startIndex = dna.indexOf(gene, startIndex) + gene.length();
        }
        return geneList;
    }
    
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
    
    public int countCTG(String dna) {
        return countString("CTG", dna);
    }
    
    public void processGenes(StorageResource sr) {
        StorageResource count60 = new StorageResource();
        StorageResource cg35 = new StorageResource();
        int longestGene = 0;
             
        for (String s : sr.data()) {
            if (s.length() > 60) {
                count60.add(s);
            }
            if (cgRatio(s) > 0.35) {
                cg35.add(s);    
            }
            if (s.length() > longestGene) {
                longestGene = s.length();
            }
        }
        
        System.out.println("Strings with longer than 60 characters:");
        for (String gene : count60.data()) {
            System.out.println(gene);
        }
        System.out.println("Total: " + count60.size());    
        System.out.println();
        
        System.out.println("Strings with C-G ratio is higher than 3.5:");
        for (String gene : cg35.data()) {
            System.out.println(gene);
        }
        System.out.println("Total: " + cg35.size());    
        System.out.println();
        
        System.out.println("The length of the longest gene: " + longestGene);    
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
        //String dna = "CGATGATCGCATGATTCATGCTTAAATAAAGCTCA";
        // printAllGenes(dna);
        
        //String dna = "acaagtttgtacaaaaaagcagaagggccgtcaaggcccaccatgcctattggatccaaagagaggccaacattttttgaaatttttaaga";
        //printAllGenes(dna.toUpperCase());
        
        //            ATGv  TAAv  ATG   v  V  TGA
        String dna = "ATGATCTAATTTATGCTGCAACGGTGAAGA";
        printAllGenes(dna);
        /*
        dna = "";
        printAllGenes(dna);
        
        System.out.println("tests finished");
        String dna = "AATGCTAACTAGCTGACTAAT";
        printAllGenes(dna);
        System.out.println("tests finished");
        */
    }

    public void testGetAllGenes() {
        // String dna = "CGATGATCGCATGATTCATGCTTAAATAAAGCTCA";
        String dna = "acaagtttgtacaaaaaagcagaagggccgtcaaggcccaccatgcctattggatccaaagagaggccaacattttttgaaatttttaaga";
        StorageResource geneList = getAllGenes(dna.toUpperCase());
        for (String gene : geneList.data()) {
            System.out.println(gene);
        }
        System.out.println("tests finished");
    }
    
    public void testCountString() {
        System.out.println(countString("C", "ATGCCATAGC"));
        System.out.println(countString("G", "ATGCCATAG"));
        System.out.println(countString("X", "ATGCCATAG"));
    }
    
    public void testCGRatio() {
        System.out.println(cgRatio("ATGCCATAG"));
    }
    
    public void testCountCTG() {
        //System.out.println(countCTG("ATGCCATAGC"));
        //System.out.println(countCTG("ATGCCACTGAGC"));
        //System.out.println(countCTG("ACTGCCATAGCTG"));
        FileResource fr = new FileResource();
        String dna = fr.asString();
        System.out.println(countCTG(dna));
    }
    
    public void testOn() {
        //String dna = "oneAtGMyGeneOneAATGGGGTAATGATAGAACCCGGYGGGGTAGGGCTGCCCATGendOneTAAnonCodingDnaTAGTGAZZZtaaTwoATGMyGeneTwoCATGGGGTAATGATAGCCatgCCCFalseStartTAATGATGendTwoTAGnonCodingDNATAACCCThreeATGMyGeneThreeATGGGGTAATGATAGATGccendThreeTAAnonCodingDNAccTAAfalsecccFourATGMyGeneFourATGGGGTAATGATAGCendFourTAGnonCodingdnaFiveAtgMyGeneFiveATGGGGTAATGATAGCendFiveTGAnonCodingdnaSixATGmyGeneSixATATGGGGTAATGATAGAendSixTAAnoncodingdnaSevenATGMyGeneSevenCcATGGGGTAATGATAGendSeventaAnoncodingdnaEightATGmyGeneEightATGGGGTAATGATAGGGendEighttaAnoncodingdnaCcccWrongtgaCtaaCtagCCcgNineATgmyGeneNineATGGGGTAATGATAGTaaAendNineTAAnonCodingDnaCcccTenATGmyGeneTenGATGGGGTAATGATAGCCHasFakeATGFAKEatgcendTentaanonCodingDnaCtagCtganonCodingDnaxxxElevenATGmyGeneElevenCATGGGGTAATGATAGTAAxxGeneATGTAACATGTAAATGCendElevenTAAnonCodingDnaxTAGxtgaTwelveATGmyGeneTwelveCATGGGGTAATGATAGCTheLastGeneIsATGTAGendTwelvetgaATGTAG";
        FileResource fr = new FileResource();
        String dna = fr.asString();
        StorageResource genes = getAllGenes(dna);
        System.out.println("Total: " + genes.size() + " genes");
        for (String gene : genes.data()) {
            System.out.println();
            System.out.println(gene);
            System.out.println("The length is " + gene.length());
            System.out.println("The CG ratio is " + cgRatio(gene));
        }
    }
    
    public void testProcessGenes() {
        // String dna = "oneAtGMyGeneOneAATGGGGTAATGATAGAACCCGGYGGGGTAGGGCTGCCCATGendOneTAAnonCodingDnaTAGTGAZZZtaaTwoATGMyGeneTwoCATGGGGTAATGATAGCCatgCCCFalseStartTAATGATGendTwoTAGnonCodingDNATAACCCThreeATGMyGeneThreeATGGGGTAATGATAGATGccendThreeTAAnonCodingDNAccTAAfalsecccFourATGMyGeneFourATGGGGTAATGATAGCendFourTAGnonCodingdnaFiveAtgMyGeneFiveATGGGGTAATGATAGCendFiveTGAnonCodingdnaSixATGmyGeneSixATATGGGGTAATGATAGAendSixTAAnoncodingdnaSevenATGMyGeneSevenCcATGGGGTAATGATAGendSeventaAnoncodingdnaEightATGmyGeneEightATGGGGTAATGATAGGGendEighttaAnoncodingdnaCcccWrongtgaCtaaCtagCCcgNineATgmyGeneNineATGGGGTAATGATAGTaaAendNineTAAnonCodingDnaCcccTenATGmyGeneTenGATGGGGTAATGATAGCCHasFakeATGFAKEatgcendTentaanonCodingDnaCtagCtganonCodingDnaxxxElevenATGmyGeneElevenCATGGGGTAATGATAGTAAxxGeneATGTAACATGTAAATGCendElevenTAAnonCodingDnaxTAGxtgaTwelveATGmyGeneTwelveCATGGGGTAATGATAGCTheLastGeneIsATGTAGendTwelvetgaATGTAG"; 
        
        FileResource fr = new FileResource();
        String dna = fr.asString();
        // System.out.println(dna);
        
        StorageResource genes = getAllGenes(dna);
        // for (String gene : genes.data()) {
        //    System.out.println(gene);
        // }
        // System.out.println("tests finished");
        
        processGenes(genes);
    }
}
