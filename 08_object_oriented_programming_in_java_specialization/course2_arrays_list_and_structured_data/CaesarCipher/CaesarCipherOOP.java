
/**
 * Write a description of CaesarCipherOOP here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipherOOP {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    
    public CaesarCipherOOP(int key) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        mainKey = key;
    }
    
    public String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder(input);
        for (int i=0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            int idx = alphabet.indexOf(Character.toUpperCase(currChar));
            if (idx != -1) {
                char newChar = shiftedAlphabet.charAt(idx);
                if (Character.isLowerCase(currChar)) {
                    newChar = Character.toLowerCase(newChar);
                }
                encrypted.setCharAt(i, newChar);
            }
        }
        return encrypted.toString();
    }

    public String decrypt(String input) {
        /*
        int[] freqs = countLetters(input);
        int maxDex = maxIndex(freqs);
        mainKey = maxDex - 4;
        if (maxDex < 4) {
            mainKey = 26 - (4 - maxDex);
        }
        */
        CaesarCipherOOP cc = new CaesarCipherOOP(26 - mainKey);
        String message = cc.encrypt(input);
        return message;
    }
    
    
}
