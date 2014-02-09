package highscore;

public class HighScore implements Comparable { //highscore class
    
    String name = new String(); //name of person who got the score
    double score; //persons score
    
    public HighScore(String name, double score) {
        this.name = name; 
        this.score = score;
    }
    @Override
    public int compareTo(Object obj) {
        HighScore otherHS = (HighScore) obj;
        if (this.score > otherHS.score) {
            return -1;
        } else if (this.score < otherHS.score) {
            return 1;
        } else { //same score
            return 0;
        }
        
    }
    
    public String getName() { 
        return this.name; 
    }
    
    public double getScore() {
        return this.score;
    }
    
    
}    