package GUI;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;

public class PlayingWithJSlider
{
    public static void main(String[] args)
    {
        sliderFrame sf = new sliderFrame();

        sf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        sf.setVisible(true);

    }
}

class sliderFrame extends JFrame
{

    public sliderFrame()
    {
        setTitle("Slider Frame");

        setBounds(600, 300, 600, 350);

        add(new sliderPanel());

    }

}

class sliderPanel extends JPanel
{

    public sliderPanel()
    {

        // JSlider control = new JSlider(SwingConstants.VERTICAL, 10, 100, 25);

        JSlider control = new JSlider(0, 100, 25);

        control.setMajorTickSpacing(50);

        control.setMinorTickSpacing(25);

        control.setFont(new Font("Times New Roman", Font.ITALIC, 12));
        
//        control.setSnapToTicks(true);

        control.setPaintTicks(true);

        control.setPaintLabels(true);

        add(control);

    }

}