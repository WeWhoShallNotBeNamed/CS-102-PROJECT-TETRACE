package Login;

import java.sql.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;


public class Login extends JPanel{
    User player;
    JButton button_login ;
    JButton signup;
    JTextField username;
    JPasswordField password;
    JButton show ;
    boolean show_hide=false;
    public Login() throws IOException{
        Icon icon=new ImageIcon("/Users/eslimranaemiroglu/Desktop/LOGIN.png");
        button_login =new JButton(icon);
    
        username=new JTextField();
        password=new JPasswordField();
        
        username.setBounds(700, 450, 200, 40);
        password.setBounds(700, 530, 200, 40);
       
        
        JLabel username_label=new JLabel("USERNAME");
        JLabel password_label=new JLabel("PASSWORD");
        username_label.setBounds(500, 460, 150, 30);
        password_label.setBounds(500, 540, 150, 30);
        username_label.setFont(new Font(Font.MONOSPACED, Font.BOLD, 24));
        password_label.setFont(new Font(Font.MONOSPACED, Font.BOLD, 24));
        BufferedImage myPicture = ImageIO.read(new File("/Users/eslimranaemiroglu/Desktop/bg_game.png"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        picLabel.setBounds(610,200,180,180);
        button_login.setBounds(820,590,78,29);
        JLabel group_label = new JLabel("Proudly created by We, Who Shall Not Be Named...");
        JLabel signup_label = new JLabel("You don't have an account?");
        signup=new JButton("Sign Up");
        signup.setBackground(Color.cyan);
        signup.setOpaque(true);
        signup.setBorderPainted(false);
        signup.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        signup.setFocusPainted(false);


        button_login.setBackground(Color.cyan);
        button_login.setOpaque(true);
        button_login.setBorderPainted(false);

        signup_label.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        signup_label.setBounds(605, 670, 250, 130);
        signup.setBounds(660, 755, 100, 30);
        group_label.setFont(new Font(Font.MONOSPACED, Font.ITALIC, 13));
        group_label.setBounds(530, 760, 500, 100);

        Icon icon2=new ImageIcon("/Users/eslimranaemiroglu/Desktop/show.png");
        show=new JButton(icon2);
        show.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(show_hide==false){
                    password.setEchoChar((char)0);
                    Icon current=new ImageIcon("/Users/eslimranaemiroglu/Desktop/hide.png");
                    show.setIcon(current);
                }
                else{
                    password.setEchoChar((Character) UIManager.get("PasswordField.echoChar"));
                    show.setIcon(icon2);
                }
                show_hide=(!show_hide);
            }
        });

        this.setLayout(null);
        this.add(picLabel);
        this.add(username_label);
        this.add(username);
        this.add(password_label);
        this.add(password);
        this.add(button_login);
        this.add(group_label);
        this.add(signup);
        this.add(signup_label);
        this.add(show);
        show.setBounds(900, 530, 40, 40);
        this.setBackground(Color.GRAY);
    }
    public JButton getLoginButton(){
        return button_login;
    }
    public JButton getSignupButton(){
        return signup;
    }
    
    public JTextField getUsername(){
        return username;
    }
    public JPasswordField getPassword(){
        return (JPasswordField)password;
    }
    public void setUser(User u){
        player=u;
    }
    public User getUser(){
       return player;
    }
    @Override
    public void paintComponent(Graphics g){
        BufferedImage myPicture=null;
        try {
            myPicture = ImageIO.read(new File("/Users/eslimranaemiroglu/Desktop/success.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        super.paintComponent(g);
        g.drawImage(myPicture, 0,0,null);
    }
}

