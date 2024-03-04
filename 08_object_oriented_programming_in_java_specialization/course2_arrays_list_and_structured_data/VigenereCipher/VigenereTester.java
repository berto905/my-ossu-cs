import edu.duke.*;
import java.util.*;

public class VigenereTester {
    public void testVigenereCipher() {
        int[] key = {17, 14, 12, 4};
        VigenereCipher vc = new VigenereCipher(key);
        FileResource fr = new FileResource("data/titus-small.txt");
        String text = fr.asString();
        String encrypted = vc.encrypt(text);
        String decrypted = vc.decrypt(encrypted);
        System.out.println();
        System.out.println(encrypted);
        System.out.println(decrypted);
    }
    
    public void testSliceString() {
        int[] key = {17, 14, 12, 4};
        VigenereBreaker vb = new VigenereBreaker();
        System.out.println(vb.sliceString("abcdefghijklm", 0, 3));
        System.out.println(vb.sliceString("abcdefghijklm", 1, 3));
        System.out.println(vb.sliceString("abcdefghijklm", 2, 3));
        System.out.println(vb.sliceString("abcdefghijklm", 0, 4));
        System.out.println(vb.sliceString("abcdefghijklm", 1, 4));
        System.out.println(vb.sliceString("abcdefghijklm", 2, 4));
        System.out.println(vb.sliceString("abcdefghijklm", 3, 4));
        System.out.println(vb.sliceString("abcdefghijklm", 0, 5));
        System.out.println(vb.sliceString("abcdefghijklm", 1, 5));
        System.out.println(vb.sliceString("abcdefghijklm", 2, 5));
        System.out.println(vb.sliceString("abcdefghijklm", 3, 5));
        System.out.println(vb.sliceString("abcdefghijklm", 4, 5));
    }
    
    public void testTryKeyLength() {
        VigenereBreaker vb = new VigenereBreaker();
        FileResource fr = new FileResource("data/secretmessage2.txt");
        String encrypted = fr.asString();
        FileResource dict = new FileResource("dictionaries/English");
        HashSet<String> dictSet = vb.readDictionary(dict);
        int[] key = vb.tryKeyLength(encrypted, 38, 'e');
        System.out.println(Arrays.toString(key));
        System.out.println(vb.countWords(encrypted, dictSet));
    }
    
    public void testBreakVigenere() {
        VigenereBreaker vb = new VigenereBreaker();
        vb.breakVigenere();
        
    }
    
    public void testReadDictionary() {
        VigenereBreaker vb = new VigenereBreaker();
        FileResource fr = new FileResource("dictionaries/German");
        HashSet<String> dictSet = vb.readDictionary(fr);
        int i = 0;
        for (String word : dictSet) {
            i += 1;
            if (i > 5) break;
            System.out.println(word);
        }
    }
    
    public void testCountWords() {
        VigenereBreaker vb = new VigenereBreaker();
        FileResource fr = new FileResource("dictionaries/English");
        HashSet<String> dictSet = vb.readDictionary(fr);
        System.out.println(vb.countWords("In the VigenereBreaker class, write " +
                                         "the public method breakForLanguage",
                                          dictSet));
    }
    
    public void testBreakForLanguage() {
        VigenereBreaker vb = new VigenereBreaker();
        FileResource dict = new FileResource("dictionaries/English");
        HashSet<String> dictSet = vb.readDictionary(dict);
        FileResource text = new FileResource("data/athens_keyflute.txt");
        String encrypted = text.asString();
        String decrypted = vb.breakForLanguage(encrypted, dictSet);
        System.out.println(decrypted);
    }
    
    public void testMostCommonChar() {
        VigenereBreaker vb = new VigenereBreaker();
        FileResource dict = new FileResource("dictionaries/English");
        HashSet<String> dictSet = vb.readDictionary(dict);
        // vb.mostCommonCharIn(dictSet);
        System.out.println(vb.mostCommonCharIn(dictSet));
    }
    
    public void testBreakForAllLangs() {
        VigenereBreaker vb = new VigenereBreaker();
        FileResource text = new FileResource("data/athens_keyflute.txt");
        String encrypted = text.asString();
        String[] langs = {"Danish", "Dutch", "English", "French", 
                          "German", "Italian", "Portuguese", "Spanish"};
        HashMap<String, HashSet<String>> languages = new HashMap<String, HashSet<String>>();
        for (String lang : langs) {
            FileResource fr = new FileResource("dictionaries/" + lang);
            HashSet<String> dict = vb.readDictionary(fr);
            languages.put(lang, dict);
        }
        String decrypted = vb.breakForAllLangs(encrypted, languages);
    }
}
