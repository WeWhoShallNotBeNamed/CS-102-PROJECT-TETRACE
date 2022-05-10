package Login;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;



public class Single_Player extends JPanel {

    private Tetris_Label game1;

    private Timer timer1;
    private Timer rotateTimer;
    private final int STARTING_FREQUENCY = 1000;
    private int currentFrequency1;
    private boolean isSoftDropActive;
    private int softDrop = 1;
    private JLabel hold;
    private JLabel next;
    private int label_number;
    private boolean isActivated;
    User u;
    private final int PREFERRED_GAP = 21;
    Timer speedTimer;
    public Single_Player (User u, int i) {
        hold=new JLabel();
        next=new JLabel();
        this.u=u;
        Border border2=BorderFactory.createBevelBorder(BevelBorder.RAISED,Color.MAGENTA,Color.BLUE);
        TitledBorder border3=BorderFactory.createTitledBorder(border2,"NEXT");
        TitledBorder border=BorderFactory.createTitledBorder(border2,"HOLD");

        if(i==3){
            border3.setTitleColor(Color.WHITE);
            border.setTitleColor(Color.WHITE);
        }

        this.speedTimer = new Timer(20000, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                setSpeed(currentFrequency1 - 2000);
                speedTimer.restart();
            }
        });

        next.setBorder(border3);
        hold.setBorder(border);
        setSpeed(STARTING_FREQUENCY);

        hold.setBounds(430,250,130,200);
        next.setBounds(880,250,130,300);
        this.add(hold);
        this.add(next);
        this.setLayout(null);
        label_number=i;
        this.speedTimer = new Timer(20000, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                setSpeed(currentFrequency1 - 2000);
                speedTimer.restart();
            }
        });
        if(i == 2){
            speedTimer.start();
        }
        reset();
        this.grabFocus();
    }

    public void setLabel(ArrayList<Integer> temp,int number){
        if(number==1){
            game1=new Tetris_Label(temp,u);
        }
        else if(number==2){
            game1=new Story_Label(temp,u);
        }
        else if(number==3){
            game1=new Oldschool(temp,u);
        }
    }

    public void setSpeed(int speed){
        currentFrequency1 = speed;
        // if(label_number == 1 || label_number == 4 || label_number == 5){

        // }
        // else{
        //     currentFrequency1 = speed;
        // }
    }

    private void reset() {

        this.isSoftDropActive = false;

        this.currentFrequency1 = STARTING_FREQUENCY;

        this.isActivated = false;

        ArrayList<Integer> temp = makeRandomSequence();

        setLabel(temp, label_number);


        game1.setBounds(390, 100, 601, 700);

        this.add(game1);

        repaint();


        this.addKeyListener(new TetrisListener());
        this.addKeyListener(new rotationListener());
        this.addKeyListener(new softDropListener());

        this.timer1 = new Timer(currentFrequency1, new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                int result = game1.canMoveDown();
                if(result==1){
                    timer1.setInitialDelay(400);
                    timer1.restart();
                }
                if(result==-1)
                {
                    updateSequences();
                    updateCurrentFrequency();
                }
            }
        });
        timer1.setInitialDelay(1);
        this.grabFocus();
    }

    @Override
    public void paint(Graphics g) {
        // TODO Auto-generated method stub
        super.paint(g);
        paintComponents(g);
        this.grabFocus();
    }

    private class softDropListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void keyPressed(KeyEvent e)
        {
            if(e.getKeyCode() == KeyEvent.VK_DOWN && !isSoftDropActive && isActivated)
            {
                isSoftDropActive = true;
                softDrop = 10;
                softFrequency();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            // TODO Auto-generated method stub
            if(e.getKeyCode() == KeyEvent.VK_DOWN && isSoftDropActive && isActivated)
            {
                isSoftDropActive = false;
                softDrop = 1;
                softFrequency();
            }
        }
    }

    private class rotationListener implements KeyListener {

        boolean isActiveClock = true;
        boolean isActiveCounter = true;

        @Override
        public void keyTyped(KeyEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void keyPressed(KeyEvent e) {
            // TODO Auto-generated method stub
            if(isActivated)
            {
                if(e.getKeyCode() == KeyEvent.VK_UP && isActiveClock)
                {
                    isActiveClock = false;
                    if (game1.canRotate(Tetrade.CLOCK_WISE)==1)
                    {
                        timer1.setInitialDelay(400);
                        timer1.restart();
                    }
                }
                else if(e.getKeyChar()=='x' && isActiveCounter)
                {
                    isActiveCounter = false;
                    if (game1.canRotate(Tetrade.COUNTER_CLOCK_WISE)==1)
                    {
                        timer1.setInitialDelay(400);
                        timer1.restart();
                    }
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            // TODO Auto-generated method stub
            if(isActivated)
            {
                if(e.getKeyCode() == KeyEvent.VK_UP)
                {
                    isActiveClock = true;
                }
                else if(e.getKeyChar()=='x')
                {
                    isActiveCounter = true;
                }
            }
        }

    }


    private class TetrisListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void keyPressed(KeyEvent e) {
            // TODO Auto-generated method stub
            if(isActivated)
            {

                if(e.getKeyCode() == KeyEvent.VK_LEFT)
                {
                    if (game1.canMoveLeft()==1)
                    {
                        timer1.setInitialDelay(400);
                        timer1.restart();
                    }
                }
                else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
                {
                    if (game1.canMoveRight()==1)
                    {
                        timer1.setInitialDelay(400);
                        timer1.restart();
                    }
                }
                else if(e.getKeyChar()=='c')
                {
                    game1.holdPiece();
                    // repaint();
                }

            }
            else
            {
                activateBoards();
                isActivated = true;
            }

            grabFocus();

        }

        @Override
        public void keyReleased(KeyEvent e) {
            // TODO Auto-generated method stub

        }

    }

    private ArrayList<Integer> makeRandomSequence() {

        ArrayList<Integer> result = new ArrayList<Integer>();

        for (int i = 0; i < PREFERRED_GAP/7; i++) {

            ArrayList<Integer> temp = new ArrayList<Integer>();

            for (int j = 1; j < 7; j++) {
                temp.add(j);
            }

            Collections.shuffle(temp);

            for (int j = 0; j < temp.size(); j++) {
                result.add(temp.get(j));
            }
        }

        return result;
    }

    private void updateSequences() {

        if(game1.getCurrentIndexGap() < PREFERRED_GAP)
        {
            ArrayList<Integer> temp = makeRandomSequence();

            game1.addNewTetrades(temp);
        }
    }

    private void activateBoards() {
        timer1.setInitialDelay(0);
        this.timer1.start();
    }

    private void updateCurrentFrequency(){
        currentFrequency1 = STARTING_FREQUENCY * 10 / (10 + game1.getTotalLinesCleared() / 5) ;
        softFrequency();
    }

    private void softFrequency()
    {
        this.timer1.setInitialDelay(0);
        this.timer1.setDelay(currentFrequency1/softDrop);
        timer1.restart();
    }
    @Override
    public void paintComponent(Graphics g){
        BufferedImage myPicture=null;
        try {
            if(label_number==1)
                myPicture = ImageIO.read(new File("/Users/eslimranaemiroglu/Desktop/back.png"));
            else if(label_number==2)
                myPicture = ImageIO.read(new File("/Users/eslimranaemiroglu/Desktop/kizilay.png"));
            else if(label_number==3)
                myPicture = ImageIO.read(new File("/Users/eslimranaemiroglu/Desktop/old_back.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        super.paintComponent(g);
        g.drawImage(myPicture, 0,0,null);
    }
}
