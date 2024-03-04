import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder slice = new StringBuilder();
        for (int i=whichSlice; i < message.length(); i+=totalSlices) {
            slice.append(message.charAt(i));
        }
        return slice.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        for (int i=0; i< klength; i++) {
            CaesarCracker cb = new CaesarCracker();
            String slice = sliceString(encrypted, i, klength);
            key[i] = cb.getKey(slice);
        }
        return key;
    }

    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> dictSet = new HashSet<String>();
        for (String line : fr.lines()) {
            dictSet.add(line.toLowerCase());
        }
        return dictSet;
    }
    
    public int countWords(String message, HashSet<String> dictionary) {
        int validWords = 0;
        String[] words = message.split("\\W+");
        for (String word : words) {
            if (dictionary.contains(word.toLowerCase())) {
                validWords += 1;
            }
        }
        return validWords;
    }
    
    public char mostCommon(HashMap<Character, Integer> countMap) {
        int max = 0;
        char most = ' ';
        for (char c : countMap.keySet()) {
            int thisCount = countMap.get(c);
            if (thisCount > max) {
                max = thisCount;
                most = c;
            }
        }
        return most;
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary) {
        HashMap<Character, Integer> charCount = new HashMap<Character, Integer>();
        for (String word : dictionary) {
                for (char c : word.toCharArray()) {
                    if (!charCount.containsKey(c)) {
                        charCount.put(c, 1);
                    } else {
                        int freq = charCount.get(c);
                        charCount.put(c, freq + 1);
                    }
                }
            }
        return mostCommon(charCount);
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        int maxCountWords = 0;
        int bestKLength = 0;
        String bestDecrypt = null;
        char mostCommon = mostCommonCharIn(dictionary);
        for (int i=1; i<=100; i++) {
            int[] key = tryKeyLength(encrypted, i, mostCommon);
            VigenereCipher vc = new VigenereCipher(key);
            String thisDecrypt = vc.decrypt(encrypted);
            int thisCount = countWords(thisDecrypt, dictionary);
            // if (i == 38) System.out.println("klength = 38, countWords = " + thisCount);
            if (thisCount > maxCountWords) {
                maxCountWords = thisCount;
                bestKLength = i;
                bestDecrypt = thisDecrypt;
            }
        }
        System.out.println("Most common character: " + mostCommon);
        System.out.println("Max valid words: " + maxCountWords);
        System.out.println("Best kLength: " + bestKLength);
        System.out.println("Keys : " + Arrays.toString(tryKeyLength(encrypted, bestKLength, 'e'))); 
        return bestDecrypt;
    }
    
    public String breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
        int maxCountWords = 0;
        int bestKLength = 0;
        String bestLang = null;
        String bestDecrypt = null;
        for (String lang : languages.keySet()) {
            System.out.println();
            System.out.println(lang);
            HashSet<String> dict = languages.get(lang);
            String decrypt = breakForLanguage(encrypted, dict);
            int count = countWords(decrypt, dict);
            if (count > maxCountWords) {
                maxCountWords = count;
                bestLang = lang;
                bestDecrypt = decrypt;
            }
        }
        System.out.println();
        System.out.println("OVERALL RESULT");
        System.out.println("Max valid words: " + maxCountWords);
        System.out.println("Best language: " + bestLang);
        return bestDecrypt;
    }

    public void breakVigenere () {
        FileResource text = new FileResource();
        String encrypted = text.asString();
        String[] langs = {"Danish", "Dutch", "English", "French", 
                          "German", "Italian", "Portuguese", "Spanish"};
        HashMap<String, HashSet<String>> languages = new HashMap<String, HashSet<String>>();
        for (String lang : langs) {
            FileResource fr = new FileResource("dictionaries/" + lang);
            HashSet<String> dict = readDictionary(fr);
            languages.put(lang, dict);
        }
         
        String decrypted = breakForAllLangs(encrypted, languages);
        System.out.println();
        System.out.println(decrypted);
    }
    
}
