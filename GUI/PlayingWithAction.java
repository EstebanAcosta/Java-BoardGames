package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class PlayingWithAction
{

    public static void main(String[] args)
    {
        actionFrame af = new actionFrame();
    }
}

class actionFrame extends JFrame
{
    public actionFrame()
    {
        setTitle("ToolBar Frame");

        setBounds(300, 300, 600, 400);

        add(new actionPanel());

        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}

class actionPanel extends JPanel
{

    public actionPanel()
    {
        colorAction aYellow = new colorAction("Yellow", Color.YELLOW);

        colorAction aRed = new colorAction("Red", Color.RED);

        colorAction aBlue = new colorAction("Blue", Color.BLUE);

        JButton yellowButton = new JButton(aYellow);

        JButton RedButton = new JButton(aRed);

        JButton BlueButton = new JButton(aBlue);

        InputMap mapEntry = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);

        KeyStroke yellowKey = KeyStroke.getKeyStroke("ctrl Y");

        KeyStroke blueKey = KeyStroke.getKeyStroke("ctrl B");

        KeyStroke redKey = KeyStroke.getKeyStroke("ctrl R");
        
        mapEntry.put(yellowKey, "yellow_background");
        
        mapEntry.put(redKey, "red_background");
        
        mapEntry.put(blueKey, "blue_background");
        
        ActionMap mapAction = getActionMap();
        
        mapAction.put("yellow_background", aYellow);
        
        mapAction.put("red_background", aRed);
        
        mapAction.put("blue_background", aBlue);

        add(RedButton);

        add(BlueButton);

        add(yellowButton);

    }

    private class colorAction extends AbstractAction
    {

        // public colorAction(String name, Icon icon, Color colorButton)
        // {
        // putValue(Action.NAME, name);
        //
        // putValue(Action.SMALL_ICON, icon);
        //
        // putValue(Action.SHORT_DESCRIPTION, " Makes the panel the color " + name);
        //
        // putValue("background_color", colorButton);
        //
        // }

        public colorAction(String name, Color colorButton)
        {
            putValue(Action.NAME, name);

            putValue(Action.SHORT_DESCRIPTION, " Makes the panel the color " + name);

            putValue("background_color", colorButton);

        }

        public void actionPerformed(ActionEvent e)
        {

            Color c = (Color) getValue("background_color");

            setBackground(c);

            System.out.println("Name: " + getValue(Action.NAME) + " Description: " + getValue(Action.SHORT_DESCRIPTION));
        }

    }

}
