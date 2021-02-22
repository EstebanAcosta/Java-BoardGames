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
        setTitle("Using Mouse");

        setVisible(true);

        setBounds(300, 300, 500, 250);

        // addMouseListener(new playingWithMouse());

        // addMouseListener(new playingWithMouse2());

        addMouseMotionListener(new playingWithMouseMotion());
    }
}

class playingWithMouseMotion implements MouseMotionListener
{

    @Override
    public void mouseDragged(MouseEvent e)
    {
        System.out.println("You're dragging it");
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
        System.out.println("Youre moving");
    }

}

class playingWithMouse2 extends MouseAdapter
{
    // public void mouseClicked(MouseEvent e)
    // {
    // System.out.println("YOU CLICKED");
    //
    // System.out.println("X Coord. " + e.getX());
    //
    // System.out.println("Y Coord. " + e.getY());
    //
    // System.out.println("Count of clicks " + e.getClickCount());
    //
    // System.out.println();
    // }

    public void mousePressed(MouseEvent e)
    {
        // System.out.println(e.getModifiersEx());

        if (e.getModifiersEx() == MouseEvent.BUTTON1_DOWN_MASK)
        {
            System.out.println("You pressed the left button");
        }

        else if (e.getModifiersEx() == MouseEvent.BUTTON3_DOWN_MASK)
        {
            System.out.println("You pressed the right button");
        }

        else
        {
            System.out.println("You pressed the mouse wheel");
        }
    }
}

class playingWithMouse implements MouseListener
{

    @Override
    public void mouseClicked(MouseEvent e)
    {
        System.out.println("You clicked");
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        System.out.println("You just pressed this");
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        System.out.println("You just released your finger");
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
        System.out.println("You just enterd");
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
        System.out.println("You just exited");
    }

}
