package JavaBoardGames.GUI;

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

class buttonPanel extends JPanel implements ActionListener
{

    JButton blueButton = new JButton(" Azul");

    JButton redButton = new JButton(" Rojo");

    JButton yellowButton = new JButton(" Amarillo");

    public buttonPanel()
    {
        add(blueButton);

        add(redButton);

        add(yellowButton);

        blueButton.addActionListener(this);

        redButton.addActionListener(this);

        yellowButton.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e)
    {
        Object buttonPressed = e.getSource();

        if (buttonPressed == blueButton)
        {
            setBackground(Color.BLUE);

        }

        else if (buttonPressed == yellowButton)
        {
            setBackground(Color.YELLOW);

        }

        else
        {
            setBackground(Color.RED);

        }

    }
}
