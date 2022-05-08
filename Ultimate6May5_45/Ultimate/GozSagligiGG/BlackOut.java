import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Graphics;

import java.io.File;

import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.Collections;
import java.awt.event.*;

public class BlackOut extends PowerUp {
    final static int PRICE = 500;

    public BlackOut() {
        super(PRICE);
    }

    private Timer timer;
   
    @Override
    public void apply(JPanel panel){
            if(panel instanceof Multi_Player){
            this.timer = new Timer(10000, new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    timer.start();
                    blackOut();
                    timer.stop();
                }
            });
        }
    }

    public class blackLabel extends Tetris_Label{

        public blackLabel(ArrayList<Integer> tetrades) {
            super(tetrades);
            //TODO Auto-generated constructor stub
        }

        
        @Override
        Color findColor(int number) {
            // TODO Auto-generated method stub
            return super.findColor(number);
        }

    }

    public void blackOut(){
        if(number==0){
            return Color.WHITE;
        }
        else if(number==1){
            return Color.BLUE;
        }
        else if(number==2){
            return Color.YELLOW;
        }
        else if(number==3){
            return Color.RED;
        }
        else if(number==4){
            return Color.GREEN;
        }
        else if(number==5){
            return Color.MAGENTA;
        }
        else if(number==6){
            return Color.ORANGE;
        }
        else return null;
        
    }
}
