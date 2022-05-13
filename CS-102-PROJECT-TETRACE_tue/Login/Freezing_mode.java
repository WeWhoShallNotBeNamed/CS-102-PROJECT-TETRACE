package Login;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;



public class Freezing_mode extends JPanel {

    private Tetris_Label game1;
    public JButton homebf;
    private Timer freezeTimer;
    private int freezeIndex = 21;
    private Timer timer1;
    private final int STARTING_FREQUENCY = 1000;
    private int currentFrequency1;
    private boolean isSoftDropActive;
    private int softDrop = 1;

    private boolean isActivated;

    private final int PREFERRED_GAP = 21;
    User u;

    public Freezing_mode (User u) {
        Icon icon=new ImageIcon("images/home.png");
        homebf=new JButton(icon);
        this.u=u;
        this.setLayout(null);
        homebf.setBounds(1200, 750, 50, 50);
        this.add(homebf);

        reset();

        this.grabFocus();
    }

    public JButton gethbf(){
        return homebf;
    }

    private void reset() {

        this.isSoftDropActive = false;

        this.currentFrequency1 = STARTING_FREQUENCY;

        this.isActivated=true;

        ArrayList<Integer> temp = makeRandomSequence();

        this.game1 = new Tetris_Label(temp,u);


        game1.setBounds(300, 0, 601, 700);

        this.add(game1);

        repaint();

        this.addKeyListener(new TetrisListener());
        this.addKeyListener(new rotationListener());
        this.addKeyListener(new softDropListener());

        this.freezeTimer = new Timer(10000, new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                if(game1.getRecentIndex() == freezeIndex){
                    game1.canMoveDown();
                }
                game1.freeze(freezeIndex--);
            }

        });

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
                    //deisik
                    if(game1.getLastIndex() >= freezeIndex){
                        freezeTimer.restart();
                    }

                    if(game1.isOver()){
                        deactivateBoards();
                        u.diamonds=u.diamonds+u.score;
                        updateScore();

                        JOptionPane.showMessageDialog(null, "your score is"+u.score);
                    }
                    else{
                    updateSequences();
                    updateCurrentFrequency();}
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

    private void deactivateBoards() {
        this.isActivated = false;
        this.timer1.stop();
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
    public void updateScore(){
        final String DB_URL="jdbc:mysql://localhost:3306/tetrace";
                final String USERNAME="root";
                final String PASSWORD="zeynepasel3";
                try{
                    
                    Connection conn=DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
                    String sql="update users set money=? where UserName=?";
                    PreparedStatement preparedStatement=conn.prepareStatement(sql);
                    preparedStatement.setString(1,""+u.diamonds);
                    preparedStatement.setString(2,""+u.username);
                    preparedStatement.execute();
                }
                catch(Exception ex){
                    System.out.println(ex);
                }
    }
    @Override
    public void paintComponent(Graphics g){
        BufferedImage myPicture=null;
        try {
           
            myPicture = ImageIO.read(new File("images.png"));
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        super.paintComponent(g);
        g.drawImage(myPicture, 0,0,null);
    }
}



