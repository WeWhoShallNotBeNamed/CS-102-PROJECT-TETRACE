package Login;
import java.awt.Point;

public class L_Shape extends Tetrade {

    private final int[][][] L_CORDINATES = 
    {
        {{2,0},{0,1},{1,1},{2,1}},
        {{1,0},{1,1},{1,2},{2,2}},
        {{0,1},{1,1},{2,1},{0,2}},
        {{0,0},{1,0},{1,1},{1,2}}
    };
    
    public L_Shape(){
        ID = 6;
        this.referX = 5;
        this.referY = 2;
        this.state = 0;
        this.blocks[0] = new Point(7,2);
        this.blocks[1] = new Point(5,3);
        this.blocks[2] = new Point(6,3);
        this.blocks[3] = new Point(7,3);
        this.cordinates = L_CORDINATES;
    }
}