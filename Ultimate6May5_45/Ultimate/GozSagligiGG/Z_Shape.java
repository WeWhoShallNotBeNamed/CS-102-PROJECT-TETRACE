import java.awt.Point;

public class Z_Shape extends Tetrade {
    
    private final int[][][] Z_CORDINATES = 
    {
        {{0,0},{1,0},{1,1},{2,1}},
        {{2,0},{1,1},{2,1},{1,2}},
        {{0,1},{1,1},{1,2},{2,2}},
        {{1,0},{0,1},{1,1},{0,2}}
    };

    public Z_Shape(){
        ID = 3;
        this.referX = 5;
        this.referY = 2;
        this.state = 0;
        this.blocks[0] = new Point(5,2);
        this.blocks[1] = new Point(6,2);
        this.blocks[2] = new Point(6,3);
        this.blocks[3] = new Point(7,3);
        this.cordinates  = Z_CORDINATES;
    }

}