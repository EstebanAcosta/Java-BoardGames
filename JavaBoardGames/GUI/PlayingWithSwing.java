package JavaBoardGames.GUI;

import javax.swing.*;

import java.awt.*;

public class PlayingWithSwing
{

    public static void main(String[] args)
    {
//        displayWindow myWindow = new displayWindow();
//
//        displayPanel myPanel = new displayPanel();
//        
        myWindow2 myW2 = new myWindow2();
        
        myW2.setVisible(true);
        
        myW2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}

class myWindow2 extends JFrame
{
    public myWindow2()
    {
        setSize(500,300);
        
//        setLocation(50,300);
        
        setBounds(500,300,400,250);
        
        //setResizable(false);
        
        setExtendedState(Frame.MAXIMIZED_BOTH);
        
        
        
    }
}

class displayWindow extends JFrame
{

    public displayWindow()
    {
        Toolkit tk = Toolkit.getDefaultToolkit();

        Dimension dimensions = tk.getScreenSize();

        int height = dimensions.height;

        int width = dimensions.width;

        setLocation(width / 4, height / 4);

        setSize(width / 2, height / 2);

        setTitle("2048");

        displayPanel panel = new displayPanel();

        add(panel);

        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}

class displayPanel extends JPanel
{
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        g.drawString("Game    2048", 300, 100);
        
        g.drawRect(50, 50, 200, 200);
        
        g.drawLine(50, 100, 100, 100);
        
        g.drawPolygon(new Polygon());
        
    }


}
