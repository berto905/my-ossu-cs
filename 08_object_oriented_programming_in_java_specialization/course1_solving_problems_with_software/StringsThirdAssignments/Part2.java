
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
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
    
    public void testCountChar() {
        System.out.println(countString("C", "ATGCCATAGC"));
        System.out.println(countString("G", "ATGCCATAG"));
        System.out.println(countString("X", "ATGCCATAG"));
    }
    
    public void testCGRatio() {
        System.out.println(cgRatio("ATGCCATAG"));
    }
    
    public void testCountCTG() {
        System.out.println(countCTG("ATGCCATAGC"));
        System.out.println(countCTG("ATGCCACTGAGC"));
        System.out.println(countCTG("ACTGCCATAGCTG"));
    }
}
