package Login;
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

public class ScreenSwap extends PowerUp {
    final static int PRICE = 1000;
    private Timer timer;

    public ScreenSwap() {
        super(PRICE);
    }

    @Override
    public void apply(JPanel panel) {
        if(panel instanceof Multi_Player){
            this.timer = new Timer(10000, new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {                    
                    stopScreenSwap();
                }
            });
            timer.stop();
        }
    } 
    public void stopScreenSwap(){
        
    }
}
