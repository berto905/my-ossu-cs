
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.ArrayList;

public class CharactersInPlay {
    private ArrayList<String> characters;
    private ArrayList<Integer> counts;
    
    public CharactersInPlay() {
        characters = new ArrayList<String>();
        counts = new ArrayList<Integer>();
    }
    
    public void update(String person) {
        int idx = characters.indexOf(person);
        if (idx == -1) {
                characters.add(person);
                counts.add(1);
            } else {
                int count = counts.get(idx);
                counts.set(idx, count + 1);
        }
    }
    
    public void findAllCharacters() {
        characters.clear();
        counts.clear();
        FileResource fr = new FileResource();
        
        for (String line : fr.lines()) {
            int start = 0;
            while (true) {
                int idx = line.indexOf(".", start);
                if (idx == -1) {
                    break;
                } else {
                    update(line.substring(start, idx));
                    start = idx + 1;
                }
            }
            
        }
    }
    
    public void tester() {
        findAllCharacters();
        for (int i = 0; i < characters.size(); i++) {
            if (counts.get(i) > 5) {
                System.out.println(characters.get(i) + "\t" + counts.get(i));
            }
        }
        System.out.println();
        charactersWithNumParts(10, 15);
    }
    
    public void charactersWithNumParts(int num1, int num2) {
        for (int i=0; i < characters.size(); i++) {
            if (counts.get(i) >= num1 && counts.get(i) <= num2) {
                System.out.println(characters.get(i) + "\t" + counts.get(i));
            }
        }
    }
}
