package Login;

import java.awt.BasicStroke;
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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class SettingsPanel extends JPanel{  
    private Image background;
    private Image home;
    private Image exit;

    private JButton exitButton;
    private JButton homeButton;

    private JSlider soundAdjuster;
    
    public SettingsPanel(){
        setLayout( null );
        setImages();
        addButtons();
    }
    
    public void setImages(){
        try{
            background = ImageIO.read( new File( "/Users/eslimranaemiroglu/Desktop/success.png" ) );
            home = ImageIO.read( new File( "/Users/eslimranaemiroglu/Desktop/home.png" ) );
            exit = ImageIO.read( new File( "/Users/eslimranaemiroglu/Desktop/exit.png" ) );
        }
        catch( IOException exception ){}
    }
    
    public void addButtons(){
        soundAdjuster = new JSlider( 0, 100, 50 );
        soundAdjuster.setBounds( 150, 250, 700, 50 );
        
        exitButton = new JButton( new ImageIcon(exit));
        exitButton.setBounds( 20, 20, 50, 50 );
        
        homeButton = new JButton(new ImageIcon(home));
        
        
        add(soundAdjuster);
        add(exitButton);
        add(homeButton);
    }
    
    public void paintComponent( Graphics g ){
        super.paintComponent(g);
        BufferedImage myPicture=null;
        try {
            myPicture = ImageIO.read(new File("/Users/eslimranaemiroglu/Desktop/success.png"));
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
}
