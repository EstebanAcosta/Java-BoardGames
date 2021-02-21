package GUI;

import javax.swing.*;

import java.awt.event.*;

public class PlayingWithMouseListen
{

    public static void main(String[] args)
    {
        mouseFrame fs = new mouseFrame();

        fs.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}

class mouseFrame extends JFrame
{
    public mouseFrame()
    {
        setTitle("Using keys");

        setVisible(true);

        setBounds(300, 300, 500, 250);

        addKeyListener(new playingWithKeys());

    }
}

//class playingWithMouse implements MouseListener
//{
//    
//    
//}

