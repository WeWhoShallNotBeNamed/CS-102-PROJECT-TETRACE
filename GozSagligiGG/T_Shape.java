import java.awt.Point;

public class T_Shape extends Tetrade {
    

    public T_Shape(){
        ID = 5;
        this.referX = 5;
        this.referY = 2;
        this.state = 0;
        this.blocks[0] = new Point(6,2);
        this.blocks[1] = new Point(5,3);
        this.blocks[2] = new Point(6,3);
        this.blocks[3] = new Point(7,3);
    }

    
    @Override
    public int setState(int state, int[][] board) {
        if(state==0){
            if(board[referY][referX+1]==0
            && board[referY+1][referX]==0
            && board[referY+1][referX+1]==0
            && board[referY+1][referX+2]==0)
            {
                this.blocks[0].move(referX+1, referY);
                this.blocks[1].move(referX, referY+1);
                this.blocks[2].move(referX+1, referY+1);
                this.blocks[3].move(referX+2, referY+1);

                this.state= state;

                if(board[referY+1][referX+1]==0
                && board[referY+2][referX]==0
                && board[referY+2][referX+1]==0
                && board[referY+2][referX+2]==0){
                    return 0;
                }
                return 1;
            }
            else return -1;
        }

        if(state==1){
            if(board[referY][referX+1]==0
            && board[referY+1][referX+1]==0
            && board[referY+1][referX+2]==0
            && board[referY+2][referX+1]==0)
            {
                this.blocks[0].move(referX+1, referY);
                this.blocks[1].move(referX+1, referY+1);
                this.blocks[2].move(referX+2, referY+1);
                this.blocks[3].move(referX+1, referY+2);

                this.state= state;

                if(board[referY+1][referX+1]==0
                && board[referY+2][referX+1]==0
                && board[referY+2][referX+2]==0
                && board[referY+3][referX+1]==0){
                    return 0;
                }
                return 1;
            }
            else return -1;
        }

        if(state==2){
            if(board[referY+1][referX]==0
            && board[referY+1][referX+1]==0
            && board[referY+1][referX+2]==0
            && board[referY+2][referX+1]==0)
            {
                this.blocks[0].move(referX, referY+1);
                this.blocks[1].move(referX+1, referY+1);
                this.blocks[2].move(referX+2, referY+1);
                this.blocks[3].move(referX+1, referY+2);

                this.state= state;

                if(board[referY+2][referX]==0
                && board[referY+2][referX+1]==0
                && board[referY+2][referX+2]==0
                && board[referY+3][referX+1]==0){
                    return 0;
                }
                return 1;
            }
            else return -1;
        }

        if(state==3){
            if(board[referY][referX+1]==0
            && board[referY+1][referX]==0
            && board[referY+1][referX+1]==0
            && board[referY+2][referX+1]==0)
            {
                this.blocks[0].move(referX+1, referY);
                this.blocks[1].move(referX, referY+1);
                this.blocks[2].move(referX+1, referY+1);
                this.blocks[3].move(referX+1, referY+2);

                this.state= state;

                if(board[referY+1][referX+1]==0
                && board[referY+2][referX]==0
                && board[referY+2][referX+1]==0
                && board[referY+3][referX+1]==0){
                    return 0;
                }
                return 1;
            }
            else return -1;
        }

        return -1;
    }


}
