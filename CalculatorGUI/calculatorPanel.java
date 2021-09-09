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

    String lastOperation;

    double result = 0;

    public calculatorPanel()
    {
        setLayout(new BorderLayout());

        JPanel upperPanel = new JPanel();

        screen = new JButton();

        screen.setText("");

        Dimension d = new Dimension(400, 20);

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

        JButton inv = new JButton("Inv");

        for (String trig : trigFunctions)
        {
            JButton tFunct = new JButton(trig);

            tFunct.addActionListener(new ActionListener()
            {

                public void actionPerformed(ActionEvent e)
                {

                }

            });

            centralLeftPanel.add(tFunct);
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
                    if (screen.getText() == "")
                    {
                        screen.setText(num.getText());
                    }

                    else
                    {
                        screen.setText(screen.getText() + num.getText());

                    }
                }

            });

            centralCentralPanel.add(num);

        }

        JButton equal = new JButton("=");

        centralCentralPanel.add(equal);

        JButton dot = new JButton(".");

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
            String operation = e.getActionCommand();

            lastOperation = operation;

            calculate(Double.parseDouble(screen.getText()));
        }

        public void calculate(double x)
        {
            if (lastOperation.equalsIgnoreCase("+"))
            {
                result += x;

            }

            else if (lastOperation.equalsIgnoreCase("-"))
            {
                result -= x;
            }

            else if (lastOperation.equalsIgnoreCase("*"))
            {
                result *= x;
            }

            else if (lastOperation.equalsIgnoreCase("/"))
            {
                result /= x;
            }

            else
            {
                result = x;
            }

            screen.setText(String.valueOf(result));
        }

    }
}
