import javax.swing.JFrame;
import javax.swing.JPanel;

public class Regulta {
    public static void main(String[] args) {
        JFrame infow = new JFrame();
        infow.setVisible(true);
        infow.setExtendedState(JFrame.MAXIMIZED_BOTH);
        infow.add(new Multi_Player());
        // Yukarini commentleyip, altdakine de bakin
        //infow.add(new Single_Player());
        infow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
