import java.awt.*;

public class O_Shape extends Tetrade {

    
    public O_Shape(){
        ID = 2;
        this.referX = 5;
        this.referY = 2;
        this.state = 0;
        blocks[0] = new Point(6,2);
        blocks[1] = new Point(7,2);
        blocks[2] = new Point(6,3);
        blocks[3] = new Point(7,3);
    }



    @Override
    public int setState(int state, int[][] board) {

        if(board[referY][referX+1]==0
        && board[referY][referX+2]==0
        && board[referY+1][referX+1]==0
        && board[referY+1][referX+2]==0)
        {
            this.blocks[0].move(referX+1, referY);
            this.blocks[1].move(referX+2, referY);
            this.blocks[2].move(referX+1, referY+1);
            this.blocks[3].move(referX+2, referY+1);

            this.state= state;

            if(board[referY+1][referX+1]==0
            && board[referY+1][referX+2]==0
            && board[referY+2][referX+1]==0
            && board[referY+2][referX+2]==0){
                return 0;
            }
            return 1;
        }
        else return -1;
    }
}
