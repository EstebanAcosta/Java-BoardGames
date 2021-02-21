package GUI_2048;

import javax.swing.*;

import java.awt.*;
import java.awt.geom.*;

public class Game2048
{

    public static void main(String[] args)
    {

        createFrame cf = new createFrame();

    }
}

class createFrame extends JFrame
{

    public createFrame()
    {

        setVisible(true);

        Toolkit screen = Toolkit.getDefaultToolkit();

        Dimension screenSize = screen.getScreenSize();

        int height = screenSize.height;

        int width = screenSize.width;

        setTitle("2048");

        setSize(width / 2, height / 2);

        setLocation(width / 4, height / 4);

        createPanel cp = new createPanel();

        add(cp);
        
        cp.setBackground(Color.yellow);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}

class createPanel extends JPanel
{
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        int height = 500;

        int width = 500;

        Rectangle2D rectangle = new Rectangle2D.Double(height / 4, width / 4, width / 2, height / 2);

        g2.setPaint(Color.BLUE);

//        g2.fill(rectangle);

//        g2.draw(rectangle);

    }
}
