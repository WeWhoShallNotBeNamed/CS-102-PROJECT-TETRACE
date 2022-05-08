import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ShopPanel extends JPanel{
    private Image background;

    private Image blackOut;
    private Image blockPicker;
    private Image cutBack;
    private Image screenSwap;
    private Image reverser;

    private Image howToPlay;

    private Image powerUpTitle;

    //private Image diamond;

    private Image purchase;
    private Image home;
    private Image settings;

    private JButton purchaseButton1;
    private JButton purchaseButton2;
    private JButton purchaseButton3;
    private JButton purchaseButton4;
    private JButton purchaseButton5;

    private JButton homeButton;
    private JButton settingsButton;
    private JButton howToPlayButton;

    public ShopPanel(){
        setLayout( null );
        setImages();
        setButtons();
    }
    
    public void setImages(){
        try
        {
            background = ImageIO.read( new File( "images/storeBack.png" ) );
            powerUpTitle = ImageIO.read(new File("images/powerUPTitle.png"));
            blackOut = ImageIO.read( new File( "images/blackOut.png" ) );
            blockPicker = ImageIO.read( new File( "images/blockPicker.png" ) );
            cutBack = ImageIO.read( new File( "images/cutBack.png" ) );
            screenSwap = ImageIO.read( new File( "images/screenSwap.png" ) );
            reverser = ImageIO.read( new File( "images/reverser.png" ) );
            purchase = ImageIO.read( new File( "images/purchase.png" ) );
            home = ImageIO.read( new File( "images/home.png" ) );
            settings = ImageIO.read( new File( "images/setting.png" ) );
            powerUpTitle = ImageIO.read( new File( "images/powerUpTitle.png" ) );
            //diamond = ImageIO.read(new File("images/diamond.png"));
            howToPlay = ImageIO.read(new File("images/howToPlay.png"));
        }
        catch( IOException exception ){}
    }
    
 
    public void setButtons(){
        purchaseButton1 = new JButton( new ImageIcon( purchase.getScaledInstance( 50, 50, BufferedImage.TYPE_INT_ARGB ) ) );
        purchaseButton2 = new JButton( new ImageIcon( purchase.getScaledInstance( 50, 50, BufferedImage.TYPE_INT_ARGB ) ) );
        purchaseButton3 = new JButton( new ImageIcon( purchase.getScaledInstance( 50, 50, BufferedImage.TYPE_INT_ARGB ) ) );
        purchaseButton4 = new JButton( new ImageIcon( purchase.getScaledInstance( 50, 50, BufferedImage.TYPE_INT_ARGB ) ) );
        purchaseButton5 = new JButton( new ImageIcon( purchase.getScaledInstance( 50, 50, BufferedImage.TYPE_INT_ARGB ) ) );

        homeButton = new JButton( new ImageIcon( home.getScaledInstance( 50, 50, BufferedImage.TYPE_INT_ARGB ) ) );
        settingsButton = new JButton( new ImageIcon( settings.getScaledInstance( 50, 50, BufferedImage.TYPE_INT_ARGB ) ) );
        howToPlayButton = new JButton( new ImageIcon( howToPlay.getScaledInstance( 150, 100, BufferedImage.TYPE_INT_ARGB ) ) );
        
        
        homeButton.setBounds( 150, 250, 50, 50 );
        settingsButton.setBounds( 75, 250, 50, 50 );
        howToPlayButton.setBounds( 20, 20, 50, 50 );
        purchaseButton1.setBounds( 375, 465, 150, 50 );
        purchaseButton2.setBounds( 375, 465, 150, 50 );
        purchaseButton3.setBounds( 375, 465, 150, 50 );
        purchaseButton4.setBounds( 375, 465, 150, 50 );
        purchaseButton5.setBounds( 375, 465, 150, 50 );
        
        homeButton.addActionListener( new HomeListener() );
        settingsButton.addActionListener( new SettingsListener() );
        howToPlayButton.addActionListener( new howToPlayListener() );
        purchaseButton1.addActionListener( new Purchase1Listener());
        purchaseButton2.addActionListener( new Purchase2Listener());
        purchaseButton3.addActionListener( new Purchase3Listener());
        purchaseButton4.addActionListener( new Purchase4Listener());
        purchaseButton5.addActionListener( new Purchase5Listener());
        
        add( homeButton );
        add( settingsButton );
        add( howToPlayButton );
        add( purchaseButton1 );
        add( purchaseButton2 );
        add( purchaseButton3 );
        add( purchaseButton4 );
        add( purchaseButton5 );
    }

    private class HomeListener implements ActionListener{
        public void actionPerformed( ActionEvent event ){
            Main.getStack().show( Main.getCards(), "home" );  
        }
    }

    private class SettingsListener implements ActionListener{
        public void actionPerformed( ActionEvent event ){
            Main.getStack().show( Main.getCards(), "settings" );
        }
    }

    private class howToPlayListener implements ActionListener{
        public void actionPerformed( ActionEvent event ){
            Main.getStack().show( Main.getCards(), "howToPlay" );
        }
    }

    private class Purchase1Listener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            PowerUp powerUp = new BlackOut(); 
            
            if(Main.getPlayer().canBuy(powerUp.getPrice()))
                powerUp.setOwned(true);
            else 
                System.err.println("The user doesn't have enough diamonds!");
        }
    }
    private class Purchase2Listener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            PowerUp powerUp = new ScreenSwap(); 
            
            if(Main.getPlayer().canBuy(powerUp.getPrice()))
                powerUp.setOwned(true);
            else 
                System.err.println("The user doesn't have enough diamonds!");
        }
    }
    private class Purchase3Listener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            PowerUp powerUp = new CutBack(); 
            
            if(Main.getPlayer().canBuy(powerUp.getPrice()))
                powerUp.setOwned(true);
            else 
                System.err.println("The user doesn't have enough diamonds!");
        }
    }
    private class Purchase4Listener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            PowerUp powerUp = new Reverser(); 
            
            if(Main.getPlayer().canBuy(powerUp.getPrice()))
                powerUp.setOwned(true);
            else 
                System.err.println("The user doesn't have enough diamonds!");
        }
    }
    private class Purchase5Listener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            PowerUp powerUp = new BlockPicker(); 
            
            if(Main.getPlayer().canBuy(powerUp.getPrice()))
                powerUp.setOwned(true);
            else 
                System.err.println("The user doesn't have enough diamonds!");
        }
    }
}