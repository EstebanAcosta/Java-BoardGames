package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class PlayingWithToolBar
{
    public static void main(String[] args)
    {
        toolbarFrame af = new toolbarFrame();

        af.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        af.setVisible(true);
    }
}

class toolbarFrame extends JFrame
{
    toolbarPanel panel;
    
    public toolbarFrame()
    {
        setTitle("Toolbar Frame");

        setBounds(300, 300, 600, 400);

        panel = new toolbarPanel();
        
        add(panel);

        Action aYellow = new colorAction("Yellow", Color.YELLOW);

        Action aRed = new colorAction("Red", Color.RED);

        Action aBlue = new colorAction("Blue", Color.BLUE);
        
        Action goOut = new AbstractAction("Exit")
        {

            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
            
        };

        JMenu menu = new JMenu("Color");

        menu.add(aBlue);

        menu.add(aYellow);

        menu.add(aRed);

        JMenuBar barMenu = new JMenuBar();

        barMenu.add(menu);

        setJMenuBar(barMenu);
        
        //toolbar creation
        
        JToolBar bar = new JToolBar();
        
        bar.add(aBlue);
        
        bar.add(aRed);
        
        bar.add(aYellow);
        
        bar.addSeparator();
        
        bar.add(goOut);
        
        add(bar, BorderLayout.NORTH);

    }

    private class colorAction extends AbstractAction
    {

        public colorAction(String name, Color colorButton)
        {
            putValue(Action.NAME, name);

            putValue(Action.SHORT_DESCRIPTION, " Makes the panel the color " + name);

            putValue("background_color", colorButton);

        }

        public void actionPerformed(ActionEvent e)
        {

            Color c = (Color) getValue("background_color");

            panel.setBackground(c);

            System.out.println("Name: " + getValue(Action.NAME) + " Description: " + getValue(Action.SHORT_DESCRIPTION));
        }

    }
}

class toolbarPanel extends JPanel
{

    public toolbarPanel()
    {

    }

}