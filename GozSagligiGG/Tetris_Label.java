


import javax.swing.Timer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.*;


public class Tetris_Label extends JLabel {

    private boolean isActivated;

    private ArrayList<Integer> tetrades;
    private Tetrade currentFigure;
    private int currentIndex;
    private int holdingTetrade;

    private int totalLinesCleared;
    private int linesCleared = -1;

    private int[][] currentMainBoard;

    private final int xBase = 240;
    private final int yBase = 25;

    static final int HEIGHT = 22;
    static final int SIDE_OF_RECTANGLE = 30;
    private final int[][] INITIAL_MAIN_BOARD =
    {
        {1,1,0,0,0,0,0,0,0,0,0,0,1,1},
        {1,1,0,0,0,0,0,0,0,0,0,0,1,1},
        {1,1,0,0,0,0,0,0,0,0,0,0,1,1},
        {1,1,0,0,0,0,0,0,0,0,0,0,1,1},
        {1,1,0,0,0,0,0,0,0,0,0,0,1,1},
        {1,1,0,0,0,0,0,0,0,0,0,0,1,1},
        {1,1,0,0,0,0,0,0,0,0,0,0,1,1},
        {1,1,0,0,0,0,0,0,0,0,0,0,1,1},
        {1,1,0,0,0,0,0,0,0,0,0,0,1,1},
        {1,1,0,0,0,0,0,0,0,0,0,0,1,1},
        {1,1,0,0,0,0,0,0,0,0,0,0,1,1},
        {1,1,0,0,0,0,0,0,0,0,0,0,1,1},
        {1,1,0,0,0,0,0,0,0,0,0,0,1,1},
        {1,1,0,0,0,0,0,0,0,0,0,0,1,1},
        {1,1,0,0,0,0,0,0,0,0,0,0,1,1},
        {1,1,0,0,0,0,0,0,0,0,0,0,1,1},
        {1,1,0,0,0,0,0,0,0,0,0,0,1,1},
        {1,1,0,0,0,0,0,0,0,0,0,0,1,1},
        {1,1,0,0,0,0,0,0,0,0,0,0,1,1},
        {1,1,0,0,0,0,0,0,0,0,0,0,1,1},
        {1,1,0,0,0,0,0,0,0,0,0,0,1,1},
        {1,1,0,0,0,0,0,0,0,0,0,0,1,1},
        {1,1,1,1,1,1,1,1,1,1,1,1,1,1},
        {1,1,1,1,1,1,1,1,1,1,1,1,1,1},
    };

    public Tetris_Label(ArrayList<Integer> tetrades)
    {
        this.currentMainBoard = INITIAL_MAIN_BOARD;
        this.totalLinesCleared = 0;
        this.tetrades = new ArrayList<>();

        this.currentIndex = -1;

        this.addNewTetrades(tetrades);

        nextTetrade();

        repaint();

    }

    public void addNewTetrades(ArrayList<Integer> addition)
    {
        for (int i = 0; i < addition.size(); i++) {
            //
            //  Very Interesting
            //  If not done like below
            //  In multiplayer, players would point to the same object
            //
            int tetrade = addition.get(i);
            this.tetrades.add(tetrade);
        }
    }

    private void nextTetrade()
    {
        // I could not fix that :(((

        currentIndex++;

        int tetrade = this.tetrades.get(this.currentIndex);

        this.currentFigure = createTetrade(tetrade);

    }

    private Tetrade createTetrade(int tetrade)
    {
        if(tetrade==1)
        {
            return new O_Shape();
        }
        else if(tetrade==2)
        {
            return new J_Shape();
        }
        else if(tetrade==3)
        {
            return new Z_Shape();
        }
        else if(tetrade==4)
        {
            return new S_Shape();
        }
        else if(tetrade==5)
        {
            return new T_Shape();
        }
        else if(tetrade==6)
        {
            return new L_Shape();
        }

        else return null;
    }


    private void refreshBoard(int[][] board) {
        int maxY = (int)(currentFigure.blocks[3].getY());
        linesCleared = 0;
        if(maxY<4) {

        }
        else {
            for (int i = maxY; i > maxY-4; i--) {
                boolean hasBlock = true;
                int[] line = board[i];
                for (int j = 2; j < 12; j++) {
                    if(hasBlock) {
                        hasBlock = !(line[j] == 0);
                    }
                }

                if(hasBlock) {
                    linesCleared++;

                }
                else{
                    board[i+linesCleared] = board[i];
                }

            }

            for (int i = maxY-4; i >= 0; i--) {
                board[i+linesCleared] = board[i];
            }

            for (int i = 0; i < linesCleared; i++) {
                board[i] = new int[14];
                board[i][0] = 1;
                board[i][1] = 1;
                board[i][12] = 1;
                board[i][13] = 1;
            }
        }

        totalLinesCleared+=linesCleared;

    }

