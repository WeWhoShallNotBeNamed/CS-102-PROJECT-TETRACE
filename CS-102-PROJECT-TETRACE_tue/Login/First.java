package Login;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class First {
    JSlider slider;
    JFrame frame;
    JPanel panel_cont;
    JPanel login_panel;
    JPanel signup_panel;
    CardLayout cl;
    FloatControl fc;
    Clip stream;
    public First() throws IOException{
        music("theme");
        frame=new JFrame("welcome page");
        Dimension d=Toolkit. getDefaultToolkit(). getScreenSize();
        frame.setPreferredSize(d);
        JPanel panel_cont=new JPanel();
        Login login_panel=new Login();
        Signup signup_panel=new Signup();

        CardLayout cl=new CardLayout();
        panel_cont.setLayout(cl);
        panel_cont.add(login_panel,"1");
        panel_cont.add(signup_panel,"2");
        cl.show(panel_cont,"1");

        JButton b2=login_panel.getSignupButton();
        b2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(panel_cont, "2");
            }
        });

        JButton b3=signup_panel.getCancelButton();
        b3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(panel_cont, "1");
            }
        });

        JTextField user_username=signup_panel.getUser_username();
        JPasswordField user_password=signup_panel.getUser_password();
        JTextField user_email=signup_panel.getUser_email();
        JTextArea user_bio=signup_panel.getUser_bio();
        JButton b5=signup_panel.getSignupButton();
        b5.addActionListener(new ActionListener() {
            User new_user=new User();

            @Override
            public void actionPerformed(ActionEvent e) {
                new_user.username=user_username.getText();
                new_user.password=String.valueOf(user_password.getPassword());
                new_user.bio=user_bio.getText();
                new_user.e_mail=user_email.getText();
                add_database(new_user);
            }
        });


        JButton b1=login_panel.getLoginButton();
        b1.addActionListener(new ActionListener() {
            JTextField username=login_panel.getUsername();
            JPasswordField password=login_panel.getPassword();
            @Override
            public void actionPerformed(ActionEvent e) {
                    String username_cont=username.getText();
                    String password_cont=String.valueOf(password.getPassword());
    
                    User player=getExistingUser(username_cont,password_cont);
    
                    if(player!=null){
                        try {
                            panel_cont.add(new Second(player,stream,fc),"3");
                            cl.show(panel_cont, "3");
                        } catch (IOException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(login_panel, "Username or password invalid", "Try Again", JOptionPane.ERROR_MESSAGE);
                    }
                }
                
            });
       
        frame.add(panel_cont);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    public User getExistingUser(String UserName,String Password){
        User user=null;
        final String DB_URL="jdbc:mysql://localhost:3306/tetrace";
        final String USERNAME="root";
        final String PASSWORD="zeynepasel3";
        try{
            Connection conn=DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            String sql="SELECT * FROM users where UserName=? AND Password=?";
            PreparedStatement preparedStatement=conn.prepareStatement(sql);
            preparedStatement.setString(1,UserName);
            preparedStatement.setString(2,Password);

            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                user=new User();
                user.username=resultSet.getString("UserName");
                user.password=resultSet.getString("Password");
                user.e_mail=resultSet.getString("e_mail");
                user.bio=resultSet.getString("bio");
            }
            preparedStatement.close();
            conn.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        return user;
    }



    public void add_database(User player){
        String player_username=player.username;
        String player_password=player.password;
        String player_bio=player.bio;
        String player_email=player.e_mail;

        final String DB_URL="jdbc:mysql://localhost:3306/tetrace";
        final String USERNAME="root";
        final String PASSWORD="zeynepasel3";
        try{
            Connection conn=DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            String sql="INSERT INTO `users` (`UserName`, `Password`, `e_mail`, `bio`) VALUES (?,?,?,?)";
            PreparedStatement preparedStatement=conn.prepareStatement(sql);

            preparedStatement.setString(1, player_username);
            preparedStatement.setString(2, player_password);
            preparedStatement.setString(3, player_email);
            preparedStatement.setString(4, player_bio);
            
            preparedStatement.executeUpdate();

            JOptionPane.showMessageDialog(null, "registered successfully");
            conn.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    public static void main (String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run(){
                try {
                    new First();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
    }
    public void music(String song)
    {
        try
        {
            AudioInputStream input = AudioSystem.getAudioInputStream(new File("/Users/eslimranaemiroglu/Desktop/"+song+".wav"));
            stream = AudioSystem.getClip();
            stream.open(input);
            fc=(FloatControl)stream.getControl(FloatControl.Type.MASTER_GAIN);
            stream.start();
            stream.loop( Clip.LOOP_CONTINUOUSLY);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
