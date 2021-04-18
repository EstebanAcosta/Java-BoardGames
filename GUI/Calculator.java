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

        // setBounds(500, 300, 450, 300);

        calculatorPanel cp = new calculatorPanel();

        add(cp);

        pack();
    }
}

class calculatorPanel extends JPanel
{
    private JPanel myPanel2;

    private JButton window;

    private boolean initial = true;

    private double result = 0;

    private String lastOperation = "";
    
    public calculatorPanel()
    {
        setLayout(new BorderLayout());

        window = new JButton("0");

        window.setEnabled(false);

        add(window, BorderLayout.NORTH);

        myPanel2 = new JPanel();

        myPanel2.setLayout(new GridLayout(4, 4));

        String botonesx = "789/456*123-0.=+";

        for (int i = 0; i < botonesx.length(); i++)
        {
            char sucesion = botonesx.charAt(i);

            if (sucesion != '+' && sucesion != '-' &&
            sucesion != '*' && sucesion != '.' &&
            sucesion != '/' && sucesion != '=')
            {
                placeButtons(Character.toString(sucesion), new insertNumber());

            }

            else
            {
                placeButtons(Character.toString(sucesion), new orderAction());

            }

        }

        add(myPanel2, BorderLayout.CENTER);
    }

    private class orderAction implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            String operation = e.getActionCommand();
            
            lastOperation = operation;
            
            calculate(Double.parseDouble(window.getText()));

            initial = true;
        }

        public void calculate(double x)
        {
            if(lastOperation.equalsIgnoreCase("+"))
            {
                result+=x;
                
            }
            
            else if(lastOperation.equalsIgnoreCase("-"))
            {
                result-=x;
            }
            
            else if(lastOperation.equalsIgnoreCase("*"))
            {
                result*=x;
            }
            
            else if(lastOperation.equalsIgnoreCase("/"))
            {
                result/=x;
            }
            
            else
            {
                result = x;
            }
            
            window.setText(String.valueOf(result));
        }

    }

    private class insertNumber implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            String entry = e.getActionCommand();

            if (initial)
            {
                window.setText("");

                initial = false;
            }

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