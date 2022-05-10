package Login;

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
    public int bo;
    public int cb;
    public int ss;
    public ArrayList<PowerUp> repo;

    public boolean boactive;
    public boolean cbactive;
    public boolean ssactive;

    public User(){
        repo = new ArrayList<>();
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
   
    public void buy(PowerUp pu){
        repo.add(pu);
        if(pu.getPrice()==500){
            bo++;
            score=score-500;
        }
        else if(pu.getPrice()==1500){
            cb++;
            score=score-1500;
        }
        else if(pu.getPrice()==1000){
            ss++;
            score=score-1000;
        }
    }
    public BlackOut getBlackOut(){
        for(int i=0;i<repo.size();i++){

            if(repo.get(i).getPrice()==500){
                BlackOut r=(BlackOut)repo.get(i);
                return r;
            }
        }
        return null;
    }
    public ScreenSwap getScreenSwap(){
        for(int i=0;i<repo.size();i++){

            if(repo.get(i).getPrice()==1000){
                ScreenSwap r=(ScreenSwap)repo.get(i);
                return r;
            }
        }
        return null;
    }
    public CutBack getCutBack(){
        for(int i=0;i<repo.size();i++){

            if(repo.get(i).getPrice()==1500){
                CutBack r=(CutBack)repo.get(i);
                return r;
            }
        }
        return null;
    }
}
