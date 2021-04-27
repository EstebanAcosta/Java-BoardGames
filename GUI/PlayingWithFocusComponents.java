package GUI;

import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class PlayingWithFocusComponents
{

    public static void main(String[] args)
    {
        focusFrame fs = new focusFrame();

        fs.setVisible(true);

        fs.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}

class focusFrame extends JFrame
{
    public focusFrame()
    {

        setTitle("Using Focus");

        setBounds(300, 300, 500, 250);

        focusPanel fp = new focusPanel();

        add(fp);

    }

}

class focusPanel extends JPanel
{

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        setLayout(null);

        box1 = new JTextField();

        box2 = new JTextField();
        
//        box3 = new JTextField();

        box1.setBounds(120, 10, 150, 20);

        box2.setBounds(120, 50, 150, 20);

//        box3.setBounds(120, 90, 150, 30);

        add(box1);

//        add(box2);
//
//        add(box3);

        box1.addFocusListener(new showFocus());
        
    }

    private class showFocus implements FocusListener
    {

        @Override
        public void focusGained(FocusEvent e)
        {
            System.out.println("You gained focus");
        }

        @Override
        public void focusLost(FocusEvent e)
        {
            System.out.println("You lost focus");
            
            String email = box1.getText();
            
            boolean confirmed = false;
         
            
            for(int i = 0 ; i < email.length(); i++)
            {
                if(email.charAt(i) == '@')
                {
                    confirmed = true;
                }
            }
            
            if(confirmed)
            {
                System.out.println("CORRECT");
            }
            
            else
            {
                System.out.println("INCORRECT");
            }
        }

    }

    JTextField box1;

    JTextField box2;

//    JTextField box3;

}
