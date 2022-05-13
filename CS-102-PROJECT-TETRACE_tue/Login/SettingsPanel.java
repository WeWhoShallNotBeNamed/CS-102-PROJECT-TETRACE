package Login;

import java.awt.BasicStroke;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

public class SettingsPanel extends JPanel{  

    private Image home;
    private Image exit;

    private JButton exitButton;
    private JButton homeButton;
    
    private JRadioButton arrows;
    private JRadioButton wasd;
    private ButtonGroup bg;
    private JLabel keyboard;


    private JSlider soundAdjuster;
    JLabel sound;
    
    public SettingsPanel(User u){
        keyboard=new JLabel();
        keyboard.setBounds(620, 290, 170, 70);
        bg=new ButtonGroup();
        arrows=new JRadioButton("arrows");
        wasd=new JRadioButton("wasd");
        bg.add(arrows);
        bg.add(wasd);
        arrows.setBounds(670,310, 100, 20);
        wasd.setBounds(670, 330, 100, 20);


        Border border2=BorderFactory.createBevelBorder(BevelBorder.RAISED,Color.MAGENTA,Color.BLUE);
        Border border3 =BorderFactory.createTitledBorder(border2,"VOLUME");
        Border border4 =BorderFactory.createTitledBorder(border2,"KEYBOARD");
        keyboard.setBorder(border4);
        sound=new JLabel();
        sound.setBorder(border3);
        sound.setBounds(620, 140, 170, 70);
        this.add(sound);
        this.add(arrows);
        this.add(wasd);
        this.add(keyboard);
        setLayout( null );
        setImages();
        addButtons();
    }
    
    public void setImages(){
        try{
            home = ImageIO.read( new File( "images/home.png" ) );
            exit = ImageIO.read( new File( "images/exit.png" ) );
        }
        catch( IOException exception ){}
    }
    
    public void addButtons(){

        soundAdjuster = new JSlider( -80, 6, 1);
        soundAdjuster.setBounds( 630, 150, 150, 50 );
        
        exitButton = new JButton( new ImageIcon(exit));
        exitButton.setBounds( 900, 40, 50, 50 );
        
        homeButton = new JButton(new ImageIcon(home));
        
        
        add(soundAdjuster);
        add(exitButton);
        add(homeButton);
    }
    
    public void paintComponent( Graphics g ){
        super.paintComponent(g);
        BufferedImage myPicture=null;
        try {
            myPicture = ImageIO.read(new File("images/success.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Graphics2D g2=(Graphics2D)g;
        g2.setStroke(new BasicStroke(4));
        g.drawImage(myPicture, 0,0,null);
        g2.setColor(Color.WHITE);
        g2.drawLine(200, 0, 200, 901);
        g2.drawLine(1200, 0, 1200, 901);
    }
    
    public JButton getHomeButton(){
        return homeButton;
    }
    public JButton getExitButton(){
        return exitButton;
    }
    public JSlider getSoundSlider(){
        return soundAdjuster;
    }
}
