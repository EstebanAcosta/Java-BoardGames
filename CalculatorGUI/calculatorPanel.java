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

        JPanel centralPanel = new JPanel();

        centralPanel.setLayout(new GridLayout(4, 6));

        String[] operations = { "x!", "+", "-", "*", "/", "E", "%","x","sqrt,log,ln" };

        String[] trigFunctions = { "sin", "cos", "tan", "csc", "sec", "cot" };

        String[] inverseFunctions = {"arcsin","arcos","arctan"};
        
        String [] constants = {"π","e"};

        JButton inv = new JButton("Inv");

        inv.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {

            }

        });

        centralPanel.add(inv);

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

            centralPanel.add(num);

        }

        add(upperPanel, BorderLayout.NORTH);

        add(centralPanel, BorderLayout.CENTER);

    }
}
