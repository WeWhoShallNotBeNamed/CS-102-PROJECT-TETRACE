package Login;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import java.awt.*;

public class Oldschool extends Tetris_Label{

    public Oldschool(ArrayList<Integer> tetrades, User u) {
        super(tetrades,u);
    }

    @Override
    Image findColor(int number){
        if(number == 0){
            return (new ImageIcon("images/Old-9.png")).getImage(); //white
        }
        else if(number == 1){
            return (new ImageIcon("images/Old-10.png")).getImage(); //Titan-1.jpg
        }
        else if(number == 2){
            return (new ImageIcon("images/Old-11.png")).getImage(); //Titan-2.jpg
        }
        else if(number == 3){
            return (new ImageIcon("images/Old-12.png")).getImage(); //Titan-3.jpg
        }
        else if(number == 4){
            return (new ImageIcon("images/Old-13.png")).getImage(); //Titan-4.jpg
        }
        else if(number == 5){
            return (new ImageIcon("images/Old-14.png")).getImage(); //Titan-5.jpg
        }
        else if(number == 6){
            return (new ImageIcon("images/Old-15.png")).getImage(); // daha yok Titan-6.jpg
        }
        else{
            return null;
        }
    }

}
