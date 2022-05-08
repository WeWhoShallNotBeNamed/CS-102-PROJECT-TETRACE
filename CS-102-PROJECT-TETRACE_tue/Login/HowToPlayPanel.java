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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class HowToPlayPanel extends JPanel{
    private Image exit;
    

    private JButton exitButton;
    
    
    public HowToPlayPanel() throws IOException{
       
        setLayout( null );
        BufferedImage myPicture = ImageIO.read(new File("/Users/eslimranaemiroglu/Desktop/howToPlayExplanation.png"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        picLabel.setBounds(200,200,1000,500);
        this.add(picLabel);
        exit = ImageIO.read( new File( "/Users/eslimranaemiroglu/Desktop/exit.png" ) );
        addButtons();
    }

    public void addButtons(){
        exitButton = new JButton( new ImageIcon(exit));
        exitButton.setBounds( 1120, 150, 50, 50 );
        
        add(exitButton);
    }
    
    public void paintComponent( Graphics g ){
        
        super.paintComponent(g);
        BufferedImage myPicture=null;
        try {
            myPicture = ImageIO.read(new File("/Users/eslimranaemiroglu/Desktop/back.png"));
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
    
    public JButton getExitButton(){
        return exitButton;
    }
    
}
