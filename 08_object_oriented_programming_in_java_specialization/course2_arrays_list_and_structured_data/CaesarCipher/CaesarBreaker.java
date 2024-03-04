
/**
 * Write a description of CaesarBreaks here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class CaesarBreaker {
    public int[] countLetters(String message) {
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[alph.length()];
        for (int i=0; i < message.length(); i++) {
            char ch = Character.toLowerCase(message.charAt(i));
            int idx = alph.indexOf(ch);
            if (idx != -1) {
                counts[idx] += 1;
            }
        }
        return counts;
    }
    
    public int maxIndex(int[] values) {
        int maxVal = 0;
        int maxInd = 0;
        for (int i=0; i < values.length; i++) {
            if (values[i] > maxVal) {
                maxVal = values[i];
                maxInd = i;
            }
        }
        return maxInd;
    }
    
    public void testCountLetters(){
        String alph = "abcdefghijklmnopqrstuvwxyz";
        String message = "It's so easy when every body trying to please me";
        int[] counts = countLetters(message);
        for (int i=0; i< counts.length; i++) {
            if (counts[i] != 0) {
                System.out.println(alph.charAt(i) + " : " + counts[i]);
            }
        }
        int maxInd = maxIndex(counts);
        System.out.println("Max index: " + maxInd + " (letter " + alph.charAt(maxInd) + ")"); 
    }
    
    public String decrypt(String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        String message = cc.encrypt(encrypted, 26 - dkey);
        return message;
    }
    
    public void testDecrypt() {
        String message = "It's so easy when every body trying to please me";
        int ekey = 15;
        CaesarCipher cc = new CaesarCipher();
        String encrypted = cc.encrypt(message, ekey);
        System.out.println("Encrypted: " + encrypted);
        String decrypted = decrypt(encrypted);
        if (decrypted.equals(message)) {
            System.out.println("Test is successful!");
        }
    }
    
    public String halfOfString(String message, int start) {
        String half = "";
        for (int i = start; i < message.length(); i+= 2) {
            half = half + message.charAt(i);        
        }
        return half;
    }
    
    public void testHalfString() {
        String message = "Qbkm Zgis";
        String half1 = halfOfString(message, 0);
        String half2 = halfOfString(message, 1);
        System.out.println(half1);
        System.out.println(half2);
    }
    
    public int getKey(String s) {
        int[] freqs = countLetters(s);
        int maxDex =  maxIndex(freqs);
        int key = maxDex - 4;
        if (maxDex < 4) {
            key = 26 - (4 - maxDex);
        }
        return key;
    }
    
    public void testGetKey() {
        String message = "It's so easy when every body trying to please me";
        System.out.println(getKey(message));
    }
    
    public String decryptTwoKeys(String encrypted) {
        String half1 = halfOfString(encrypted, 0);
        String half2 = halfOfString(encrypted, 1);
        int dkey1 = getKey(half1);
        int dkey2 = getKey(half2);
        System.out.println("key1: " + dkey1 + ", key2: " + dkey2);
        CaesarCipher cc = new CaesarCipher();
        String message = cc.encryptTwoKeys(encrypted, 26 - dkey1, 26 - dkey2);
        return message;
    }
    
    public void testDecryptTwoKeys() {
        String message = "Just a test string with lots of eeeeeeeeeeeeeeeees";
        int ekey1 = 23;
        int ekey2 = 2;
        CaesarCipher cc = new CaesarCipher();
        String encrypted = cc.encryptTwoKeys(message, ekey1, ekey2);
        System.out.println("Encrypted: " + encrypted);
        String decrypted = decryptTwoKeys(encrypted);
        System.out.println("Decrypted: " + decrypted);
        
        if (decrypted.equals(message)) {
            System.out.println("Test is successful!");
        } else {
            System.out.println("Test is failed!");
        }
    }
    
    public void runDecryptTwoKeys() {
        FileResource fr = new FileResource("data/mysteryTwoKeysQuiz.txt");
        String encrypted = fr.asString();
        // String encrypted = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
        String decrypted = decryptTwoKeys(encrypted);
        System.out.println(decrypted);
    }
}
