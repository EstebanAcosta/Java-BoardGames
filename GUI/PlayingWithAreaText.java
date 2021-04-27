package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

public class PlayingWithAreaText
{

    public static void main(String[] args)
    {
        areaFrame af = new areaFrame();

        af.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}

class areaFrame extends JFrame
{

    public areaFrame()
    {
        setTitle("Text Area Frame");

        setBounds(600, 300, 600, 350);

        areaPanel ap = new areaPanel();

        add(ap);

        setVisible(true);
    }

}

class areaPanel extends JPanel
{

    private JTextArea myA;
    
    
    public areaPanel()
    {
        myA = new JTextArea(8,20);  
        
        JScrollPane second = new JScrollPane(myA);
        
//        myA.setLineWrap(true);
        
        add(second);
        
        JButton myButton = new JButton("Touch This");
        
        myButton.addActionListener(new manageArea());
        
        add(myButton);
    }
    
    private class manageArea implements ActionListener{


        public void actionPerformed(ActionEvent e)
        {
            System.out.println(myA.getText());
        }
        
    }
}
