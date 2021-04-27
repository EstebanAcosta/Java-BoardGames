package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class playingWithRadioButtons2
{
    public static void main(String[] args)
    {
        radioFrame2 rf = new radioFrame2();

        rf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        rf.setVisible(true);

    }
}

class radioFrame2 extends JFrame
{

    public radioFrame2()
    {
        setTitle("Radio Buttons Frame");

        setBounds(600, 300, 600, 350);

        add(new radioPanel());

    }

}

class radioPanel2 extends JPanel
{

}