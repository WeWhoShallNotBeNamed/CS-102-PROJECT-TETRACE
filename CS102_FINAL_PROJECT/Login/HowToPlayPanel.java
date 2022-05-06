package Login;



import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class HowToPlayPanel extends JPanel{
    private Image background;
    private Image home;
    private Image exit;
    

    private JButton exitButton;
    private JButton homeButton;
    
    
    public HowToPlayPanel(){
        setLayout( null );
        setImages();
        addButtons();
    }
    
    public void setImages()
    {
        try{
            background = ImageIO.read( new File( "/Users/eslimranaemiroglu/Desktop/success.png" ) );
            home = ImageIO.read( new File( "/Users/eslimranaemiroglu/Desktop/home.png" ) );
            exit = ImageIO.read( new File( "/Users/eslimranaemiroglu/Desktop/exit.png" ) );
        }
        catch( IOException exception ){}
    }

    public void addButtons(){
        exitButton = new JButton( new ImageIcon(exit));
        exitButton.setBounds( 20, 20, 50, 50 );
        
        
        homeButton = new JButton(new ImageIcon(home));

        
    
        add(exitButton);
        add(homeButton);

    }
    
    public void paintComponent( Graphics g ){
        super.paintComponent( g );
        
        g.drawImage( background, 0, 0, 1440, 901, null );
    }
    public JButton getHomeButton(){
        return homeButton;
    }
    public JButton getExitButton(){
        return exitButton;
    }
    
}
