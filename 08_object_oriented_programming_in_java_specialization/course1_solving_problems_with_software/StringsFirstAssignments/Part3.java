
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class Part3 {
    public boolean twoOccurences(String stringa, String stringb) {
        int first = stringb.indexOf(stringa);
        if (first == -1) {
            return false;
        } else if (stringb.indexOf(stringa, first + stringa.length()) == -1) {
            return false;
        } else {
            return true;
        }
    }
    
    public String lastPart(String stringa, String stringb) {
        int first = stringb.indexOf(stringa);
        if (first == -1) {
            return stringb;
        } else {
            return stringb.substring(first + stringa.length());
        }
    }
    
    public void testing() {
        String stringa = "by";
        String stringb = "A story by Abby Long";
        System.out.println(twoOccurences(stringa, stringb));
        System.out.println(lastPart(stringa, stringb));
        System.out.println();
        
        stringa = "a";
        stringb = "banana";
        System.out.println(twoOccurences(stringa, stringb));
        System.out.println(lastPart(stringa, stringb));
        System.out.println();
        
        stringa = "c";
        stringb = "banana";
        System.out.println(twoOccurences(stringa, stringb));
        System.out.println(lastPart(stringa, stringb));
        System.out.println();
        
        stringa = "atg";
        stringb = "ctgtatgta";
        System.out.println(twoOccurences(stringa, stringb));
        System.out.println(lastPart(stringa, stringb));
        System.out.println();
    }
}
