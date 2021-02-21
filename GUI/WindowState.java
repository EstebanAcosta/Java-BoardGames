package GUI;

import java.awt.Frame;
import java.awt.event.*;

import javax.swing.*;


public class WindowState
{
    public static void main(String[] args)
    {
        FrameState fs = new FrameState();

        fs.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}

class FrameState extends JFrame
{
    public FrameState()
    {
        setTitle("Changing States");

        setVisible(true);

        setBounds(300, 300, 500, 250);

        changeState cs = new changeState();
        
        addWindowStateListener(cs);
    }
}

class changeState implements WindowStateListener
{
    public void windowStateChanged(WindowEvent e)
    {
        System.out.println("Window has changed state");
        
//        System.out.println("Old State " + e.getOldState());

//        System.out.println("New State " + e.getNewState());
        
        if(e.getNewState() == Frame.MAXIMIZED_BOTH)
        {
            System.out.println("Window is complete");
        }
        
        else if(e.getNewState() == Frame.NORMAL)
        {
            System.out.println("Window is normal");

        }
        
        else if(e.getNewState() == Frame.ICONIFIED)
        {
            System.out.println("Window is minimized");

        }
    }
}