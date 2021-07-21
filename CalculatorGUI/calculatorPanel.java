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

    public calculatorPanel()
    {
        setLayout(new BorderLayout());

        JPanel centralPanel = new JPanel();

        centralPanel.setLayout(new BorderLayout());

        JPanel upperPanel = new JPanel();

        screen = new JButton();

        screen.setText("");

        Dimension d = new Dimension(400, 20);

        screen.setPreferredSize(d);

        screen.setEnabled(false);

        upperPanel.add(screen);

        for (int i = 0; i < 9; i++)
        {
            JButton num = new JButton(Integer.toString(i));

            num.addActionListener(new ActionListener()
            {

                public void actionPerformed(ActionEvent e)
                {
                    if(screen.getText() == "")
                    {
                        screen.setText(num.getText());
                    }
                    
                    else
                    {
                        screen.setText(screen.getText() + num.getText());

                    }
                }

            });
        }

        add(upperPanel, BorderLayout.NORTH);

        add(centralPanel, BorderLayout.SOUTH);

    }
}
