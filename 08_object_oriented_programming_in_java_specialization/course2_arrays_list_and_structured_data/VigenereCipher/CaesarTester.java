import edu.duke.*;

public class CaesarTester {
    public void testTransformLetter() {
        int key  = 3;
        CaesarCipher cc = new CaesarCipher(key);
        char c = 'c';
        char encrypted =  cc.encryptLetter(c);
        System.out.println("Letter '" + c + "' encrypted with key " + key + 
                           " becomes '"+ encrypted + "'");
        System.out.println("Letter '" + encrypted + "' decrypted with key " + key + 
                           " becomes '" + cc.decryptLetter(encrypted) + '"');
    }
    
    public void testTransformText() {
        System.out.println();
        CaesarCipher cc = new CaesarCipher(3);
        FileResource fr = new FileResource("data/titus-small.txt");
        String text = fr.asString();
        System.out.println("Original text:\n" + text);
        String encrypted = cc.encrypt(text);
        System.out.println("Encrypted:\n" + encrypted);
        System.out.println("Decrypted:\n" + cc.decrypt(encrypted));
    }
    
    public void testCaesarBreakerEng() {
        CaesarCracker cb = new CaesarCracker();
        FileResource fr = new FileResource("data/titus-small_key5.txt");
        String encrypted = fr.asString();
        System.out.println();
        System.out.println("Encrypted:");
        System.out.println(encrypted);
        System.out.println("Key obtained: " + cb.getKey(encrypted));
        System.out.println("Decrypted:");
        System.out.println(cb.decrypt(encrypted));
        
    }
    
    public void testCaesarBreakerOthers() {
        CaesarCracker cb = new CaesarCracker('a');
        FileResource fr = new FileResource("data/oslusiadas_key17.txt");
        String encrypted = fr.asString();
        System.out.println();
        System.out.println("Encrypted:");
        System.out.println(encrypted);
        System.out.println("Key obtained: " + cb.getKey(encrypted));
        System.out.println("Decrypted:");
        System.out.println(cb.decrypt(encrypted));
        
    }
}
