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

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////

        JPanel centralLeftPanel = new JPanel();

        centralLeftPanel.setLayout(new GridLayout(3, 3));

        String[] operations = { "x!", "+", "-", "*", "/", "E", "%", "x", "sqrt,log,ln" };

        String[] trigFunctions = { "sin", "cos", "tan", "csc", "sec", "cot" };

        String[] inverseFunctions = { "arcsin", "arcos", "arctan" };

        String[] constants = { "π", "e" };

        JButton inv = new JButton("Inv");

        for (String trig : trigFunctions)
        {
            JButton tFunct = new JButton(trig);

            centralLeftPanel.add(tFunct);
        }

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////

        JPanel centralCentralPanel = new JPanel();

        centralCentralPanel.setLayout(new GridLayout(4, 6));

        inv.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {

            }

        });

        centralCentralPanel.add(inv);

        for (int i = 0; i < 10; i++)
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
        
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////

        JPanel centralRightPanel = new JPanel(new GridLayout());
        
        
        for (String operation : operations)
        {
            JButton = new JButton(operation);
            
            centralRightPanel.add();
        }

        add(upperPanel, BorderLayout.NORTH);

        add(centralLeftPanel, BorderLayout.WEST);

        add(centralCentralPanel, BorderLayout.CENTER);

    }
}
