package Login;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.print.attribute.standard.Media;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class First {
    JFrame frame;
    JPanel panel_cont;
    JPanel login_panel;
    JPanel signup_panel;
    CardLayout cl;
    public First() throws IOException{
        playMusic("/Users/eslimranaemiroglu/Desktop/theme.au");
        frame=new JFrame("welcome page");
        Dimension d=Toolkit. getDefaultToolkit(). getScreenSize();
        frame.setPreferredSize(d);
        JPanel panel_cont=new JPanel();
        Login login_panel=new Login();
        Signup signup_panel=new Signup();
        Home home_panel=new Home();
        Multi multi_panel=new Multi();
        Single single_panel=new Single();
        Creative creative_panel=new Creative();
        HowToPlayPanel hPanel=new HowToPlayPanel();
        SettingsPanel sPanel=new SettingsPanel();

        CardLayout cl=new CardLayout();
        panel_cont.setLayout(cl);
        panel_cont.add(login_panel,"1");
        panel_cont.add(signup_panel,"2");
        panel_cont.add(home_panel,"3");
        panel_cont.add(single_panel,"4");
        panel_cont.add(multi_panel,"5");
        panel_cont.add(creative_panel,"6");
        panel_cont.add(hPanel,"7");
        panel_cont.add(sPanel,"8");
        cl.show(panel_cont,"1");


        JButton home_hpb=home_panel.gethpbButton();
        home_hpb.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(panel_cont, "7");
            }
            
        });


        JButton home_settings=home_panel.getSettingsButton();
        home_settings.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(panel_cont, "8");
            }
            
        });

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
                        cl.show(panel_cont, "3");
                    }
                    else{
                        JOptionPane.showMessageDialog(login_panel, "Username or password invalid", "Try Again", JOptionPane.ERROR_MESSAGE);
                    }
                }
                
            });

            JButton bs=home_panel.getSingleButton();
            bs.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    cl.show(panel_cont, "4");
                }
                
            });


            JButton bm=home_panel.getMultiButton();
            bm.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    cl.show(panel_cont, "5");
                }
                
            });


            JButton bc=home_panel.getCreativeButton();
            bc.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    cl.show(panel_cont, "6");
                }
                
            });

            JButton sPanel_exit=sPanel.getExitButton();
            sPanel_exit.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    cl.show(panel_cont, "3");
                }
            });

            JButton hPanel_exit=hPanel.getExitButton();
            hPanel_exit.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    cl.show(panel_cont, "3");
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
    public void playMusic(String filepath){
        File music;
        try {
            music=new File(filepath);
            AudioInputStream audioInput=AudioSystem.getAudioInputStream(music);
            Clip clip=AudioSystem.getClip();
            clip.open(audioInput);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
