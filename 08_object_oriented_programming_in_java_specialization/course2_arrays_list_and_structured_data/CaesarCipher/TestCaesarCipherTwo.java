
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class TestCaesarCipherTwo {
    private int[] countLetters(String message) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int[] counts = new int[alphabet.length()];
        for (int i=0; i < message.length(); i++) {
            char ch = Character.toUpperCase(message.charAt(i));
            int idx = alphabet.indexOf(ch);
            if (idx != -1) {
                counts[idx] += 1;
            }
        }
        return counts;
    }
    
    private int maxIndex(int[] values) {
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

    private String halfOfString(String message, int start) {
        String half = "";
        for (int i = start; i < message.length(); i+= 2) {
            half = half + message.charAt(i);        
        }
        return half;
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
    
    public String breakCaesarCipher(String input) {
        String half1 = halfOfString(input, 0);
        String half2 = halfOfString(input, 1);
        int mainKey1 = getKey(half1);
        int mainKey2 = getKey(half2);
        System.out.println("mainKey1: " + mainKey1 + ", mainKey2: " + mainKey2);
        CaesarCipherTwo cc = new CaesarCipherTwo(mainKey1, mainKey2);
        String message = cc.decrypt(input);
        return message;        
    }
    
    public void simpleTests() {
        // FileResource fr = new FileResource();
        // String message = fr.asString();
        String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";      
        CaesarCipherTwo cc = new CaesarCipherTwo(21, 8);
        String encrypted = cc.encrypt(message);
        System.out.println("Encrypted: " + encrypted);
        String decrypted = cc.decrypt(encrypted);
        System.out.println("Decrypted: " + decrypted);
        
        if (decrypted.equals(message)) {
            System.out.println("Encryption test is successful!");
        } else {
            System.out.println("Encryption test fails!");
        }
        
        String directDecrypted = breakCaesarCipher(encrypted);
        System.out.println("direct decrypted: \n" + directDecrypted);
        if (directDecrypted.equals(message)) {
            System.out.println("Decryption test is successful!");
        } else {
            System.out.println("Decryption test fails!");
        }
    }
}