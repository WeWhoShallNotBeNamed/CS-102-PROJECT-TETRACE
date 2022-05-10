package Login;
import javax.swing.JPanel;

public abstract class PowerUp extends JPanel{
    private int price;
    protected boolean isActive;
    private boolean isOwned;

    private int numberOfOwned = 0;
    
    public PowerUp(int price){
        this.price = price;
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
    
    //public abstract void apply(JPanel panel);
}