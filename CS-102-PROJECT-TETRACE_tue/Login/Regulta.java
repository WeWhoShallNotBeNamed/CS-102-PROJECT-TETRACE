package Login;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Regulta {
    public static void main(String[] args) {
        JFrame infow = new JFrame();
        infow.setExtendedState(JFrame.MAXIMIZED_BOTH);
        User u1=new User();
        u1.diamonds=6000;
        u1.buy(new BlackOut(u1));
        User u2=new User();
        //infow.add(new Multi_Player(u1,u2));
        infow.add(new ShopPanel(u1));
        // Yukarini commentleyip, altdakine de bakin
        //infow.add(new Single_Player());
        infow.setVisible(true);
        infow.pack();
       // infow.add(new Freezing_mode());
        infow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
