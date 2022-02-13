package CalculatorGUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class calculatorPanel extends JPanel
{

    JButton[][] functionGrid;

    // Button used to display either numbers or calculations done on numbers
    JButton screen;

    // Dynamic array that stores the most recent operations used
    ArrayList<String> list_of_operations = new ArrayList<String>();

    int numOps = 0;

    // Keeps track of the calculation results
    double result;

    // Keeps track of whether or not a new number has been entered
    boolean newEntry = true;

    public calculatorPanel()
    {

        setLayout(new BorderLayout());

        JPanel upperPanel = new JPanel();

        screen = new JButton();

        screen.setText("");

        Dimension d = new Dimension(400, 40);

        screen.setPreferredSize(d);

        screen.setEnabled(false);

        upperPanel.add(screen);

        String[] operations = { "+", "-", "*", "/", "%", "sqrt", "x!", "x^2", "x^n", "log", "ln", "e^x" };

        String[] trigFunctions = { "sin", "cos", "tan", "csc", "sec", "cot" };

        String[] inverseFunctions = { "arcsin", "arcos", "arctan" };

        String[] constants = { "π", "e" };

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////

        JPanel centralLeftPanel = new JPanel();

        centralLeftPanel.setLayout(new GridLayout(4, 6));

        JButton clear = new JButton("C");

        clear.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {
                if (clear.getText().equalsIgnoreCase("C"))
                {

                    clear.setText("AC");

                    screen.setText("0");

                    list_of_operations.clear();

                    result = 0;

                    newEntry = false;
                }

            }

        });

        centralLeftPanel.add(clear);

        JButton plus_minus = new JButton("+/-");

        plus_minus.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {
                double number = Double.parseDouble(screen.getText());
                if (number % 1 == 0)
                    screen.setText(String.valueOf((int) number * -1));
                else
                    screen.setText(Double.toString(number * -1));

            }

        });

        centralLeftPanel.add(plus_minus);

        for (String trig : trigFunctions)
        {
            JButton tFunct = new JButton(trig);

            tFunct.addActionListener(new trigAction());

            centralLeftPanel.add(tFunct);
        }

        for (String inv : inverseFunctions)
        {
            JButton invF = new JButton(inv);

            invF.addActionListener(new trigAction());

            centralLeftPanel.add(invF);
        }

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////

        JPanel centralCentralPanel = new JPanel();

        centralCentralPanel.setLayout(new GridLayout(4, 6));

        for (int i = 9; i >= 0; i--)
        {
            JButton num = new JButton(Integer.toString(i));

            num.addActionListener(new ActionListener()
            {

                public void actionPerformed(ActionEvent e)
                {
                    String entry = e.getActionCommand();

                    if (!newEntry)
                    {
                        screen.setText("");

                        newEntry = true;
                    }

                    screen.setText(screen.getText() + entry);

                    clear.setText("C");

                }

            });

            centralCentralPanel.add(num);

        }

        JButton dot = new JButton(".");

        dot.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {

                if (!screen.getText().contains("."))
                {
                    screen.setText(screen.getText() + ".");
                }

            }

        });

        centralCentralPanel.add(dot);

        JButton equal = new JButton("=");

        equal.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {

            }
        });

        centralCentralPanel.add(equal);

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////

        JPanel centralRightPanel = new JPanel(new GridLayout(4, 6));

        for (String operation : operations)
        {
            JButton op = new JButton(operation);

            op.addActionListener(new operationAction());

            centralRightPanel.add(op);
        }

        add(upperPanel, BorderLayout.NORTH);

        add(centralLeftPanel, BorderLayout.WEST);

        add(centralCentralPanel, BorderLayout.CENTER);

        add(centralRightPanel, BorderLayout.EAST);

    }

    private class trigAction implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            JButton trigButton = (JButton) e.getSource();

            newEntry = false;

            String trigFunc = trigButton.getText();

            calculate(trigFunc, Double.parseDouble(screen.getText()));

        }

        public void calculate(String trigFunc, Double num)
        {

            if (trigFunc.equalsIgnoreCase("sin"))
                result = Math.sin(num);

            else if (trigFunc.equalsIgnoreCase("cos"))

                result = Math.cos(result);

            else if (trigFunc.equalsIgnoreCase("tan"))

                result = Math.tan(num);

            else if (trigFunc.equalsIgnoreCase("csc"))

                result = 1 / Math.sin(num);

            else if (trigFunc.equalsIgnoreCase("sec"))

                result = 1 / Math.cos(num);

            else if (trigFunc.equalsIgnoreCase("cot"))

                result = 1 / Math.tan(num);

            else if (trigFunc.equalsIgnoreCase("arcsin"))
                result = Math.asin(num);

            else if (trigFunc.equalsIgnoreCase("arccos"))
                result = Math.acos(num);

            else if (trigFunc.equalsIgnoreCase("arctan"))
                result = Math.atan(num);

            screen.setText(String.valueOf(result));

        }

    }

    private class operationAction implements ActionListener
    {

        boolean basicOperation = false;

        public void actionPerformed(ActionEvent e)
        {

            String operation = e.getActionCommand();

            newEntry = false;

            if (operation.equalsIgnoreCase("+") || operation.equalsIgnoreCase("-")
            || operation.equalsIgnoreCase("*") || operation.equalsIgnoreCase("/")
            || operation.equalsIgnoreCase("="))
            {
                basicOperation = true;
            }

            list_of_operations.add(operation);

            calculate(Double.parseDouble(screen.getText()));
        }

        public void calculate(double num)
        {

            int list_size = list_of_operations.size();

            String lastOperation = list_of_operations.get(list_size - 1);

            if (list_size <= 1 && basicOperation)
                result = num;

            else
            {
                if (lastOperation.equalsIgnoreCase("+"))
                    result += num;

                else if (lastOperation.equalsIgnoreCase("-"))
                    result -= num;

                else if (lastOperation.equalsIgnoreCase("*"))
                    result *= num;

                else if (lastOperation.equalsIgnoreCase("/"))
                {
                    if (num == 0)
                    {
                        screen.setText("Undefined");

                        result = 0;

                        list_of_operations.clear();
                    }

                    else
                        result /= num;

                }

                else if (lastOperation.equalsIgnoreCase("%"))
                {
                    result = num / 100;
                }

                else if (lastOperation.equalsIgnoreCase("sqrt"))
                    result = Math.sqrt(num);

                else if (lastOperation.equalsIgnoreCase("x!"))
                {
                    if (num % 1 == 0 && num >= 0)
                    {
                        long answer = findFactorial(new Hashtable<Integer, Long>(), (int) num);

                        screen.setText(String.valueOf(answer));
                    }

                    else
                    {
                        if (num <= 0)
                            screen.setText("Can't Take The Factorial of a Negative Value");
                        else
                            screen.setText("Can't Take The Factorial of a Decimal Value");

                        result = 0;

                        list_of_operations.clear();
                    }

                }

                else if (lastOperation.equalsIgnoreCase("x^2"))
                {
                    result = num * num;
                }

                else if (lastOperation.equalsIgnoreCase("x^n"))
                {
                    int exp = 0;

                    JFrame getExpFrame = new JFrame();

                    getExpFrame.setBounds(300, 400, 200, 200);

                    getExpFrame.setTitle("Get Exponent");

                    getExpFrame.setResizable(false);

                    JSpinner randNumSpinner = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));

                    JPanel getExPanel = new JPanel();
                    
                    JButton submitButton = new JButton("Submit");

                    getExpFrame.add(getExPanel);

                    getExPanel.setLayout(new BorderLayout());

                    getExPanel.add(randNumSpinner, BorderLayout.CENTER);

                    getExpFrame.setVisible(true);

                    getExpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                    result = Math.pow(num, exp);
                }

                else if (lastOperation.equalsIgnoreCase("log"))
                {
                    result = Math.log(num);
                }

                else if (lastOperation.equalsIgnoreCase("ln"))
                {
                    result = Math.log(num) / Math.log(Math.E);
                }

            }

            // if the operation isn't a factorial
            if (!lastOperation.equalsIgnoreCase("x!"))
            {
                // if the result is an integer
                if (result % 1 == 0)
                    // cast the result to an integer then print the result
                    screen.setText(String.valueOf((int) result));

                // if the result is a double/float
                else
                    // print the result as is
                    screen.setText(String.valueOf(result));
            }

        }

        /***
         * Memoized factorial method. Takes a hashtable and the value we wish to find the factorial of and recursively
         * looks for the factorial of the parameter value either by checking if it's already been computed and stored in a
         * hash table or by computing the factorial manually.
         * @param factorialTable
         * @param num
         * @return
         */
        public long findFactorial(Hashtable<Integer, Long> factorialTable, int num)
        {

            if (num <= 1)
                return 1;

            if (factorialTable.containsKey(num))
                return factorialTable.get(num);

            else
            {
                factorialTable.put(num, (long) (num * findFactorial(factorialTable, num - 1)));
                return factorialTable.get(num);
            }

        }

    }
}
