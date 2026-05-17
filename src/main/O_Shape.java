package Login;
import java.awt.*;

public class O_Shape extends Tetrade {

    private final int[][][] O_CORDINATES = 
    {
        {{1,0},{2,0},{1,1},{2,1}},
        {{1,0},{2,0},{1,1},{2,1}},
        {{1,0},{2,0},{1,1},{2,1}},
        {{1,0},{2,0},{1,1},{2,1}}
    };
    
    public O_Shape(){
        ID = 2;
        this.referX = 5;
        this.referY = 2;
        this.state = 0;
        this.cordinates = O_CORDINATES;
        blocks[0] = new Point(6,2);
        blocks[1] = new Point(7,2);
        blocks[2] = new Point(6,3);
        blocks[3] = new Point(7,3);
    }
}