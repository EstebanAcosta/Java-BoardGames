package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Layouts
{
    public static void main(String[] args)
    {
        layoutFrame lf = new layoutFrame();

        lf.setVisible(true);

        lf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

class layoutFrame extends JFrame
{
    public layoutFrame()
    {
        setTitle("New Tries");

        setBounds(600, 350, 600, 300);

        // FlowLayout fl = new FlowLayout(FlowLayout.CENTER);

        layoutPanel lp = new layoutPanel();

        layoutPanel2 lp2 = new layoutPanel2();

        // lp.setLayout(fl);

        // lp.setLayout(new FlowLayout(FlowLayout.CENTER,75,100));

        // add(lp,BorderLayout.NORTH);
        //
        // add(lp2,BorderLayout.SOUTH);

        add(lp);
    }

}

class layoutPanel extends JPanel
{
    public layoutPanel()
    {
        // setLayout(new FlowLayout(FlowLayout.CENTER,75,100));

        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        add(new JButton("Yellow"));

        add(new JButton("Red"));

    }
}

class layoutPanel2 extends JPanel
{
    public layoutPanel2()
    {
        setLayout(new BorderLayout());

        add(new JButton("Blue"), BorderLayout.WEST);

        add(new JButton("Green"), BorderLayout.EAST);

        add(new JButton("Black"), BorderLayout.CENTER);
    }
}
