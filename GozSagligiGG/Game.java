import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.Timer;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.*;

public class Game extends Single_Player{
    private Timer timer;
    private Tetris_Label game1;
    private int x = 0;
    private int y = 300;
    static final int HEIGHT = 63;
    static final int WEIDTH = 601;

    public Game() {
        super();
    }

    @Override
    protected void reset(){
        this.timer = new Timer(10000, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(getLabel().getLinesCleared() == 0){
                     try {
                         freeze();
                     } catch (IOException e1) {
                         // TODO Auto-generated catch block
                         e1.printStackTrace();
                     }
                 }
                 timer.restart();
            }
        });
        timer.start();
        super.reset();
    }

    public void freeze() throws IOException{
        BufferedImage ice = ImageIO.read(new File("/Users/selin/Desktop/success.jpg"));
        JLabel picLabel = new JLabel(new ImageIcon(ice));
        picLabel.setBounds(x, y, WIDTH, HEIGHT);
        x = ;
        y = ;
        this.add(picLabel);
    }
}
