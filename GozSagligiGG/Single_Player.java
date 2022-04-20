import javax.swing.JPanel;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.*;


    

public class Single_Player extends JPanel {

    private Tetris_Label game1;
    
    // private Timer timer;
    // private final int STARTING_FREQUENCY = 3000;
    // private int currentFrequence;

    private boolean isActivated;

    private final int PREFERRED_GAP = 21;


    public Single_Player () {
        this.setLayout(null);
        reset();

        this.grabFocus();
    }

    private void reset() {

        // this.currentFrequence = STARTING_FREQUENCY;

        this.isActivated = false;

        ArrayList<Integer> temp = makeRandomSequence();

        this.game1 = new Tetris_Label(temp);
        

        game1.setBounds(300, 0, 601, 700);

        this.add(game1);

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
                else if(e.getKeyChar()=='s')
                {
                    if(!game1.canMoveDown()){
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
                else if(e.getKeyChar()=='d')
                {
                    if (game1.canMoveRight())
                    {
                        repaint();
                    }
                }
                else if(e.getKeyChar()=='e')
                {
                    game1.holdPiece();
                    repaint();
                }
                else if(e.getKeyChar()=='q')
                {
                    if (game1.canRotate( Tetrade.COUNTER_CLOCK_WISE))
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

        if(game1.getCurrentIndexGap() < PREFERRED_GAP)
        {
            ArrayList<Integer> temp = makeRandomSequence();

            game1.addNewTetrades(temp);
        }
    }

    private void activateBoards() {
        this.game1.activate();
    }
}
