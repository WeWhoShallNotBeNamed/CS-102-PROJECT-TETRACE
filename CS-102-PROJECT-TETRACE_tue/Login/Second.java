package Login;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Second extends JPanel{
    JSlider slider;
    JPanel panel_cont=this;
    CardLayout cl;
    FloatControl fc;
    Clip stream;
    User player;
    
    
    public Second(User u,Clip clip,FloatControl fc) throws IOException{
        
        player=u;
        Home home_panel=new Home(u);
        Single single_panel=new Single();
        Creative creative_panel=new Creative(u);
        SettingsPanel sPanel=new SettingsPanel(u);
        HowToPlayPanel hPanel=new HowToPlayPanel();
        Single_Player game_single=new Single_Player(u,1);
        Freezing_mode game_freezing=new Freezing_mode(u);
        Single_Player game_story=new Single_Player(u,2);
        Single_Player game_oldschool=new Single_Player(u,3);

        Profile profile=new Profile(u, panel_cont);
        CardLayout cl=new CardLayout();
        panel_cont.setLayout(cl);
        panel_cont.add(home_panel,"1");
        panel_cont.add(single_panel,"2");
        //panel_cont.add(multi_panel,"3");
        panel_cont.add(creative_panel,"4");
        panel_cont.add(hPanel,"5");;
        panel_cont.add(sPanel,"6");
        panel_cont.add(game_single,"7");
        panel_cont.add(game_freezing,"8");
        panel_cont.add(game_story,"9");
        panel_cont.add(game_oldschool,"10");
        panel_cont.add(profile,"11");
        
        cl.show(this,"1");

        JButton home_multi=home_panel.getMultiButton();
        home_multi.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String name;
                name=JOptionPane.showInputDialog(null, "firstname");
                String password = JOptionPane.showInputDialog(null, "password");
                User u2= getExistingUser(name, password);
                Multi_Player mp = new Multi_Player(u, u2);
                panel_cont.add(mp,"3");
                cl.show(panel_cont, "3");
            }
            
        });
        JButton exitButton=hPanel.getExitButton();
        exitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(panel_cont, "1");
            }
        });
        JButton home_hpb=home_panel.gethpbButton();
        home_hpb.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                    cl.show(panel_cont, "5");
                
            }
            
        });

        JButton standard_game=single_panel.getStandard();
        standard_game.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(panel_cont, "7");
                
            }
            
        });

        JButton freezing_game=single_panel.getFreezing();
        freezing_game.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(panel_cont, "8");
                
            }
            
        });

        JButton story_game=single_panel.getStory();
        story_game.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(panel_cont, "9");
            }
            
        });

        JButton home_settings=home_panel.getSettingsButton();
        home_settings.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(panel_cont, "6");
            }
            
        });

      
            JButton bs=home_panel.getSingleButton();
            bs.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    cl.show(panel_cont, "2");
                }
                
            });


            JButton bm=home_panel.getMultiButton();
            bm.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    cl.show(panel_cont, "3");
                }
                
            });


            JButton bc=home_panel.getCreativeButton();
            bc.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    cl.show(panel_cont, "4");
                }
            });

            JButton sPanel_exit=sPanel.getExitButton();
            sPanel_exit.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    cl.show(panel_cont, "1");
                }
            });

            slider=sPanel.getSoundSlider();
            slider.addChangeListener(new ChangeListener() {

                @Override
                public void stateChanged(ChangeEvent e) {
                    fc.setValue(slider.getValue());
                }
                
            });

            JButton profile_page=home_panel.getProfileButton();
            profile_page.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    cl.show(panel_cont,"11");
                    
                }
                
            });
        JButton old=single_panel.getOldSchool();
        old.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                cl.show(panel_cont, "10");
            }
            
        });
    }
    public CardLayout getCardLayout(){
        return cl;
    }
    public JPanel getCont(){
        return panel_cont;
    }
    public User getExistingUser(String UserName,String Password){
        User user=null;
        final String DB_URL="jdbc:mysql://localhost:3306/tetrace";
        final String USERNAME="root";
        final String PASSWORD="zeynepasel3";
        try{
            Connection conn=DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            String sql="SELECT * FROM users where UserName=? AND Password=?";
            PreparedStatement preparedStatement=conn.prepareStatement(sql);
            preparedStatement.setString(1,UserName);
            preparedStatement.setString(2,Password);

            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                user=new User();
                user.username=resultSet.getString("UserName");
                user.password=resultSet.getString("Password");
                user.e_mail=resultSet.getString("e_mail");
                user.bio=resultSet.getString("bio");
            }
            preparedStatement.close();
            conn.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        return user;
    }
}
