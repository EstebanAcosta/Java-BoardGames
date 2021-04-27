package GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class PlayingWithAreaText2
{
    public static void main(String[] args)
    {
        areaFrame2 af2 = new areaFrame2();

        af2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        af2.setVisible(true);

    }
}

class areaFrame2 extends JFrame
{

    private JPanel buttonPanel;

    private JButton insert;

    private JButton skipLines;

    private JTextArea areaText;

    private JScrollPane scrollPanel;

    public areaFrame2()
    {
        setTitle("Text Area Frame 2");

        setBounds(600, 300, 600, 350);

        setLayout(new BorderLayout());

        buttonPanel = new JPanel();

        insert = new JButton("Insert");

        skipLines = new JButton("Skip Lines");

        insert.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {
                areaText.append("In a foreign place whose name I dont want to remember");
            }

        });

        skipLines.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {

                boolean skip = !areaText.getLineWrap();

                areaText.setLineWrap(skip);

                if (skip)
                {
                    skipLines.setText("Don't Skip Lines");
                }

                else
                {
                    skipLines.setText("Skip Lines");
                }

            }

        });

        buttonPanel.add(insert);

        buttonPanel.add(skipLines);

        add(buttonPanel, BorderLayout.SOUTH);

        areaText = new JTextArea(8, 20);

        scrollPanel = new JScrollPane(areaText);

        add(scrollPanel, BorderLayout.CENTER);

    }

}
