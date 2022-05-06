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
            background = ImageIO.read( new File( "images/background.jpg" ) );
            home = ImageIO.read( new File( "images/home.png" ) );
            exit = ImageIO.read( new File( "images/exit.png" ) );
        }
        catch( IOException exception ){}
    }
    
    public void addButtons(){
        soundAdjuster = new JSlider( 0, 100, 50 );
        soundAdjuster.setBounds( 150, 250, 700, 50 );
        
        exitButton = new JButton( new ImageIcon(exit));
        exitButton.setBounds( 20, 20, 50, 50 );
        exitButton.addActionListener( new ExitButtonListener() );
        
        homeButton = new JButton(new ImageIcon(home));
        homeButton.addActionListener(new HomeButtonListener());
        
        
        add(soundAdjuster);
        add(exitButton);
        add(homeButton);
    }
    
    public void paintComponent( Graphics g ){
        super.paintComponent( g );
        
        g.drawImage( background, 0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT, null );
    }
    
    private class ExitButtonListener implements ActionListener{
        /**
         * implemented actionPerformed method
         */
        public void actionPerformed( ActionEvent event )
        {
            Regulta.getStack().show( Regulta.getCards(), "menu" );
            //Regulta.setPanel( new RegultaMenuPanel() );
        }
    }

    private class HomeButtonListener implements ActionListener{
        /**
         * implemented actionPerformed method
         */
        public void actionPerformed( ActionEvent event ){
            Regulta.getStack().show( Regulta.getCards(), "home" );
            //Regulta.setPanel( new RegultaMenuPanel() );
        }
    }
}
