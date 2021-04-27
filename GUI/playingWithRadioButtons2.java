package GUI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class playingWithRadioButtons2
{
    public static void main(String[] args)
    {
        radioFrame2 rf = new radioFrame2();

        rf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        rf.setVisible(true);

    }
}

class radioFrame2 extends JFrame
{

    public radioFrame2()
    {
        setTitle("Radio Buttons Frame");

        setBounds(600, 300, 600, 350);

        add(new radioPanel2());

    }

}

class radioPanel2 extends JPanel
{
    private JLabel text;

    private ButtonGroup myGroup;

    private JPanel radioPanel;

    // private JRadioButton button1, button2, button3, button4;

    public radioPanel2()
    {

        text = new JLabel("je crois que nous ne pouvons pas nous voir");

        text.setFont(new Font("Serif", Font.PLAIN, 12));

        setLayout(new BorderLayout());

        JPanel textPanel = new JPanel();
        
        myGroup = new ButtonGroup();

        radioPanel = new JPanel();


        textPanel.add(text);

        add(textPanel, BorderLayout.CENTER);

        placeButtons("Small", true, 10);

        placeButtons("Median", false, 25);

        placeButtons("Big", false, 35);

        placeButtons("Extra Large", false, 50);

        add(radioPanel, BorderLayout.SOUTH);

        // JPanel radioPanel = new JPanel();

        // button1 = new JRadioButton("Small", false);
        //
        // button2 = new JRadioButton("Median", true);
        //
        // button3 = new JRadioButton("Big", false);
        //
        // button4 = new JRadioButton("Extra Large", false);

        // button1.addActionListener(new enhanceSize());
        //
        // button2.addActionListener(new enhanceSize());
        //
        // button3.addActionListener(new enhanceSize());
        //
        // button4.addActionListener(new enhanceSize());
        //
        // ButtonGroup myGroup = new ButtonGroup();
        //
        // myGroup.add(button1);
        //
        // myGroup.add(button2);
        //
        // myGroup.add(button3);
        //
        // myGroup.add(button4);
        //
        // radioPanel.add(button1);
        //
        // radioPanel.add(button2);
        //
        // radioPanel.add(button3);
        //
        // radioPanel.add(button4);
        //
        // add(radioPanel, BorderLayout.SOUTH);

    }

    public void placeButtons(String name, boolean selected, final int size)
    {
        JRadioButton button = new JRadioButton(name, selected);

        myGroup.add(button);

        radioPanel.add(button);

        ActionListener sizeChange = new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {
                text.setFont(new Font("Serif", Font.PLAIN, size));
            }

        };

        button.addActionListener(sizeChange);

    }

    // private class enhanceSize implements ActionListener
    // {
    //
    // public void actionPerformed(ActionEvent e)
    // {
    // if(e.getSource() == button1)
    // {
    // text.setFont(new Font("Serif",Font.ITALIC,10));
    // }
    //
    // else if(e.getSource() == button2)
    // {
    // text.setFont(new Font("Serif",Font.ITALIC,20));
    // }
    //
    // else if(e.getSource() == button3)
    // {
    // text.setFont(new Font("Serif",Font.ITALIC,30));
    // }
    //
    // else
    // {
    // text.setFont(new Font("Serif",Font.ITALIC,40));
    // }
    // }
    //
    // }
}