package highscore;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class HighScoreList {
    
    double space = 1;
    public List highscorearray = new ArrayList();
    
    
    public HighScoreList() {
    
    }
    public void sort() {
        Collections.sort(highscorearray);
    }
    
    public List getHighScoreArray() {
        return highscorearray;
    }
    
    public void add(String name, double score) {
        
        HighScore hs = new HighScore(name, score);
        highscorearray.add(hs);
        
        
    }
    public List NameList() {
        List name = new ArrayList();
        
        for (Iterator iterator = highscorearray.iterator(); 
                iterator.hasNext();) {
            HighScore element = (HighScore) iterator.next();
            
            List hslist = new ArrayList();
            hslist.add(element.getName());
            hslist.add(element.getScore());
            name.add(hslist);
        } 
            
        return name;
        
        
    }
    
    public void save(String filename) {
        Path path = Paths.get(filename);
        File file = new File(filename);
        Charset usAscii = Charset.forName("US-ASCII");
        if(file.exists()) {
            try (BufferedWriter bufferedWriter =
                Files.newBufferedWriter(path, usAscii, 
                        StandardOpenOption.APPEND);
                PrintWriter printWriter = 
                     new PrintWriter(bufferedWriter)) {
                for (Iterator iterator = highscorearray.iterator(); 
                    iterator.hasNext();) {
                HighScore element = (HighScore) iterator.next();
                
                printWriter.println(element.name + " " + element.score);
                //printWriter.println(element.score);
                } 
            
            } catch (IOException e) {
                System.out.println(e.toString());
            }
        } else {    
            try (BufferedWriter bufferedWriter =
                    Files.newBufferedWriter(path, usAscii, 
                            StandardOpenOption.CREATE);
                PrintWriter printWriter = 
                         new PrintWriter(bufferedWriter)) {
                for (Iterator iterator = highscorearray.iterator(); 
                        iterator.hasNext();) {
                HighScore element = (HighScore) iterator.next();
                
                printWriter.println(element.name + " " + element.score);
                //printWriter.println(element.score);
                } 
            
            } catch (IOException e) {
                System.out.println(e.toString());
            }
        }
    }    
    
    public void load(String filename) {
        Path path = Paths.get(filename);
        Charset usAscii = Charset.forName("US-ASCII");
        try (BufferedReader br = Files.newBufferedReader(path, usAscii)) {
            String hs = new String();
            while ((hs = br.readLine()) != null) {                
                String[] tokens = hs.split(" ");
                Double score = Double.parseDouble(tokens[1]);
                add(tokens[0], score);
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}