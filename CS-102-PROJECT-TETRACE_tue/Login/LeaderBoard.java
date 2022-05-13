package Login;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LeaderBoard extends JPanel{
    JLabel first;
    JLabel second;
    JLabel third;

    JButton homeoflead;
    public LeaderBoard(){


        Icon home = new ImageIcon( "images/home.png" ) ;
        homeoflead=new JButton(home);


        this.setLayout(null);
        User[] leadUsers=getTop(getExistingUser());
        first=new JLabel("1) "+leadUsers[0].username);
        second=new JLabel("2) "+leadUsers[1].username);
        third=new JLabel("3) "+leadUsers[2].username);
        Font font=new Font(Font.MONOSPACED, Font.BOLD,  24);
        first.setFont(font);
        second.setFont(font);
        third.setFont(font);

        first.setBounds(650, 200, 200, 100);
        second.setBounds(650, 400, 200, 100);
        third.setBounds(650, 600, 200, 100);
        homeoflead.setBounds(1200, 850, 50, 50);

        this.add(homeoflead);
        this.add(first);
        this.add(second);
        this.add(third);
    }
    @Override
    public void paintComponent(Graphics g){
        BufferedImage myPicture=null;
        try {
           
            myPicture = ImageIO.read(new File("images/back.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        super.paintComponent(g);
        g.drawImage(myPicture, 0,0,null);
    }

    public JButton gethol(){
        return homeoflead;
    }
    public ArrayList<User> getExistingUser(){
        ArrayList<User> to_return=new ArrayList<>();
        final String DB_URL="jdbc:mysql://localhost:3306/tetrace";
        final String USERNAME="root";
        final String PASSWORD="zeynepasel3";
        try{
            Connection conn=DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            String sql="SELECT * FROM users";
            PreparedStatement preparedStatement=conn.prepareStatement(sql);

            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                User us=new User();
                us.id=(resultSet.getInt("idusers"));
                us.username=(resultSet.getString("UserName"));
                us.password=(resultSet.getString("Password"));
                us.e_mail=(resultSet.getString("e_mail"));
                us.bio=(resultSet.getString("bio"));
                us.score=(resultSet.getInt("score"));
                us.bo=(resultSet.getInt("bo"));
                us.cb=(resultSet.getInt("cb"));
                us.ss=(resultSet.getInt("ss"));
                us.diamonds=(resultSet.getInt("money"));

                to_return.add(us);
            }
            preparedStatement.close();
            conn.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        return to_return;
    }

    public User[] getTop(ArrayList<User> to_return){
        User[] leaders=new User[3];
       
        for(int k=0;k<3;k++){
            User maximum=new User();
            int max=0;
            for(int i=0;i<to_return.size();i++)
            {
                if(to_return.get(i).diamonds !=0 && to_return.get(i).diamonds>max){
                    max=to_return.get(i).diamonds;
                    maximum=to_return.get(i);
                }
            }
            leaders[k]=maximum;
            to_return.remove(maximum);
        }
        return leaders;
    }
    
}
