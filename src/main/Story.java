package Login;
import java.util.ArrayList;

import javax.swing.Timer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.*;

public class Story_Label extends Tetris_Label{

    public Story_Label(ArrayList<Integer> tetrades,User u) {
        super(tetrades,u);
    }

    @Override
    Image findColor(int number){
        if(number == 0){
            return (new ImageIcon("images/white_block.png")).getImage(); //white
        }
        else if(number == 1){
            return (new ImageIcon("images/Titan-10.png")).getImage(); //Titan-1.jpg
        }
        else if(number == 2){
            return (new ImageIcon("images/Titan-11.png")).getImage(); //Titan-2.jpg
        }
        else if(number == 3){
            return (new ImageIcon("images/Titan-12.png")).getImage(); //Titan-3.jpg
        }
        else if(number == 4){
            return (new ImageIcon("images/Titan-13.png")).getImage(); //Titan-4.jpg
        }
        else if(number == 5){
            return (new ImageIcon("images/Titan-14.png")).getImage(); //Titan-5.jpg
        }
        else if(number == 6){
            return (new ImageIcon("images/Titan-15.png")).getImage(); // daha yok Titan-6.jpg
        }
        return null;
    }

}
