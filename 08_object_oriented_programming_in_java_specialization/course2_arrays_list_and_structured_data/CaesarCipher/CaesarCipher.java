
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class CaesarCipher {
    public String encrypt(String input, int key) {
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shifted = alphabet.substring(key) + alphabet.substring(0, key);
        for (int i=0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            int idx = alphabet.indexOf(Character.toUpperCase(currChar));
            if (idx != -1) {
                char newChar = shifted.charAt(idx);
                if (Character.isLowerCase(currChar)) {
                    newChar = Character.toLowerCase(newChar);
                }
                encrypted.setCharAt(i, newChar);
            }
        }
        return encrypted.toString();
    }
    
    public void testCaesar() {
        int key = 15;
        // FileResource fr = new FileResource();
        //String message = fr.asString();
        String message = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);
        String decrypted = encrypt(encrypted, 26-key);
        System.out.println(decrypted);
    }
    
    public boolean isEven(int num) {
        if (num % 2 == 0) {
            return true;
        } return false;
    }
    
    public String encryptTwoKeys(String input, int key1, int key2) {
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shifted1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        String shifted2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        for (int i=0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            int idx = alphabet.indexOf(Character.toUpperCase(currChar));
            if (idx != -1) {
                char newChar = ' ';
                if (isEven(i)) {
                    newChar = shifted1.charAt(idx);
                } else {
                    newChar = shifted2.charAt(idx);
                }
                if (Character.isLowerCase(currChar)) {
                    newChar = Character.toLowerCase(newChar);
                }
                encrypted.setCharAt(i, newChar);
            }
        }
        return encrypted.toString();
    }
    
    public void testEncryptTwoKeys() {
        int key1 = 8;
        int key2 = 21;
        // FileResource fr = new FileResource();
        //String message = fr.asString();
        // String message = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        // String encrypted = encryptTwoKeys(message, key1, key2);
        // System.out.println("key is " + key1 + " and " + key2 + "\n" + encrypted);
        // String decrypted = encryptTwoKeys(encrypted, 26-key1, 26-key2);
        String decrypted = encryptTwoKeys("Hfs cpwewloj loks cd Hoto kyg Cyy.", 26-14, 26-24);
        System.out.println(decrypted);
    }
}
