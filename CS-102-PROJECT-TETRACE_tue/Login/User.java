package Login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
    public int diamonds;

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
        if(price<=diamonds){
            return true;
        }
        return false;
    }
   public void setRepo(){
       for(int i=0;i<bo;i++){
           repo.add(new BlackOut(this));
       }
       for(int i=0;i<cb;i++){
        repo.add(new BlackOut(this));
        }
        for(int i=0;i<ss;i++){
            repo.add(new ScreenSwap());
        }
   }
    public void buy(PowerUp pu){
        repo.add(pu);
        if(pu.getPrice()==500){
            bo++;
            diamonds=diamonds-500;
        }
        else if(pu.getPrice()==1500){
            cb++;
            diamonds=diamonds-1500;
        }
        else if(pu.getPrice()==1000){
            ss++;
            diamonds=diamonds-1000;
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
    public void updateScore(){
        final String DB_URL="jdbc:mysql://localhost:3306/tetrace";
                final String USERNAME="root";
                final String PASSWORD="zeynepasel3";
                try{
                    
                    Connection conn=DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
                    String sql="update users set money=?, bo=?, cb=?, ss=? where UserName=?";
                    PreparedStatement preparedStatement=conn.prepareStatement(sql);
                    preparedStatement.setInt(1,this.diamonds);
                    preparedStatement.setInt(2,this.bo);
                    preparedStatement.setInt(3,this.cb);
                    preparedStatement.setInt(4,this.ss);
                    preparedStatement.setString(5,""+this.username);
                    preparedStatement.execute();
                    updateRepo();
                }
                catch(Exception ex){
                    System.out.println(ex);
                }
    }
    public void updateRepo(){
        this.repo.clear();
        for(int i=0;i<this.bo;i++){
            this.repo.add(new BlackOut(this));
        }
        for(int i=0;i<this.cb;i++){
            this.repo.add(new CutBack());
        }
        for(int i=0;i<this.ss;i++){
            this.repo.add(new ScreenSwap());
        }
    }
}
