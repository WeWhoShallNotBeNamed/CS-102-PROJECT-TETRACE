package Login;

import java.awt.BasicStroke;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Single extends JPanel{
    public JButton standard;
    public JButton freezing;
    public JButton old_school;
    public JButton story;

    public Single() throws IOException{
        standard=new JButton("STANDARD");
        freezing=new JButton("FREEZING");
        old_school=new JButton("OLD-SCHOOL");
        story=new JButton("STORY");

        standard.setFocusPainted(false);
        freezing.setFocusPainted(false);
        old_school.setFocusPainted(false); 
        story.setFocusPainted(false);

        standard.setOpaque(true);
        standard.setBorderPainted(false);
        standard.setBackground(Color.MAGENTA);

        freezing.setOpaque(true);
        freezing.setBorderPainted(false);
        freezing.setBackground(Color.ORANGE);

        old_school.setOpaque(true);
        old_school.setBorderPainted(false);
        old_school.setBackground(Color.MAGENTA);

        story.setOpaque(true);
        story.setBorderPainted(false);
        story.setBackground(Color.ORANGE);

        standard.setBounds(610, 400, 200, 80);
        freezing.setBounds(610, 500, 200, 80);
        old_school.setBounds(610, 600, 200, 80);
        story.setBounds(610, 700, 200, 80);

        JLabel group_label = new JLabel("Proudly created by We, Who Shall Not Be Named...");
        BufferedImage myPicture = ImageIO.read(new File("/Users/eslimranaemiroglu/Desktop/bg_game.png"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        picLabel.setBounds(610,200,180,180);
        this.setLayout(null);
        group_label.setFont(new Font(Font.MONOSPACED, Font.ITALIC, 13));
        group_label.setBounds(530, 760, 500, 100);

        this.setLayout(null);
        this.add(standard);
        this.add(freezing);
        this.add(old_school);
        this.add(story);
        this.add(group_label);
        this.add(picLabel);
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
    public JButton getStandard(){
        return standard;
    }
    public JButton getFreezing(){
        return freezing;
    }
    public JButton getStory(){
        return story;
    }
    public JButton getOldSchool(){
        return old_school;
    }
}
