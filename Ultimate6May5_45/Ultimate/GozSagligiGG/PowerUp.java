import javax.swing.JPanel;

public abstract class PowerUp extends JPanel{
    private int price;

    private final int MAX_TIME_ALIVE = 5;

    private int timeAlive;
    private boolean isActive;
    private boolean isOwned;

    private int numberOfOwned = 0;
    
    public PowerUp(int price){
        this.price = price;
        timeAlive = 0;
        isOwned = false;
        isActive = false;
        this.numberOfOwned += 1;
    } 
    
    //Getter methods
    public int getPrice(){
        return price;
    }
    public int getNumberOfOwned(){
        return numberOfOwned;
    }


    public void increaseTimeAlive(int timeAlive){
        this.timeAlive += timeAlive;
    }
    public boolean isAlive(){ // duration of effect of each power up
        return MAX_TIME_ALIVE >= timeAlive;
    }
    
    public boolean isActive(){
        return isActive;
    }
    public void setActive(boolean isActive){
        this.isActive = isActive;
    }
    public void setOwned( boolean isOwned ){
        this.isOwned = isOwned;
    }
   
    public boolean isOwned(){
        return isOwned;
    }
    
    public abstract void apply(JPanel panel);
}