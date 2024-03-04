
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class WordPlay {
    public boolean isVowel(char ch) {
        String vowel = "aeiou";
        if (vowel.indexOf(Character.toLowerCase(ch)) != -1) {
            return true;
        }
        return false;
    }
    
    public void testIsVowel() {
        System.out.println("F"+ " is " + isVowel('F'));
        System.out.println("a"+ " is " + isVowel('a'));
    }
    
    public String replaceVowels(String phrase, char ch) {
        StringBuilder replaced = new StringBuilder(phrase);
        for (int i=0; i<replaced.length(); i++) {
            if (isVowel(replaced.charAt(i))) {
                replaced.setCharAt(i, ch);
            }
        }
        return replaced.toString();
    }
    
    public void testReplaceVowels() {
        String phrase = "Hello World";
        char ch = '*';
        System.out.println(phrase + " becomes " + replaceVowels(phrase, ch));
    }
    
    public boolean isEven(int num) {
        if (num % 2 == 0) {
            return true;
        } return false;
    }
    
    public String emphasize(String phrase, char ch) {
        StringBuilder emphasized = new StringBuilder(phrase);
        for (int i=0; i<emphasized.length(); i++) {
            if (Character.toLowerCase(emphasized.charAt(i)) == 
                Character.toLowerCase(ch)) {
                if (isEven(i)) { 
                    emphasized.setCharAt(i, '*');
                } else {
                    emphasized.setCharAt(i, '+');
                }
            }
        }
        return emphasized.toString();
    }
    
    public void testEmphasize() {
        String phrase = "dna ctgaaactga";
        char ch = 'a';
        System.out.println(phrase + " becomes " + emphasize(phrase, ch));
        
        phrase = "Mary Bella Abracadabra";
        ch = 'a';
        System.out.println(phrase + " becomes " + emphasize(phrase, ch));
    }
}
