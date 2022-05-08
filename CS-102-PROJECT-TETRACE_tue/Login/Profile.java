package Login;

import java.awt.CardLayout;

import javax.swing.JPanel;

public class Profile extends JPanel{
    JPanel panel_cont;
    Edit_Profile edit_page;
    CardLayout cl;
    Profile(User u, JPanel container){
        panel_cont=this;
        Edit_Profile edit_page=new Edit_Profile(u);
        LoginActivity la=new LoginActivity(u);
        cl=new CardLayout();
        panel_cont.setLayout(cl);
        panel_cont.add(edit_page,"1");
        panel_cont.add(la,"2");
        cl.show(panel_cont,"1");
    }
}
