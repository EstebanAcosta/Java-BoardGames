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

        setBounds(500, 300, 700, 500);

        add(new calculatorPanel());
        
        setVisible(true);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
