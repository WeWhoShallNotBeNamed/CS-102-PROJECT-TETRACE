import javax.swing.JFrame;

public class Story_Mode {
    public static void main(String[] args) {
        JFrame infow = new JFrame();
        infow.setVisible(true);
        infow.setExtendedState(JFrame.MAXIMIZED_BOTH);
        infow.add(new Single_Player(2));
        infow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
