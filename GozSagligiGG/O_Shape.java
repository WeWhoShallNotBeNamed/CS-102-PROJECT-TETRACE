import java.awt.*;

public class O_Shape extends Tetrade {

    
    public O_Shape(){
        ID = 2;
        blocks[0] = new Point(6,2);
        blocks[1] = new Point(6,3);
        blocks[2] = new Point(7,2);
        blocks[3] = new Point(7,3);
    }

    
    @Override
    boolean canRotate(int[][] board, int direction) {
        return true;
    }


}
