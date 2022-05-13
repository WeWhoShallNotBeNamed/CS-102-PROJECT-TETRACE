package Login;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Signup extends JPanel{
    JTextField username;
    JPasswordField password;
    JTextArea bio;
    JTextField email;
    JButton signup;
    JButton cancel;
    public Signup(){
        cancel=new JButton("cancel");
        username=new JTextField();
        password=new JPasswordField();
        
        username.setBounds(640, 100, 170, 30);
        password.setBounds(640, 150, 170, 30);
       
        bio=new JTextArea(2,2);
        email=new JTextField();
        
        bio.setBounds(640, 250, 170, 50);
        email.setBounds(640, 200, 170, 30);

        JLabel username_label=new JLabel("Username");
        JLabel password_label=new JLabel("Password");
        JLabel email_label=new JLabel("e-mail");
        JLabel bio_label=new JLabel("Biography");
        username_label.setBounds(540, 100, 100, 30);
        password_label.setBounds(540, 150, 100, 30);
        username_label.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
        password_label.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
        
        email_label.setBounds(540, 200, 100, 30);
        bio_label.setBounds(540, 250, 100, 30);
        email_label.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
        bio_label.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
        
        JLabel group_label = new JLabel("Proudly created by We, Who Shall Not Be Named...");
        
        signup=new JButton("Sign Up");
        signup.setBackground(Color.cyan);
        signup.setOpaque(true);
        signup.setBorderPainted(false);
        signup.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        signup.setFocusPainted(false);

        cancel.setBackground(Color.cyan);
        cancel.setOpaque(true);
        cancel.setBorderPainted(false);
        cancel.setBounds(580, 415, 100, 20);
        cancel.setFocusPainted(false);
       
        signup.setBounds(710, 320, 100, 30);
        group_label.setFont(new Font(Font.MONOSPACED, Font.ITALIC, 10));
        group_label.setBounds(500, 400, 500, 100);
        this.setLayout(null);
        this.add(username_label);
        this.add(username);
        this.add(password_label);
        this.add(password);
        this.add(email_label);
        this.add(email);
        this.add(bio);
        this.add(bio_label);
        this.add(group_label);
        this.add(signup);
        this.add(cancel);
        
        this.setBackground(Color.GRAY);
    }
    public JButton getSignupButton(){
        return signup;
    }
    public JButton getCancelButton(){
        return cancel;
    }
    public JTextField getUser_username(){
        return username;
    }
    public JPasswordField getUser_password(){
        return password;
    }
    public JTextField getUser_email(){
        return email;
    }
    public JTextArea getUser_bio(){
        return bio;
    }
    @Override
    public void paintComponent(Graphics g){
        BufferedImage myPicture=null;
        try {
            myPicture = ImageIO.read(new File("images/success.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        super.paintComponent(g);
        g.drawImage(myPicture, 0,0,null);
    }
}