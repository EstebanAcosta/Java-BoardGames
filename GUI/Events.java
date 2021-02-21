package GUI;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Events
{

    public static void main(String[] args)
    {
        buttons b = new buttons();
    }

}

class buttons extends JFrame
{
    public buttons()
    {
        setTitle("Playing with buttons");

        setBounds(700, 300, 500, 300);

        buttonPanel bp = new buttonPanel();

        add(bp);

        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

class buttonPanel extends JPanel
{

    JButton blueButton = new JButton(" Azul");

    JButton redButton = new JButton(" Rojo");

    JButton yellowButton = new JButton(" Amarillo");

    public buttonPanel()
    {
        add(blueButton);

        add(redButton);

        add(yellowButton);
        
        backgroundColor yellow = new backgroundColor(Color.yellow);
        
        backgroundColor blue= new backgroundColor(Color.blue);
        
        backgroundColor red= new backgroundColor(Color.red);

        blueButton.addActionListener(blue);

        redButton.addActionListener(red);

        yellowButton.addActionListener(yellow);
        


    }
    
    private class backgroundColor implements ActionListener
    {
        private Color backColor;

        public backgroundColor(Color c)
        {
            backColor = c;
        }

        public void actionPerformed(ActionEvent e)
        {

            setBackground(backColor);

        }
    }

    // public void actionPerformed(ActionEvent e)
    // {
    // Object buttonPressed = e.getSource();
    //
    // if (buttonPressed == blueButton)
    // {
    // setBackground(Color.BLUE);
    //
    // }
    //
    // else if (buttonPressed == yellowButton)
    // {
    // setBackground(Color.YELLOW);
    //
    // }
    //
    // else
    // {
    // setBackground(Color.RED);
    //
    // }
    //
    // }

}


