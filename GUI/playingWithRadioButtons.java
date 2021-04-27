package GUI;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class playingWithRadioButtons
{
    public static void main(String[] args)
    {
        radioFrame rf = new radioFrame();

        rf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        rf.setVisible(true);

    }
}

class radioFrame extends JFrame
{

    public radioFrame()
    {
        setTitle("Radio Buttons Frame");

        setBounds(600, 300, 600, 350);

        add(new radioPanel());

    }

}

class radioPanel extends JPanel
{
    public radioPanel()
    {
        ButtonGroup myGroup = new ButtonGroup();

        ButtonGroup gender = new ButtonGroup();

        JRadioButton blue = new JRadioButton("Blue", false);

        JRadioButton black = new JRadioButton("Green", false);

        JRadioButton red = new JRadioButton("Red", false);

        JRadioButton male = new JRadioButton("Male", false);

        JRadioButton female = new JRadioButton("Female", false);

        myGroup.add(blue);

        myGroup.add(black);

        myGroup.add(red);

        gender.add(male);

        gender.add(female);

        add(blue);

        add(black);

        add(red);

        add(male);

        add(female);

    }

}