package Login;

import java.awt.Dimension;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Profile extends JPanel {
    JLabel username;
    ImageIcon profile_pic;
    JButton edit_profile;
    JLabel biography;
    JLabel puzzles;
    JLabel repository;
    public Profile(){
        username=new JLabel("rana");
        //profile_pic=new ImageIcon();
        Icon icon=new ImageIcon("/Users/eslimranaemiroglu/Desktop/bg_game.png");
        edit_profile=new JButton(icon);
        biography=new JLabel("cs");
        repository=new JLabel("cutback");
        this.setLayout(null);
        this.add(username);
        username.setBounds(10,10,50,50);
        this.add(edit_profile);
        username.setBounds(10,80,50,50);
        this.add(biography);
        biography.setBounds(10,100,50,50);
    }
    public static void main(String[] args) {
        JFrame frame=new JFrame("welcome page");
        Dimension d=new Dimension(500,500);
        frame.setPreferredSize(d);
        JPanel p=new Profile();
        frame.add(p);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
    }
}
