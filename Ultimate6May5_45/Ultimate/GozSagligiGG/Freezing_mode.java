import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.*;



public class Freezing_mode extends JPanel {

    private Tetris_Label game1;

    private Timer freezeTimer;
    private int freezeIndex = 21;
    private Timer timer1;
    private final int STARTING_FREQUENCY = 1000;
    private int currentFrequency1;
    private boolean isSoftDropActive;
    private int softDrop = 1;

    private boolean isActivated;

    private final int PREFERRED_GAP = 21;


    public Freezing_mode () {
        this.setLayout(null);
        reset();

        this.grabFocus();
    }

    private void reset() {

        this.isSoftDropActive = false;

        this.currentFrequency1 = STARTING_FREQUENCY;

        this.isActivated = false;

        ArrayList<Integer> temp = makeRandomSequence();

        this.game1 = new Tetris_Label(temp);


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
                    if(game1.getLastIndex()>=freezeIndex){
                        freezeTimer.restart();
                    }
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
        this.freezeTimer.start();
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
}

