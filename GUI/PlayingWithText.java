package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
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
    
    JLabel result;

    public textPanel()
    {
        setLayout(new BorderLayout());
        
        JPanel northPanel = new JPanel();
        
        northPanel.setLayout(new FlowLayout());
        
        result = new JLabel("",JLabel.CENTER);
        
        JLabel label = new JLabel("Email: ");

        northPanel.add(label);

        field1 = new JTextField(20);

        northPanel.add(field1);
        
        add(result, BorderLayout.CENTER);

        JButton myButton = new JButton("Validate Email");

        giveMeText myEvent = new giveMeText();

        myButton.addActionListener(myEvent);

        northPanel.add(myButton);
        
        add( northPanel,BorderLayout.NORTH);

        // System.out.println(field1.getText().trim());
    }

    private class giveMeText implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {

            int correct = 0;
            
            String email = field1.getText().trim();
            
            for(int i = 0; i < email.length(); i++)
            {
                if(email.charAt(i) == '@')
                {
                    correct++;
                }
            }
            
            if(correct != 1)
            {
//                 System.out.println("Incorrect Email");

                result.setText("Invalid Email");
            }
            
            else
            {
//                System.out.println("Valid Email");
                
                result.setText("Valid Email");
            }


        }

    }
}
