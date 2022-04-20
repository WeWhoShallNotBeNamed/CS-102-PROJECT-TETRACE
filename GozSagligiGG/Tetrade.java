
import java.awt.*;

public abstract class Tetrade {

    final static int CLOCK_WISE = 1;
    final static int COUNTER_CLOCK_WISE = -1;
    Point[] blocks = new Point[4];
    int ID;
    
    protected int referX;
    protected int referY;
    protected int state;

    boolean setState(int state, int[][] board){
        return false;
    }

    boolean canRotate(int[][] board, int direction) {
        int result = (this.state + direction) % 4;
        if (result==-1) result = 3;
        
        if(this.state==1){
            if(!this.setState(result, board)){
                this.referX++;
                if(!this.setState(result, board)){
                    this.referY++;
                    if(!this.setState(result, board)){
                        this.referX--;
                        this.referY-=3;
                        if(!this.setState(result, board)){
                            this.referX++;
                            if(!this.setState(result, board)){
                                this.referX--;
                                this.referY+=2;
                                return false;
                            }
                        }
                    }
                }
            }
        }
        else if(this.state==3){
            if(!this.setState(result, board)){
                this.referX--;
                if(!this.setState(result, board)){
                    this.referY++;
                    if(!this.setState(result, board)){
                        this.referX++;
                        this.referY-=3;
                        if(!this.setState(result, board)){
                            this.referX--;
                            if(!this.setState(result, board)){
                                this.referX++;
                                this.referY+=2;
                                return false;
                            }
                        }
                    }
                }
            }
        }
        else if(result==1){
            if(!this.setState(result, board)){
                this.referX--;
                if(!this.setState(result, board)){
                    this.referY--;
                    if(!this.setState(result, board)){
                        this.referX++;
                        this.referY+=3;
                        if(!this.setState(result, board)){
                            this.referX--;
                            if(!this.setState(result, board)){
                                this.referX++;
                                this.referY-=2;
                                return false;
                            }
                        }
                    }
                }
            }
        }
        else if(result==3){
            if(!this.setState(result, board)){
                this.referX++;
                if(!this.setState(result, board)){
                    this.referY--;
                    if(!this.setState(result, board)){
                        this.referX--;
                        this.referY+=3;
                        if(!this.setState(result, board)){
                            this.referX++;
                            if(!this.setState(result, board)){
                                this.referX--;
                                this.referY-=2;
                                return false;
                            }
                        }
                    }
                }
            }
        }

        return true;
    }

    public boolean canMove(int[][] board){

        for (int i = this.blocks.length-1; i >= 0; i--) {

            Point block = this.blocks[i];

            if(board[(int)(block.getY()+1)][(int)(block.getX())]!=0){
                for (int j = 0; j < 4; j++) {
                    Point xaj = this.blocks[j];
                    board[(int)(xaj.getY())][(int)(xaj.getX())] = this.ID;
                }
                //refreshBoard(board);
                return false;
            }

            // if(block.getY()+1==Gorax.HEIGHT){
            //     for (int j = 0; j < 4; j++) {
            //         Point xaj = this.blocks[j];
            //         board[(int)(xaj.getY())][(int)(xaj.getX())] = this.ID;
            //     }
            //     //refreshBoard(board);
            //     return false;
            // }

        }

        for (int i = 0; i < this.blocks.length; i++) {
            Point block = this.blocks[i];
            block.y = block.y+1;
        }

        this.referY++;
        return true;
    }

    public boolean canMoveHorizon(int[][] board, int direction) {
        for (int i = 0; i < this.blocks.length; i++) {
            Point block = this.blocks[i];
            if(board[(int)(block.getY())][(int)(block.getX()+direction)]!=0){
                
                return false;
            }
            // if(block.getX()+direction==10||block.getX()+direction==-1){
                
            //     return false;
            // }
        }
        for (int i = 0; i < this.blocks.length; i++) {
            Point block = this.blocks[i];
            block.x = block.x+direction;
        }
        this.referX+=direction;
        return true;
    }

    public int getID() {
        return ID;
    }

}
