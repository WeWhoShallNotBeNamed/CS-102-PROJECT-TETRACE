import java.util.ArrayList;
import javax.swing.Timer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;
public class Story_Level2 extends Story_Label{

    public Story_Level2(ArrayList<Integer> tetrades) {
        super(tetrades);
    }

    @Override
    Image findColor(int number){
        if(number == 10){
            return (new ImageIcon("/Users/selin/Desktop/Ultimate6May5_45/Ultimate/Enemy_Blocks.png")).getImage();
        }
        return super.findColor(number);
    }
}
