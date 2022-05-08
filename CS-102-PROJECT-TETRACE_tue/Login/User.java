package Login;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class User {
    public static int count=0;
    public int id;
    public String username;
    public String password;
    public String e_mail;
    public String bio;
    public int choice;
    public int score;
    public int level;
    public ArrayList <PowerUp> repo;
    public User(){
        repo=new ArrayList<>();
        count++;
        id=count;
    }
    public void setProfilePic(){

    }
    public void increaseScore(int x){
        score+=x;
    }
    public void decreaseScore(int x){
        score-=x;
    }
    public boolean canBuy(int price){
        if(price<=score){
            return true;
        }
        return false;
    }
    public PowerUp getBlackOut(){
        for(int i=0;i<repo.size();i++){
            if(repo.get(i).getPrice()==500){
                return repo.get(i);
            }
        }
        return null;
        
    }
    public void buy(PowerUp pu){
        repo.add(pu);
    }
}
