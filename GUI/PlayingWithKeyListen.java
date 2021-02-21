package GUI;

import javax.swing.*;

import java.awt.event.*;



public class PlayingWithKeyListen
{

    public static void main(String[] args)
    {
        keyFrame fs = new keyFrame();

        fs.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}

class keyFrame extends JFrame
{
    public keyFrame()
    {
        setTitle("Using keys");

        setVisible(true);

        setBounds(300, 300, 500, 250);

        addKeyListener(new playingWithKeys());

    }
}

class playingWithKeys implements KeyListener
{

    public void keyTyped(KeyEvent e)
    {
        char code = e.getKeyChar();
        
        System.out.println(code);
    }

    public void keyPressed(KeyEvent e)
    {
//        int code = e.getKeyCode();
//
//        System.out.println(code);
    }

    public void keyReleased(KeyEvent e)
    {

    }

}