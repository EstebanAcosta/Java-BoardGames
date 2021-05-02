package GUI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class WordProcessor
{

    public static void main(String[] args)
    {
        wordProcessorFrame wpf = new wordProcessorFrame();

        wpf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        wpf.setVisible(true);

    }
}

class wordProcessorFrame extends JFrame
{

    public wordProcessorFrame()
    {
        setTitle("Slider Frame");

        setBounds(600, 300, 600, 350);

        add(new wordProcessorPanel());

    }

}

class wordProcessorPanel extends JPanel
{

    private JTextPane areaText;

    public wordProcessorPanel()
    {
        setLayout(new BorderLayout());

        JPanel menuPanel = new JPanel();

        JMenuBar menubar = new JMenuBar();

        /////////////////////////////////////////////////////////////////

        JMenu font = new JMenu("Font");

        JMenuItem arial = new JMenuItem("Arial");
        
        arial.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {
                areaText.setFont(new Font("Arial", Font.PLAIN, 12));
            }
        });

        JMenuItem courier = new JMenuItem("Courier");

        courier.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {
                areaText.setFont(new Font("Courier", Font.PLAIN, 12));
            }
        });

        JMenuItem times = new JMenuItem("Times");
        
        times.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {
                areaText.setFont(new Font("Times", Font.PLAIN, 12));
            }
        });

        font.add(arial);

        font.add(courier);

        font.add(times);

        //////////////////////////////////////////////////////////////////////

        JMenu style = new JMenu("Style");

        JMenuItem bold = new JMenuItem("Bold");

        JMenuItem cursive = new JMenuItem("Cursive");

        style.add(bold);

        style.add(cursive);

        /////////////////////////////////////////////////////////////////

        JMenu size = new JMenu("Size");

        JMenuItem thirteen = new JMenuItem("13");

        JMenuItem fourteen = new JMenuItem("14");

        JMenuItem twenty = new JMenuItem("20");

        size.add(thirteen);

        size.add(fourteen);

        size.add(twenty);

        /////////////////////////////////////////////////////////////////

        areaText = new JTextPane();

        menubar.add(font);

        menubar.add(style);

        menubar.add(size);

        menuPanel.add(menubar);

        add(menuPanel, BorderLayout.NORTH);

        add(areaText, BorderLayout.CENTER);

    }

}
