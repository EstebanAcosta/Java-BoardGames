package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Calculator
{

    public static void main(String[] args)
    {
        calculatorFrame cf1 = new calculatorFrame();

        cf1.setVisible(true);

        cf1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

class calculatorFrame extends JFrame
{
    public calculatorFrame()
    {
        setTitle("Calculator");

//        setBounds(500, 300, 450, 300);

        calculatorPanel cp = new calculatorPanel();

        add(cp);
        
        pack();
    }
}

class calculatorPanel extends JPanel
{
    JPanel myPanel2;
    
    JButton window;
    
    public calculatorPanel()
    {
        setLayout(new BorderLayout());

        window = new JButton("");

        window.setEnabled(false);

        add(window, BorderLayout.NORTH);

        myPanel2 = new JPanel();

        myPanel2.setLayout(new GridLayout(4, 4));
        
        String botonesx="789/456*123-0.=+";
        
        for(int i=0; i<botonesx.length();i++) 
        {
            char sucesion=botonesx.charAt(i);
            
            if(sucesion != '+' && sucesion != '-' &&
               sucesion != '*' && sucesion != '.' &&
               sucesion != '/'  && sucesion != '=')
            {
                placeButtons(Character.toString(sucesion),new insertNumber());

            }
            
            else
            {
                
            }
                        
            
        }

        
        add(myPanel2,BorderLayout.CENTER);
    }
     
    private class insertNumber implements ActionListener
    {

    
        public void actionPerformed(ActionEvent e)
        {
            String entry = e.getActionCommand();
            
            window.setText(window.getText() + entry);
        }
        
    }
    
    private void placeButtons(String label, ActionListener listen)
    {
        JButton button = new JButton(label);
        
        button.addActionListener(listen);
        
        myPanel2.add(button);
    }

}

//JButton button1 = new JButton("1");
//
//myPanel2.add(button1);
//
//JButton button2 = new JButton("2");
//
//myPanel2.add(button2);
//
//JButton button3 = new JButton("3");
//
//myPanel2.add(button3);
//
//JButton button4 = new JButton("4");
//
//myPanel2.add(button4);
//
//
//JButton button5 = new JButton("5");
//
//myPanel2.add(button5);
//
//JButton button6 = new JButton("6");
//
//myPanel2.add(button6);
//
//JButton button7 = new JButton("7");
//
//myPanel2.add(button7);
//
//JButton button8 = new JButton("8");
//
//myPanel2.add(button8);
//
//
//JButton button9 = new JButton("9");
//
//myPanel2.add(button9);
//
//JButton button0 = new JButton("0");
//
//myPanel2.add(button0);
//
//JButton buttonPlus = new JButton("+");
//
//myPanel2.add(buttonPlus);
//
//JButton buttonMinus = new JButton("-");
//
//myPanel2.add(buttonMinus);
//
//
//JButton buttonTimes = new JButton("*");
//
//myPanel2.add(buttonTimes);
//
//JButton buttonDivided = new JButton("/");
//
//myPanel2.add(buttonDivided);
//
//JButton buttonComma = new JButton(",");
//
//myPanel2.add(buttonComma);
//
//JButton buttonPeriod = new JButton(".");
//
//myPanel2.add(buttonPeriod);