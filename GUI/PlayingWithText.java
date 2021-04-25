package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PlayingWithText
{
    public static void main(String[] args)
    {
        frameText ft = new frameText();

        ft.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}

class frameText extends JFrame
{
    public frameText()
    {

        setTitle("Text Frame");

        setBounds(600, 300, 600, 350);

        textPanel tp = new textPanel();

        add(tp);

        setVisible(true);
    }
}

class textPanel extends JPanel
{
    JTextField field1;
    
    public textPanel()
    {
        JLabel label = new JLabel("Email: ");
        
        add(label);
        
        field1 = new JTextField(20);

        add(field1);

        JButton myButton = new JButton("Validate Email");

        giveMeText myEvent = new giveMeText();

        myButton.addActionListener(myEvent);

        add(myButton);

        // System.out.println(field1.getText().trim());
    }
    
    private class giveMeText implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            System.out.println(field1.getText().trim());
            
        }
        
    }
}
