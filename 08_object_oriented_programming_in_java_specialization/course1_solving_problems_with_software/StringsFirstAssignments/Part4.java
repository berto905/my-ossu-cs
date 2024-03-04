
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class Part4 {
    public void getYoutubeLinks(URLResource ur) {
        int count = 0;
        for (String word : ur.words()) {
            // System.out.println(word);
            if (word.startsWith("href")) {
                if (word.toLowerCase().indexOf("youtube") != -1) {
                    count = count + 1;
                    int start = word.indexOf("\"");
                    //System.out.println(start);
                    int stop = word.lastIndexOf("\">");
                    //System.out.println(stop);
                    System.out.println(word.substring(start, stop+1));
                }
            }
        }
        System.out.println(count);
    }
    
    public void testLinks() {
        String url = "https://www.dukelearntoprogram.com/course2/data/manylinks.html";
        URLResource ur = new URLResource(url);
        getYoutubeLinks(ur);
    }
}
