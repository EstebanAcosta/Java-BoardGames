package GUI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PlayingWithCheckBox
{
    public static void main(String[] args)
    {
        checkFrame cf = new checkFrame();

        cf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cf.setVisible(true);

    }
}

class checkFrame extends JFrame
{

    public checkFrame()
    {
        setTitle("CheckBox Frame");

        setBounds(600, 300, 600, 350);

        add(new checkPanel());

    }

}

class checkPanel extends JPanel
{

    private JLabel text;

    private JCheckBox check1, check2;

    private class manageChecks implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            int type = 0;

            if (check1.isSelected())
            {
                type += Font.BOLD;
            }

            if (check2.isSelected())
            {
                type += Font.ITALIC;
            }

            text.setFont(new Font("Serif", type, 24));
        }

    }

    public checkPanel()
    {
        setLayout(new BorderLayout());

        Font myFont = new Font("Serif", Font.PLAIN, 24);

        text = new JLabel("Je vais te dire la verite quand tu es prepare");

        text.setFont(myFont);

        JPanel textPanel = new JPanel();

        textPanel.add(text);

        add(textPanel, BorderLayout.CENTER);

        JPanel checkPanel = new JPanel();

        check1 = new JCheckBox("Bold");

        check2 = new JCheckBox("Italic");

        check1.addActionListener(new manageChecks());

        check2.addActionListener(new manageChecks());

        checkPanel.add(check1);

        checkPanel.add(check2);

        add(checkPanel, BorderLayout.SOUTH);

    }
}
