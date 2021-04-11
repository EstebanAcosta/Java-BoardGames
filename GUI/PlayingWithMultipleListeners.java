package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PlayingWithMultipleListeners
{
    public static void main(String[] args)
    {
        mainFrame mf = new mainFrame();
        
        mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        mf.setVisible(true);
    }
    
    
}

class mainFrame extends JFrame
{
    public mainFrame()
    {
        setTitle("Several Tries");
        
        setBounds(1300,100,300,200);
        
        mainPanel mp = new mainPanel();
        
        add(mp);
    }
}

class mainPanel extends JPanel
{
    public mainPanel()
    {
        JButton newbutton = new JButton("New");
        
        add(newbutton);
        
        closebutton = new JButton("Close Everything");
        
        add(closebutton);

        newListener nL = new newListener();
        
        
        newbutton.addActionListener(nL);
    }
    
    private class newListener implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            newFrame nf = new newFrame(closebutton);
            
            nf.setVisible(true);
            
            
        }
        
    }
    
    JButton closebutton;
}

class newFrame extends JFrame
{
    private static int count = 0 ;
    
    public newFrame(JButton mainButton)
    {
        count++;

        setTitle("Window " + count);
        
        setBounds(40*count,40*count,300,200);
        
        mainButton.addActionListener(new closeEverything());
        
        
    }
    
    private class closeEverything implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            dispose();
        }
        
    }
}
