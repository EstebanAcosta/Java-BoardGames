package GUI;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;

public class PlayingWithJSpinner1
{
    public static void main(String[] args)
    {
        spinnerFrame sf = new spinnerFrame();

        sf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        sf.setVisible(true);

    }
}

class spinnerFrame extends JFrame
{

    public spinnerFrame()
    {
        setTitle("Spinner Frame");

        setBounds(600, 300, 600, 350);

        add(new spinnerPanel());

    }

}

class spinnerPanel extends JPanel
{
    public spinnerPanel()
    {
        // String [] list = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

        // JSpinner control = new JSpinner(new SpinnerNumberModel(5,0,50,2));

        // JSpinner control = new JSpinner(new SpinnerListModel(list));

        JSpinner control = new JSpinner(new SpinnerNumberModel(5, 0, 50, 1)
        {
            public Object getNextValue()
            {

                return super.getPreviousValue();
            }

            public Object getPreviousValue()
            {

                return super.getNextValue();
            }
        });

        Dimension d = new Dimension(200, 20);

        control.setPreferredSize(d);

        add(control);
    }

    // private class MyModelJSpinner extends SpinnerNumberModel
    // {
    // public MyModelJSpinner()
    // {
    // super(5,0,50,1);
    // }
    //
    // public Object getNextValue()
    // {
    //
    // return super.getPreviousValue();
    // }
    //
    // public Object getPreviousValue()
    // {
    //
    // return super.getNextValue();
    // }
    // }
}