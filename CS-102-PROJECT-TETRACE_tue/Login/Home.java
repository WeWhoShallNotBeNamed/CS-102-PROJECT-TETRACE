package Login;

import java.awt.BasicStroke;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Home extends JPanel{
    public JButton multi;
    public JButton single;
    public JButton creative;
    JButton hpb;
    JButton hs;
    JButton profile;
    public Home(User u) throws IOException{
        Icon icon_book=new ImageIcon("/Users/eslimranaemiroglu/Desktop/book.png");
        hpb=new JButton(icon_book);
        hpb.setBounds(1100, 750, 39, 34);
        hpb.setBackground(Color.LIGHT_GRAY);
        hpb.setBorderPainted(false);
        hpb.setFocusPainted(false);
        

        Icon icon_profile=new ImageIcon("/Users/eslimranaemiroglu/Desktop/profile_rus.png");
        profile=new JButton(icon_profile);
        profile.setBounds(1100, 100, 39, 34);
        profile.setBorderPainted(false);
        profile.setFocusPainted(false);
       

        Icon icon_settings=new ImageIcon("/Users/eslimranaemiroglu/Desktop/settings.png");
        hs=new JButton(icon_settings);
        hs.setBounds(1100, 700, 39, 34);
        hs.setBackground(Color.LIGHT_GRAY);
        hs.setBorderPainted(false);
        hs.setFocusPainted(false);
       



        multi=new JButton("Multi Player");
        single=new JButton("Single Player");
        creative=new JButton("<html>"+(" Create & Share"+ "\n"+ "&nbsp Your Puzzle").replaceAll("\n", "<br>")+"</html>");

        multi.setBackground(Color.magenta);
        multi.setOpaque(true);
        multi.setBorderPainted(false);
        multi.setFocusPainted(false);

        single.setBackground(Color.ORANGE);
        single.setOpaque(true);
        single.setBorderPainted(false);
        single.setFocusPainted(false);

        creative.setBackground(Color.CYAN);
        creative.setOpaque(true);
        creative.setBorderPainted(false);
        creative.setFocusPainted(false);

        multi.setBounds(610, 500, 200, 80);
        single.setBounds(610, 400, 200, 80);
        creative.setBounds(610, 600, 200, 80);

        multi.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        single.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        creative.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));


        JLabel group_label = new JLabel("Proudly created by We, Who Shall Not Be Named...");
        BufferedImage myPicture = ImageIO.read(new File("/Users/eslimranaemiroglu/Desktop/bg_game.png"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        picLabel.setBounds(610,200,180,180);
        this.setLayout(null);
        group_label.setFont(new Font(Font.MONOSPACED, Font.ITALIC, 13));
        group_label.setBounds(530, 760, 500, 100);


        this.add(profile);
        this.add(hpb);
        this.add(hs);
        this.add(single);
        this.add(multi);
        this.add(creative);
        this.add(picLabel);
        this.add(group_label);
    }

    @Override
    public void paintComponent(Graphics g){
        BufferedImage myPicture=null;
        try {
            myPicture = ImageIO.read(new File("/Users/eslimranaemiroglu/Desktop/success.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.paintComponent(g);
        Graphics2D g2=(Graphics2D)g;
        g2.setStroke(new BasicStroke(4));
        g.drawImage(myPicture, 0,0,null);
        g2.setColor(Color.WHITE);
        g2.drawLine(200, 0, 200, 901);
        g2.drawLine(1200, 0, 1200, 901);
    }
    public JButton getSingleButton(){
        return single;
    }
    public JButton getMultiButton(){
        return multi;
    }
    public JButton getCreativeButton(){
        return creative;
    }
    public JButton gethpbButton(){
        return hpb;
    }
    public JButton getSettingsButton(){
        return hs;
    }
    public JButton getProfileButton(){
        return profile;
    }
}

