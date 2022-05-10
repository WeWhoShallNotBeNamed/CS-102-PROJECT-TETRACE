package Login;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


    
public class Multi_Player extends JPanel {

    private Tetris_LabelMulti game1;
    private Tetris_LabelMulti game2;
    
    private Timer timer1;
    private Timer timer2;
    private final int STARTING_FREQUENCY = 1000;
    private int currentFrequency1;
    private int currentFrequency2;
    private boolean isSoftDropActive1;
    private boolean isSoftDropActive2;
    private int softDrop1 = 1;
    private int softDrop2 = 1;

    private boolean isActivated;

    private final int PREFERRED_GAP = 21;

    User user1;
    User user2;

    JButton blackOut;
    JButton screenSwap;
    JButton cutBack;

    JButton blackOut2;
    JButton screenSwap2;
    JButton cutBack2;

    int blackOutu1;
    int blackOutu2;

    int screenSwapu1;
    int screenSwapu2;

    int cutBacku1;
    int cutBacku2;

    JLabel u1bo;
    JLabel u2bo;
    JLabel u1cb;
    JLabel u2cb;
    JLabel u1ss;
    JLabel u2ss;

   
    public Multi_Player (User u1,User u2) {
        int blackOutu1=u1.bo;
        int blackOutu2=u2.bo;

        int screenSwapu1=u1.ss;
        int screenSwapu2=u2.ss;

        int cutBacku1=u1.cb;
        int cutBacku2=u2.cb;

        u1bo=new JLabel(String.valueOf(blackOutu1));
        u2bo=new JLabel(String.valueOf(blackOutu2));
        u1ss=new JLabel(String.valueOf(screenSwapu1));
        u2ss=new JLabel(String.valueOf(screenSwapu2));
        u1cb=new JLabel(String.valueOf(cutBacku1));
        u2cb=new JLabel(String.valueOf(cutBacku2));

        this.setLayout(null);
        Icon icon1=new ImageIcon("images/blackOut.png");
        blackOut=new JButton(icon1);
        Icon icon2=new ImageIcon("images/screenSwap.png");
        screenSwap=new JButton(icon2);
        Icon icon3=new ImageIcon("images/cutBack.png");
        cutBack=new JButton(icon3);
        
        blackOut2=new JButton(icon1);
        screenSwap2=new JButton(icon2);
        cutBack2=new JButton(icon3);

        u1bo.setBounds(80, 420, 40, 40);;
        u2bo.setBounds(1250, 420, 40, 40);;
        
        u1ss.setBounds(80, 470, 40, 40);;
        u2ss.setBounds(1250, 470, 40, 40);;
        
        u1cb.setBounds(80, 520, 40, 40);;
        u2cb.setBounds(1250, 520, 40, 40);

        blackOut.setBounds(30, 420, 40, 40);
        screenSwap.setBounds(30, 470, 40, 40);
        cutBack.setBounds(30, 520, 40, 40);

        blackOut2.setBounds(1200, 420, 40, 40);
        screenSwap2.setBounds(1200, 470, 40, 40);
        cutBack2.setBounds(1200, 520, 40, 40);

        blackOut.setOpaque(false);
        blackOut.setContentAreaFilled(false);
        blackOut.setBorderPainted(false);
        
        blackOut2.setOpaque(false);
        blackOut2.setContentAreaFilled(false);
        blackOut2.setBorderPainted(false);

        screenSwap.setOpaque(false);
        screenSwap.setContentAreaFilled(false);
        screenSwap.setBorderPainted(false);
        
        screenSwap2.setOpaque(false);
        screenSwap2.setContentAreaFilled(false);
        screenSwap2.setBorderPainted(false);

        cutBack.setOpaque(false);
        cutBack.setContentAreaFilled(false);
        cutBack.setBorderPainted(false);

        cutBack2.setOpaque(false);
        cutBack2.setContentAreaFilled(false);
        cutBack2.setBorderPainted(false);

        blackOut.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                u1.getBlackOut().isActive=true;
                u1.getBlackOut().start=System.currentTimeMillis();
            }
            
        });

        cutBack.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                u2.decreaseScore(100);
                u1.cb--;
                u1.repo.remove(u1.getCutBack());
            }
            
        });
        
        this.add(blackOut);
        this.add(blackOut2);
        this.add(screenSwap);
        this.add(screenSwap2);
        this.add(cutBack);
        this.add(cutBack2);
        this.add(u1bo);
        this.add(u2bo);
        this.add(u1ss);
        this.add(u2ss);
        this.add(u1cb);
        this.add(u2cb);

        user1=u1;
        user2=u2;
        reset();
       
        this.grabFocus();
    }

    private void reset() {

        this.isSoftDropActive1 = false;
        this.isSoftDropActive2 = false;

        this.currentFrequency1 = STARTING_FREQUENCY;
        this.currentFrequency2 = STARTING_FREQUENCY;

        this.isActivated = false;

        ArrayList<Integer> temp = makeRandomSequence();

        this.game1 = new Tetris_LabelMulti(temp,user1,user2);
        this.game2 = new Tetris_LabelMulti(temp,user2,user1);
        

        game1.setBounds(0, 0, 601, 700);
        game2.setBounds(601, 0, 601, 700);

        this.add(game1);
        this.add(game2);

        repaint();

        this.addKeyListener(new TetrisListener1());
        this.addKeyListener(new TetrisListener2());

        this.addKeyListener(new rotationListener1());
        this.addKeyListener(new rotationListener2());

        this.addKeyListener(new softDropListener1());
        this.addKeyListener(new softDropListener2());
        
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
                    updateCurrentFrequency1();
                }
            }

        });

        this.timer2 = new Timer(currentFrequency2, new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                int result = game2.canMoveDown();
                if(result==1){
                    timer2.setInitialDelay(400);
                    timer2.restart();
                }
                if(result==-1)
                {
                    updateSequences();
                    updateCurrentFrequency2();
                }
            }

        });
        timer2.setInitialDelay(1);
        this.grabFocus();
    }

    @Override
    public void paint(Graphics g) {
        // TODO Auto-generated method stub
        super.paint(g);
        paintComponents(g);
        this.grabFocus();
    }

    private class softDropListener1 implements KeyListener {
        
        @Override
        public void keyTyped(KeyEvent e) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void keyPressed(KeyEvent e) 
        {    
            if(e.getKeyCode() == KeyEvent.VK_DOWN && !isSoftDropActive1 && isActivated)
            {
                isSoftDropActive1 = true;
                softDrop1 = 10;
                softFrequency1();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            // TODO Auto-generated method stub
            if(e.getKeyCode() == KeyEvent.VK_DOWN && isSoftDropActive1 && isActivated)
            {
                isSoftDropActive1 = false;
                softDrop1 = 1;
                softFrequency1();
            }
        }
    }

    private class softDropListener2 implements KeyListener {
        
        @Override
        public void keyTyped(KeyEvent e) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void keyPressed(KeyEvent e) 
        {    
            if(e.getKeyChar() == 's' && !isSoftDropActive2 && isActivated)
            {
                isSoftDropActive2 = true;
                softDrop2 = 10;
                softFrequency2();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            // TODO Auto-generated method stub
            if(e.getKeyChar() == 's' && isSoftDropActive2 && isActivated)
            {
                isSoftDropActive2 = false;
                softDrop2 = 1;
                softFrequency2();
            }
        }
    }


    private class rotationListener1 implements KeyListener {

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
                else if(e.getKeyChar()=='o' && isActiveCounter)
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
                else if(e.getKeyChar()=='o')
                {
                    isActiveCounter = true;
                }
            }
        }
        
    }

    private class rotationListener2 implements KeyListener {

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
                if(e.getKeyChar() == 'w' && isActiveClock)
                {
                    isActiveClock = false;
                    if (game2.canRotate(Tetrade.CLOCK_WISE)==1)
                    {
                        timer2.setInitialDelay(400);
                        timer2.restart();
                    }
                }
                else if(e.getKeyChar()=='q' && isActiveCounter)
                {
                    isActiveCounter = false;
                    if (game2.canRotate(Tetrade.COUNTER_CLOCK_WISE)==1)
                    {
                        timer2.setInitialDelay(400);
                        timer2.restart();
                    }
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            // TODO Auto-generated method stub
            if(isActivated)
            {
                if(e.getKeyChar() == 'w')
                {
                    isActiveClock = true;
                }
                else if(e.getKeyChar() == 'q')
                {
                    isActiveCounter = true;
                }
            }
        }
        
    }


    private class TetrisListener1 implements KeyListener {

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

    private class TetrisListener2 implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void keyPressed(KeyEvent e) {
            // TODO Auto-generated method stub
            if(isActivated)
            {
                
                if(e.getKeyChar() == 'a')
                {
                    if (game2.canMoveLeft()==1)
                    {
                        timer2.setInitialDelay(400);
                        timer2.restart();
                    }
                }
                else if(e.getKeyChar() == 'd')
                {
                    if (game2.canMoveRight()==1)
                    {
                        timer2.setInitialDelay(400);
                        timer2.restart();
                    }
                }
                else if(e.getKeyChar()=='p')
                {
                    game2.holdPiece();
                }
                
            }

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

        if(game1.getCurrentIndexGap() < PREFERRED_GAP
            ||
           game2.getCurrentIndexGap() < PREFERRED_GAP)
        {
            ArrayList<Integer> temp = makeRandomSequence();

            game1.addNewTetrades(temp);
            game2.addNewTetrades(temp);
        }
    }

    private void activateBoards() {
        timer1.setInitialDelay(0);
        timer2.setInitialDelay(0);
        this.timer1.start();
        this.timer2.start();
    }

    private void updateCurrentFrequency1(){
        currentFrequency1 = STARTING_FREQUENCY * 10 / (10 + game1.getTotalLinesCleared() / 5) ;
        softFrequency1();
    }

    private void softFrequency1() 
    {
        this.timer1.setInitialDelay(0);
        this.timer1.setDelay(currentFrequency1/softDrop1);
        timer1.restart();
    }

    private void updateCurrentFrequency2(){
        currentFrequency2 = STARTING_FREQUENCY * 10 / (10 + game2.getTotalLinesCleared() / 5) ;
        softFrequency2();
    }

    private void softFrequency2() 
    {
        this.timer2.setInitialDelay(0);
        this.timer2.setDelay(currentFrequency2/softDrop2);
        timer2.restart();
    }
    @Override
    public void paintComponent(Graphics g){
        BufferedImage myPicture=null;
        try {
            
            myPicture = ImageIO.read(new File("/Users/eslimranaemiroglu/Desktop/old_back.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        super.paintComponent(g);
        g.drawImage(myPicture, 0,0,null);

    }
    
}
