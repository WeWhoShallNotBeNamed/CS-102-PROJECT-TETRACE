package Login;
import java.awt.image.BufferedImage;

public class User {
    public String username;
    public String password;
    public String e_mail;
    public String bio;
    // public Powerup[] repository;
    // public Puzzle[] puzzles;
    public BufferedImage profile_pic;// or the path so you might want to store a string
    public int score;
    public User(){
        
    }
    public void setProfilePic(){

    }
    public void increaseScore(int x){
        score+=x;
    }
    public void decreaseScore(int x){
        score-=x;
    }
}
