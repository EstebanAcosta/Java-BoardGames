package CalculatorGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.LineBorder;

import com.sun.glass.events.KeyEvent;

public class calculatorPanel extends JPanel
{

    JButton[][] functionGrid;

    JButton screen;

    ArrayList<String> list_of_operations = new ArrayList<String>();

    double result;

    boolean initial;

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

        String[] operations = { "x!", "+", "-", "*", "/", "%", "sqrt", "log", "ln" };

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

                    result = 0;
                }

            }

        });

        centralLeftPanel.add(clear);

        JButton plus_minus = new JButton("+/-");

        plus_minus.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {
                screen.setText(Double.toString(Double.parseDouble(screen.getText()) * -1));
            }

        });

        centralLeftPanel.add(plus_minus);

        for (String trig : trigFunctions)
        {
            JButton tFunct = new JButton(trig);

            tFunct.addActionListener(new ActionListener()
            {

                public void actionPerformed(ActionEvent e)
                {

                    JButton originator = (JButton) e.getSource();

                    // if (originator.getText().equalsIgnoreCase("sin"))
                    // {
                    // Math.sin();
                    // }
                    //
                    // else if (originator.getText().equalsIgnoreCase("cos"))
                    // {
                    // Math.cos();
                    // }
                    // else if (originator.getText().equalsIgnoreCase("tan"))
                    // {
                    // Math.tan();
                    // }
                    // else if (originator.getText().equalsIgnoreCase("csc"))
                    // {
                    // 1/Math.sin();
                    // }
                    // else if (originator.getText().equalsIgnoreCase("sec"))
                    // {
                    // 1/Math.cos();
                    //
                    // }
                    //
                    // else
                    // {
                    // 1/Math.tan();
                    //
                    // }
                }

            });

            centralLeftPanel.add(tFunct);
        }

        for (String inv : inverseFunctions)
        {
            JButton invF = new JButton(inv);

            invF.addActionListener(new ActionListener()
            {

                public void actionPerformed(ActionEvent e)
                {
                    JButton originator = (JButton) e.getSource();

                    if (originator.getText().equalsIgnoreCase("arcsin"))
                    {

                    }

                    else if (originator.getText().equalsIgnoreCase("arccos"))
                    {

                    }
                    else if (originator.getText().equalsIgnoreCase("arctan"))
                    {

                    }

                }

            });

            centralLeftPanel.add(invF);
        }

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////

        JPanel centralCentralPanel = new JPanel();

        centralCentralPanel.setLayout(new GridLayout(4, 6));

        // inv.addActionListener(new ActionListener()
        // {
        //
        // public void actionPerformed(ActionEvent e)
        // {
        //
        // }
        //
        // });

        for (int i = 9; i >= 0; i--)
        {
            JButton num = new JButton(Integer.toString(i));

            num.addActionListener(new ActionListener()
            {

                public void actionPerformed(ActionEvent e)
                {
                    String entry = e.getActionCommand();

                    if (initial)
                    {
                        screen.setText("");

                        initial = false;
                    }

                    screen.setText(screen.getText() + entry);

                    clear.setText("C");

                }

            });

            centralCentralPanel.add(num);

        }

        JButton equal = new JButton("=");

        centralCentralPanel.add(equal);

        equal.addActionListener(new orderAction());

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

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////

        JPanel centralRightPanel = new JPanel(new GridLayout(4, 6));

        for (String operation : operations)
        {
            JButton op = new JButton(operation);

            op.addActionListener(new orderAction());

            centralRightPanel.add(op);
        }

        add(upperPanel, BorderLayout.NORTH);

        add(centralLeftPanel, BorderLayout.WEST);

        add(centralCentralPanel, BorderLayout.CENTER);

        add(centralRightPanel, BorderLayout.EAST);

    }

    private class orderAction implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            list_of_operations.add(e.getActionCommand());

            initial = true;

            calculate(Double.parseDouble(screen.getText()));
        }

        public void calculate(double x)
        {

            int list_size = list_of_operations.size();

            String lastOperation = list_of_operations.get(list_size - 1);

            if (lastOperation.equalsIgnoreCase("+"))
                result += x;

            else if (lastOperation.equalsIgnoreCase("-"))
                result -= x;

            else if (lastOperation.equalsIgnoreCase("*"))
                result *= x;

            else if (lastOperation.equalsIgnoreCase("/"))
            {
                if (x == 0)
                {
                    screen.setText(String.valueOf("Undefined"));

                    result = 0;
                    
                    list_of_operations.clear();
                }

                else
                    result /= x;

            }

            else if (lastOperation.equalsIgnoreCase("="))
            {

                if (list_size > 2 && list_of_operations.get(list_size - 2).equalsIgnoreCase("="))
                {

                }

                screen.setText(String.valueOf(result));
            }
            
            
            if(list_size >= 2)
                screen.setText(String.valueOf(result));

        }

    }
}
