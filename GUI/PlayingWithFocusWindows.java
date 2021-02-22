package GUI;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JFrame;

public class PlayingWithFocusWindows extends JFrame implements WindowFocusListener
{
    static PlayingWithFocusWindows fs = new PlayingWithFocusWindows();

    static PlayingWithFocusWindows fs2 = new PlayingWithFocusWindows();

    public static void main(String[] args)
    {

        PlayingWithFocusWindows p = new PlayingWithFocusWindows();

        p.initialize();

    }

    public void initialize()
    {

        fs.setBounds(300, 300, 500, 250);

        fs2.setBounds(800, 100, 600, 350);

        fs.setVisible(true);

        fs2.setVisible(true);

        fs.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        fs2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        fs.addWindowFocusListener(this);

        fs2.addWindowFocusListener(this);

    }

    @Override
    public void windowGainedFocus(WindowEvent e)
    {
        if (e.getSource() == fs)
        {
            fs.setTitle("FOCUS");

        }

        else
        {
            fs2.setTitle("FOCUS");
        }
    }

    @Override
    public void windowLostFocus(WindowEvent e)
    {
        if (e.getSource() == fs)
        {
            fs.setTitle("lost it");

        }

        else
        {
            fs2.setTitle("lost it");
        }
    }

}
