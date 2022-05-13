package Login;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ShopPanel extends JPanel{
    
    private JLabel blackOut;
    private JLabel cutBack;
    private JLabel screenSwap;

    private Icon howToPlay;

    private JLabel powerUpTitle;
    private JLabel diamonds;
    private JLabel money;

    //private Image diamond;

    private Icon purchase;
    private Icon home;
    private Icon settings;

    private JButton purchaseButton1;
    private JButton purchaseButton2;
    private JButton purchaseButton3;

    private JButton homeButton;
    private JButton settingsButton;
    private JButton howToPlayButton;
    User user;

    public ShopPanel(User u){
        user=u;
        money=new JLabel(""+user.diamonds);
       
        setLayout( null );
        setImages();
        setButtons();
        updatePage();
    }
    
    public void setImages(){
        try
        {
            BufferedImage icon=ImageIO.read( new File( "images/powerUpTitle.png" ) );
            powerUpTitle = new JLabel(new ImageIcon(icon));
            BufferedImage icon1=ImageIO.read( new File( "images/blackOut.png" ) );
            blackOut=new JLabel(new ImageIcon(icon1));
            BufferedImage icon2=ImageIO.read( new File( "images/cutBack.png" ) );
            cutBack=new JLabel(new ImageIcon(icon2));
            BufferedImage icon3=ImageIO.read( new File( "images/screenSwap.png" ) );
            screenSwap=new JLabel(new ImageIcon(icon3));
            purchase = new ImageIcon("images/purchase.png" );
            home = new ImageIcon( "images/home.png" ) ;
            settings = new ImageIcon( "images/settings.png"  );
            BufferedImage icon4=ImageIO.read( new File( "images/diamond.png" ) );
            diamonds=new JLabel(new ImageIcon(icon4));
            howToPlay = new ImageIcon("images/howToPlay.png");
        }
        catch( IOException exception ){}
    }
 
    public void setButtons(){
        purchaseButton1 = new JButton(  purchase);
        purchaseButton2 = new JButton( purchase);
        purchaseButton3 = new JButton( purchase );

        homeButton = new JButton( home  );
        settingsButton = new JButton( settings );
        howToPlayButton = new JButton( howToPlay );
        


        powerUpTitle.setBounds(480, 200, 450, 170);
        homeButton.setBounds( 1200, 600, 50, 50 );
        settingsButton.setBounds( 1200, 650, 50, 50 );
        howToPlayButton.setBounds( 1200, 700, 50, 50 );
        purchaseButton1.setBounds( 500, 465, 100, 50 );
        purchaseButton2.setBounds( 650, 465, 100, 50 );
        purchaseButton3.setBounds( 800, 465, 100, 50 );
        
        purchaseButton1.addActionListener( new Purchase1Listener());
        purchaseButton2.addActionListener( new Purchase2Listener());
        purchaseButton3.addActionListener( new Purchase3Listener());
        
        blackOut.setBounds(530, 400, 40, 40);
        screenSwap.setBounds(680, 400, 40, 40);
        cutBack.setBounds(830, 400, 40, 40);

        diamonds.setBounds(150, 100, 50, 50);
        money.setBounds(200, 100, 50, 50);

        add(money);
        add(diamonds);
        add(blackOut);
        add(cutBack);
        add(screenSwap);
        add(powerUpTitle);
        add( homeButton );
        add( settingsButton );
        add( howToPlayButton );
        add( purchaseButton1 );
        add( purchaseButton2 );
        add( purchaseButton3 );
    }

    public JButton getHomeButton(){
        return homeButton;
    }
    public JButton getSettingsButton(){
        return homeButton;
    }
    public JButton getHowToPlayButton(){
        return homeButton;
    }

    private class Purchase1Listener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            PowerUp powerUp = new BlackOut(user); 
            
            if(user.canBuy(powerUp.getPrice()))
                user.buy(powerUp);
            else 
                JOptionPane.showMessageDialog(null, "The user doesn't have enough diamonds!");
            updateUser();
            updatePage();
        }
    }
    private class Purchase2Listener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            PowerUp powerUp = new ScreenSwap(); 
            
            if(user.canBuy(powerUp.getPrice()))
            user.buy(powerUp);
            else 
            JOptionPane.showMessageDialog(null, "The user doesn't have enough diamonds!");
            updateUser();
            updatePage();
        }
    }
    private class Purchase3Listener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            PowerUp powerUp = new CutBack(); 
            
            if(user.canBuy(powerUp.getPrice()))
            user.buy(powerUp);
            else 
                JOptionPane.showMessageDialog(null, "The user doesn't have enough diamonds!");

            updateUser();
            updatePage();
        }
    }
    @Override
    public void paintComponent(Graphics g){
        BufferedImage myPicture=null;
        try {
            myPicture = ImageIO.read(new File("images/bg_game2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.paintComponent(g);
        
        g.drawImage(myPicture, 0,0,null);
        
    }
    public void updatePage(){
        money.setText(""+user.diamonds);
    }
    public void updateUser(){
        final String DB_URL="jdbc:mysql://localhost:3306/tetrace";
                final String USERNAME="root";
                final String PASSWORD="zeynepasel3";
                try{
                    
                    Connection conn=DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
                    String sql="update users set bo=?, cb=?, ss=?, money=? where UserName=?";
                    PreparedStatement preparedStatement=conn.prepareStatement(sql);
                    preparedStatement.setInt(1,user.bo);
                    preparedStatement.setInt(2,user.cb);
                    preparedStatement.setInt(3,user.ss);
                    preparedStatement.setInt(4,user.diamonds);
                    preparedStatement.setString(5,""+user.username);
                    preparedStatement.execute();
                }
                catch(Exception ex){
                    System.out.println(ex);
                }
    }
}