    public int getLinesCleared(){
        return linesCleared;
    }

    @Override
    public void paint(Graphics g) {

        // TODO Auto-generated method stub
        super.paint(g);

        int[][] tempBoard = makeTempBoard();

        if(holdingTetrade!=0){
            Tetrade holding = createTetrade(holdingTetrade);
            for (int j = 0; j < holding.blocks.length; j++) {
                Point block = holding.blocks[j];
                int xCordinate = xBase + (int)(block.getX()-11)*SIDE_OF_RECTANGLE;
                int yCordinate = yBase + (int)(block.getY())*SIDE_OF_RECTANGLE + 90;

                g.setColor(findColor(holding.ID));
                g.fillRect(xCordinate, yCordinate, SIDE_OF_RECTANGLE, SIDE_OF_RECTANGLE);

                g.setColor(Color.BLACK);
                g.drawRect(xCordinate, yCordinate, SIDE_OF_RECTANGLE, SIDE_OF_RECTANGLE);

            }
        }
        for (int i = 1; i < 4; i++) {
            Tetrade tetrade = createTetrade(tetrades.get(i+currentIndex));
            for (int j = 0; j < tetrade.blocks.length; j++) {
                Point block = tetrade.blocks[j];
                int xCordinate = xBase + (int)(block.getX()+4)*SIDE_OF_RECTANGLE;
                int yCordinate = yBase + (int)(block.getY())*SIDE_OF_RECTANGLE + 90*i;

                g.setColor(findColor(tetrade.ID));
                g.fillRect(xCordinate, yCordinate, SIDE_OF_RECTANGLE, SIDE_OF_RECTANGLE);

                g.setColor(Color.BLACK);
                g.drawRect(xCordinate, yCordinate, SIDE_OF_RECTANGLE, SIDE_OF_RECTANGLE);

            }
        }

        for (int i = 0; i < 22; i++) {

            int yCordinate = yBase + SIDE_OF_RECTANGLE*i;
            for (int j = 0; j < 10; j++) {

                int xCordinate = xBase + SIDE_OF_RECTANGLE*(j-2);

                g.setColor(findColor(tempBoard[i][j]));

                g.fillRect(xCordinate, yCordinate, SIDE_OF_RECTANGLE, SIDE_OF_RECTANGLE);

                g.setColor(Color.BLACK);
                g.drawRect(xCordinate, yCordinate, SIDE_OF_RECTANGLE, SIDE_OF_RECTANGLE);
            }

        }
    }

    int[][] makeTempBoard() {
        int[][] temp = new int[22][10];

        for (int i = 0; i < 22; i++) {
            for (int j = 0; j < 10; j++) {
                temp[i][j] = this.currentMainBoard[i][j+2];
            }
        }

        for (int i = 0; i < currentFigure.blocks.length; i++) {
            Point block = currentFigure.blocks[i];
            temp[(int)(block.getY())][(int)(block.getX())-2] = currentFigure.getID();
        }

        // for (int i = 0; i < temp.length; i++) {
        //     System.out.println(Arrays.toString(temp[i]));
        // }
        // System.out.println("---------------------------");

        return temp;
    }

    Color findColor (int number) {
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

    public int canRotate(int direction) {
        int result = currentFigure.canRotate(currentMainBoard, direction);
        if (result!=-1)
        {
            repaint();
        }
        return result;
    }

    public int canMoveDown()
    {
        int result = currentFigure.canMove(currentMainBoard);
        if(result==-1){

            refreshBoard(currentMainBoard);

            nextTetrade();
        }

        repaint();
        return result;
    }

    public int canMoveLeft() {
        int result = currentFigure.canMoveHorizon(currentMainBoard,-1);
        if (result!=-1)
        {
            repaint();
        }
        return result;
    }

    public int canMoveRight() {
        int result = currentFigure.canMoveHorizon(currentMainBoard,+1);
        if (result!=-1)
        {
            repaint();
        }
        return result;
    }

    public void holdPiece() {
        if(holdingTetrade==0)
        {
            holdingTetrade = tetrades.get(currentIndex);

            nextTetrade();
        }
        else
        {
            int temp = holdingTetrade;
            holdingTetrade = tetrades.get(currentIndex);
            tetrades.set(currentIndex, temp);
            currentFigure = createTetrade(temp);
        }

        // Can we use this.getParent().paintAll ???
        repaint();
    }

    public int getCurrentIndexGap() {
        return this.tetrades.size() - this.currentIndex;
    }

    public int getTotalLinesCleared() {
        return totalLinesCleared;
    }
}
