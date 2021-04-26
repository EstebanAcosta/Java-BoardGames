package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

public class PlayingWithAreaText
{

    public static void main(String[] args)
    {
        areaFrame af = new areaFrame();

        af.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}

class areaFrame extends JFrame
{

    public areaFrame()
    {
        setTitle("Text Area Frame");

        setBounds(600, 300, 600, 350);

        areaPanel ap = new areaPanel();

        add(ap);

        setVisible(true);
    }

}

class areaPanel extends JPanel
{
    public areaPanel()
    {

    }
}
