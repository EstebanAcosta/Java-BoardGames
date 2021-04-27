package GUI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PlayingWithComboBox
{
    public static void main(String[] args)
    {
        comboFrame cf = new comboFrame();

        cf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cf.setVisible(true);

    }
}

class comboFrame extends JFrame
{

    public comboFrame()
    {
        setTitle("ComboBox Frame");

        setBounds(600, 300, 600, 350);

        add(new comboPanel());

    }

}

class comboPanel extends JPanel
{
    private JLabel text;

    private JComboBox combo;

    public comboPanel()
    {
        setLayout(new BorderLayout());

        text = new JLabel("Je veux changer ta vie en ce moment");

        text.setFont(new Font("Times New Roman", Font.ITALIC, 18));

        JPanel textPanel = new JPanel();

        textPanel.add(text);

        add(textPanel, BorderLayout.CENTER);

        JPanel comboPanel = new JPanel();

        combo = new JComboBox();

        // combo.setEditable(true);

        combo.addItem("Ariel");

        combo.addItem("Times New Roman");

        combo.addItem("SansSerif");

        combo.addItem("MonoSpaced");

        combo.addActionListener(new changeFont());

        comboPanel.add(combo);

        add(comboPanel, BorderLayout.NORTH);
    }

    private class changeFont implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            text.setFont(new Font((String) combo.getSelectedItem(), Font.ITALIC, 25));
        }

    }
}
