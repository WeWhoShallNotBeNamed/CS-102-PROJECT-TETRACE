package Login;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Edit_Profile extends JPanel {
    JPanel panel_cont;
    //sol taraf
    JLabel username_header;
    JLabel bio_header;
    BufferedImage level_img;//you can do this with paintComponent


    JLabel username;
    ImageIcon profile_pic;
    JLabel biography;
    JLabel puzzles;
    JLabel repository;
    JButton edit_profile;
    //edit part-orta
    JLabel Password;
    JLabel Username;
    JLabel e_mail_label;
    JLabel History;



    JTextField password;
    JTextField username_edit;
    JTextField e_mail;
    JTextArea bio;
    JButton save;

    User u;

    public Edit_Profile(User u){
        this.u=u;
        username=new JLabel(""+u.username);
        biography=new JLabel(""+u.bio);


        username.setBounds(200, 100, 200, 50);
        biography.setBounds(200, 200, 200, 100);
        this.add(username);
        this.add(biography);
        panel_cont=this;
        Password=new JLabel("Password");
        Username=new JLabel("Username");
        e_mail_label=new JLabel("e_mail");
        History=new JLabel("History");
        Font font=new Font(Font.MONOSPACED, Font.BOLD, 16);
        Password.setFont(font);
        Username.setFont(font);
        e_mail_label.setFont(font);
        History.setFont(font);

        password=new JTextField(""+u.password);
        username_edit=new JTextField(""+u.username);
        e_mail=new JTextField(""+u.e_mail);
        bio=new JTextArea(""+u.bio);


        this.setLayout(null);
        Password.setBounds(500, 200, 100, 20);
        this.add(Password);
        Username.setBounds(500, 300, 100, 20);
        this.add(Username);
        e_mail_label.setBounds(500, 400, 100, 20);
        this.add(e_mail_label);
        History.setBounds(500, 500, 100, 20);
        this.add(History);

        password.setBounds( 650, 200, 200, 35);
        this.add(password);
        username_edit.setBounds( 650, 300, 200, 35);
        this.add(username_edit);
        e_mail.setBounds( 650, 400, 200, 35);
        this.add(e_mail);
        bio.setBounds( 650, 500, 300, 150);
        this.add(bio);
        
        save=new JButton("Save Changes");
        save.setBounds(810, 700, 130, 30);
        save.setOpaque(true);
        save.setBorderPainted(false);
        save.setBackground(Color.CYAN);
        save.setFocusPainted(false);
        this.add(save);


        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                final String DB_URL="jdbc:mysql://localhost:3306/tetrace";
                final String USERNAME="root";
                final String PASSWORD="zeynepasel3";
                try{
                    String value1=username_edit.getText();
                    String value2=password.getText();
                    String value3=e_mail.getText();
                    String value4=bio.getText();
                    
                    Connection conn=DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
                    String sql="update users set UserName=?, Password=?, e_mail=?, bio=? where UserName=?";
                    PreparedStatement preparedStatement=conn.prepareStatement(sql);
                    preparedStatement.setString(1,value1);
                    preparedStatement.setString(2,value2);
                    preparedStatement.setString(3,value3);
                    preparedStatement.setString(4,value4);
                    preparedStatement.setString(5,""+u.username);
                    preparedStatement.execute();
                    JOptionPane.showMessageDialog(null, "record UPDATED");
                    updateUser();
                    updatePage();
                }
                catch(Exception ex){
                    System.out.println(ex);
                }
            }
        });
    }
    public void updatePage(){
        username.setText(""+u.username);
        biography.setText(""+u.bio);
    }
    public void updateUser(){
       u.username=username_edit.getText();
       u.password=password.getText();
       u.bio=bio.getText();
       u.e_mail=e_mail.getText();
    }
    @Override
    public void paintComponent(Graphics g){
        BufferedImage myPicture=null;
        try {
            myPicture = ImageIO.read(new File("/Users/eslimranaemiroglu/Desktop/bg_game2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.paintComponent(g);
        Graphics2D g2=(Graphics2D)g;
        g2.setStroke(new BasicStroke(4));
        g.drawImage(myPicture, 0,0,null);
        g2.setColor(Color.BLACK);
        g2.drawLine(400, 100, 400, 700);
       
    }
}
