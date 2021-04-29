package GUI;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PlayingWithSlider2
{
    public static void main(String[] args)
    {
        sliderFrame2 sf = new sliderFrame2();

        sf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        sf.setVisible(true);

    }
}

class sliderFrame2 extends JFrame
{

    public sliderFrame2()
    {
        setTitle("Slider Frame");

        setBounds(600, 300, 600, 350);

        add(new sliderPanel2());

    }

}

class sliderPanel2 extends JPanel
{

    private JLabel text;

    private JSlider control;

    public sliderPanel2()
    {
        setLayout(new BorderLayout());

        text = new JLabel("La vie c'est un peu complique. N'est-ce pas");

        JPanel textPanel = new JPanel();

        Font f = new Font("Times New Roman", Font.ITALIC, 13);

        text.setFont(f);

        textPanel.add(text);

        add(textPanel, BorderLayout.CENTER);

        control = new JSlider(5, 55, 10);

        control.setFont(new Font("Times New Roman", Font.ITALIC, 12));

        control.setMajorTickSpacing(25);

        control.setMinorTickSpacing(5);

        control.setPaintTicks(true);

        control.setPaintLabels(true);
        
        control.addChangeListener(new sliderEvent());

        JPanel sliderPanel = new JPanel();

        sliderPanel.add(control);

        add(sliderPanel, BorderLayout.NORTH);

    }

    private class sliderEvent implements ChangeListener
    {

        public void stateChanged(ChangeEvent e)
        {
            text.setFont(new Font("Ariel", Font.ITALIC, control.getValue()));
        }

    }

}