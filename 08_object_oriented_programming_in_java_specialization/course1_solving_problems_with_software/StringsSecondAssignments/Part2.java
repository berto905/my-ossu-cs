
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public int howMany(String stringa, String stringb) {
        int total = 0;
        int startIndex = 0;
        while (true) {
            int posIndex = stringb.indexOf(stringa, startIndex);
            if (posIndex == -1) break;
            total += 1;
            startIndex = posIndex + stringa.length();
        }
        return total;
    }
    
    public void testHowMany() {
        System.out.println("test1");
        System.out.println(howMany("GAA", "ATGAACGAATTGAATC"));
        System.out.println();
        
        System.out.println("test2");
        System.out.println(howMany("AA", "ATAAAA"));
        System.out.println();
        
        System.out.println("test3");
        System.out.println(howMany("AB", "ATAAAA"));
        System.out.println();
        
    }
    
    /*
    public void quiz2() {
        String dna = "CTGCCTGCATGATCGTA";
        int pos = dna.indexOf("TG");
        int count = 0;
        while (pos >= 0) {
            count = count + 1;
            pos = dna.indexOf("TG",pos);
        }
        System.out.println(count);
    }
    */
}
