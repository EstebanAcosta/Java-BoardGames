package GUI;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class PlayingWithWindowsListener
{

    public static void main(String[] args)
    {
        FrameWindow fw = new FrameWindow();

        fw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        fw.setTitle("Window 1");

        fw.setBounds(300, 300, 500, 350);

        FrameWindow fw2 = new FrameWindow();

        fw2.setTitle("Window 2");

        fw2.setBounds(300, 400, 300, 350);

        fw2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

}

class FrameWindow extends JFrame
{
    public FrameWindow()
    {

        // setTitle("Responding");
        //
        // setBounds(300, 300, 500, 350);

        setVisible(true);

        F_Window listener = new F_Window();

        N_Window listener2 = new N_Window();

        addWindowListener(listener);

        addWindowListener(listener2);

    }

}

class N_Window extends WindowAdapter
{
    public void windowIconified(WindowEvent e)
    {
        System.out.println("Window minimized");
    }
}

class F_Window implements WindowListener
{

    public void windowOpened(WindowEvent e)
    {
        System.out.println("Window open");
    }

    public void windowClosing(WindowEvent e)
    {
        System.out.println("Window closing");

    }

    public void windowClosed(WindowEvent e)
    {
        System.out.println("Window has been closed");
    }

    public void windowIconified(WindowEvent e)
    {
        System.out.println("Window minimized");
    }

    public void windowDeiconified(WindowEvent e)
    {
        System.out.println("Window restored");
    }

    public void windowActivated(WindowEvent e)
    {
        System.out.println("Window activated");
    }

    public void windowDeactivated(WindowEvent e)
    {
        System.out.println("Window inactive");
    }

}
