import java.awt.Graphics;

import javax.swing.JPanel;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;

public class Multi_Player extends JPanel {

    private Tetris_Label game1;
    private Tetris_Label game2;
    
    // private Timer timer;
    // private final int STARTING_FREQUENCY = 3000;
    // private int currentFrequence;

    private boolean isActivated;

    private final int PREFERRED_GAP = 21;


    public Multi_Player () {
        this.setLayout(null);
        reset();

        this.grabFocus();
    }

    private void reset() {

        // this.currentFrequence = STARTING_FREQUENCY;

        this.isActivated = false;

        ArrayList<Integer> temp = makeRandomSequence();

        this.game1 = new Tetris_Label(temp);
        this.game2 = new Tetris_Label(temp);
        

        game1.setBounds(0, 0, 601, 700);
        game2.setBounds(700, 0, 601, 700);

        this.add(game1);
        this.add(game2);

        repaint();

        this.addKeyListener(new TetrisListener());
        this.grabFocus();
    }

    @Override
    public void paint(Graphics g) {
        // TODO Auto-generated method stub
        super.paint(g);
        paintComponents(g);
        this.grabFocus();
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
                if(e.getKeyChar()=='w')
                {
                    if (game1.canRotate(Tetrade.CLOCK_WISE))
                    {
                        repaint();
                    }
                    
                }
                else if(e.getKeyCode()==KeyEvent.VK_UP)
                {
                    if (game2.canRotate(Tetrade.CLOCK_WISE))
                    {
                        repaint();
                    }
                    
                }
                else if(e.getKeyChar()=='s')
                {
                    if(!game1.canMoveDown()){
                        // TODO
                        updateSequences();
                    }
                    repaint();
                }
                else if(e.getKeyCode()==KeyEvent.VK_DOWN)
                {
                    if(!game2.canMoveDown()){
                        // TODO
                        updateSequences();
                    }
                    repaint();
                }
                else if(e.getKeyChar()=='a')
                {
                    if (game1.canMoveLeft())
                    {
                        repaint();
                    }
                }
                else if(e.getKeyCode()==KeyEvent.VK_LEFT)
                {
                    if (game2.canMoveLeft())
                    {
                        repaint();
                    }
                }
                else if(e.getKeyChar()=='d')
                {
                    if (game1.canMoveRight())
                    {
                        repaint();
                    }
                }
                else if(e.getKeyCode()==KeyEvent.VK_RIGHT)
                {
                    if (game2.canMoveRight())
                    {
                        repaint();
                    }
                }
                else if(e.getKeyChar()=='e')
                {
                    game1.holdPiece();
                    repaint();
                }
                else if(e.getKeyChar()=='l')
                {
                    game2.holdPiece();
                    repaint();
                }

                // STOP hehehehehe
                else if(e.getKeyChar()=='q')
                {
                    if (game1.canRotate( Tetrade.COUNTER_CLOCK_WISE))
                    {
                        repaint();
                    }
                }
                else if(e.getKeyChar()=='k')
                {
                    if (game2.canRotate( Tetrade.COUNTER_CLOCK_WISE))
                    {
                        repaint();
                    }
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

        if(
            game1.getCurrentIndexGap() < PREFERRED_GAP
            ||
            game2.getCurrentIndexGap() < PREFERRED_GAP
        ){
            ArrayList<Integer> temp = makeRandomSequence();

            game1.addNewTetrades(temp);
            game2.addNewTetrades(temp);
        }
    }

    private void activateBoards() {
        this.game1.activate();
        this.game2.activate();
    }
}
