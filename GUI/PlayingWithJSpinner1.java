package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

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
        setTitle("Slider Frame");

        setBounds(600, 300, 600, 350);

        add(new spinnerPanel());

    }

}

class spinnerPanel extends JPanel
{
    public spinnerPanel()
    {
        JSpinner control = new JSpinner(new SpinnerDateModel());
        
        add(control);
    }
}