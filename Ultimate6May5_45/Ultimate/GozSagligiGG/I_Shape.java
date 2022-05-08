import java.awt.Point;

public class I_Shape extends Tetrade{
    private final int[][][] I_CORDINATES =
    {
        {{1,0},{1,4},{2,4},{0,2}},
        {{1,0},{1,1},{5,1},{5,0}},
        {{0,1},{1,1},{2,1},{2,2}},
        {{1,0},{1,1},{0,2},{1,2}}
    };

    public I_Shape(){
        ID = 7;
        this.referX = 5;
        this.referY = 2;
        this.state = 0;
        this.blocks[0] = new Point(5,2);
        this.blocks[1] = new Point(5,3);
        this.blocks[2] = new Point(6,3);
        this.blocks[3] = new Point(7,3);
        this.cordinates = I_CORDINATES;
    }
}
