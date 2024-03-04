
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class TestCaesarCipher {
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

    public String breakCaesarCipher(String input) {
        int[] freqs = countLetters(input);
        int maxDex =  maxIndex(freqs);
        int mainKey = maxDex - 4;
        if (maxDex < 4) {
            mainKey = 26 - (4 - maxDex);
        }
        System.out.println("mainKey: " + mainKey);
        CaesarCipherOOP cc = new CaesarCipherOOP(mainKey);
        return cc.decrypt(input);
    }
    
    public void simpleTests() {
        // FileResource fr = new FileResource();
        // String message = fr.asString();
        String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        CaesarCipherOOP cc = new CaesarCipherOOP(15);
        String encrypted = cc.encrypt(message);
        System.out.println("encrypted: \n" + encrypted );
        System.out.println("decrypted: \n" + cc.decrypt(encrypted));
        String directDecrypted = breakCaesarCipher(encrypted);
        System.out.println("direct decrypted: \n" + directDecrypted);
    }
}
