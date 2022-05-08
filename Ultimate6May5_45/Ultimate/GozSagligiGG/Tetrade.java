
import java.awt.*;

public abstract class Tetrade {

    final static int CLOCK_WISE = 1;
    final static int COUNTER_CLOCK_WISE = -1;
    Point[] blocks = new Point[4];
    int ID;
    
    protected int referX;
    protected int referY;
    protected int state;
    protected int[][][] cordinates;

    private int setState(int state, int[][] board){

        for (int i = 0; i < cordinates.length; i++) {
            if(board[referY+cordinates[state][i][1]][referX+cordinates[state][i][0]]!=0){
                return -1;
            }
        }

        for (int i = 0; i < blocks.length; i++) {
            this.blocks[i].move(referX+cordinates[state][i][0], referY+cordinates[state][i][1]);
        }

        this.state= state;

        for (int i = 0; i < cordinates.length; i++) {
            if(board[1+referY+cordinates[state][i][1]][referX+cordinates[state][i][0]]!=0){
                return 1;
            }
        }
        return 0;
    }

    int canRotate(int[][] board, int direction) {
        int newState = (this.state + direction) % 4;
        if (newState==-1) newState = 3;
        
        if(this.state==1){
            int result = this.setState(newState, board);
            if(result==-1){
                this.referX++;

                result = this.setState(newState, board);
                if(result==-1){
                    this.referY++;

                    result = this.setState(newState, board);
                    if(result==-1){
                        this.referX--;
                        this.referY-=3;

                        result = this.setState(newState, board);
                        if(result==-1){
                            this.referX++;

                            result = this.setState(newState, board);
                            if(result==-1){
                                this.referX--;
                                this.referY+=2;

                            }
                        }
                    }
                }
            }

            return result;
        }
        else if(this.state==3){
            int result = this.setState(newState, board);
            if(result==-1){
                this.referX--;

                result = this.setState(newState, board);
                if(result==-1){
                    this.referY++;

                    result = this.setState(newState, board);
                    if(result==-1){
                        this.referX++;
                        this.referY-=3;

                        result = this.setState(newState, board);
                        if(result==-1){
                            this.referX--;

                            result = this.setState(newState, board);
                            if(result==-1){
                                this.referX++;
                                this.referY+=2;
                                
                            }
                        }
                    }
                }
            }

            return result;
        }
        else if(newState==1){
            int result = this.setState(newState, board);
            if(result==-1){
                this.referX--;

                result = this.setState(newState, board);
                if(result==-1){
                    this.referY--;

                    result = this.setState(newState, board);
                    if(result==-1){
                        this.referX++;
                        this.referY+=3;

                        result = this.setState(newState, board);
                        if(result==-1){
                            this.referX--;

                            result = this.setState(newState, board);
                            if(result==-1){
                                this.referX++;
                                this.referY-=2;
                                
                            }
                        }
                    }
                }
            }

            return result;
        }
        else if(newState==3){
            int result = this.setState(newState, board);
            if(result==-1){
                this.referX++;

                result = this.setState(newState, board);
                if(result==-1){
                    this.referY--;

                    result = this.setState(newState, board);
                    if(result==-1){
                        this.referX--;
                        this.referY+=3;

                        result = this.setState(newState, board);
                        if(result==-1){
                            this.referX++;

                            result = this.setState(newState, board);
                            if(result==-1){
                                this.referX--;
                                this.referY-=2;
                                
                            }
                        }
                    }
                }
            }
            return result;
        }

        return -1;
    }

    public int canMove(int[][] board){

        this.referY++;
        int result = this.setState(state, board);
        if(result==-1){
            this.referY--;
            for (int j = 0; j < 4; j++) {
                Point xaj = this.blocks[j];
                board[(int)(xaj.getY())][(int)(xaj.getX())] = this.ID;
            }
        }
        return result;
    }

    public int canMoveHorizon(int[][] board, int direction) {
        this.referX+=direction;
        int result = this.setState(state, board);
        if(result==-1){
            this.referX-=direction;
        }

        return result;
    }

    public int getID() {
        return ID;
    }

}
