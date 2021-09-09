package CalculatorGUI;

import javax.swing.JFrame;

public class Calculator
{
    public static void main(String[] args)
    {
        calculatorFrame cF = new calculatorFrame();

    }
}

class calculatorFrame extends JFrame
{
    public calculatorFrame()
    {
        setTitle("Calculator");        

        add(new calculatorPanel());
        
        pack();
        
        setLocation(500,200);
        
        setResizable(false);
        
        setVisible(true);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
