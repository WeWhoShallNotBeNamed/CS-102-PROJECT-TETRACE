import java.awt.Point;

public class S_Shape extends Tetrade {

    private final int[][][] S_CORDINATES = 
    {
        {{1,0},{2,0},{0,1},{1,1}},
        {{1,0},{1,1},{2,1},{2,2}},
        {{1,1},{2,1},{0,2},{1,2}},
        {{0,0},{0,1},{1,1},{1,2}}
    };
    
    public S_Shape(){
        ID = 4;
        this.referX = 5;
        this.referY = 2;
        this.state = 0;
        this.blocks[0] = new Point(6,2);
        this.blocks[1] = new Point(7,2);
        this.blocks[2] = new Point(5,3);
        this.blocks[3] = new Point(6,3);
        this.cordinates = S_CORDINATES;
    }

}