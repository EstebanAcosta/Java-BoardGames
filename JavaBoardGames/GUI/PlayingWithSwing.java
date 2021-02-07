package JavaBoardGames.GUI;

import javax.swing.*;

import java.awt.*;
import java.awt.geom.*;

public class PlayingWithSwing
{

    public static void main(String[] args)
    {

        //
        // myWindow2 myW2 = new myWindow2();

        // myW2.setVisible(true);
        //
        // myW2.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        // centeredFrame cf = new centeredFrame();
        //
        // cf.setVisible(true);
        //
        // cf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // windowWithText wt = new windowWithText();
        //
        // wt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        windowWithDrawings wd = new windowWithDrawings();

        wd.setVisible(true);

        wd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}

class myWindow2 extends JFrame
{
    public myWindow2()
    {
        setSize(500, 300);

        // setLocation(50,300);

        setBounds(500, 300, 400, 250);

        // setResizable(false);

        setExtendedState(Frame.MAXIMIZED_BOTH);

    }
}

class centeredFrame extends JFrame
{

    public centeredFrame()
    {
        Toolkit screen = Toolkit.getDefaultToolkit();

        Dimension screensize = screen.getScreenSize();

        int height = screensize.height;

        int width = screensize.width;

        setSize(width / 2, height / 2);

        setLocation(width / 4, height / 4);

        setTitle("Centered Frame");

        Image myIcon = screen.getImage("/Users/estebanacosta/Desktop/Snapchat-1632097606.jpeg");

        setIconImage(myIcon);

    }

}

class panelWithFigures extends JPanel
{
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

//        g.drawRect(50, 50, 200, 200);
//        
//        g.drawLine(100, 100, 300, 200);
//        
//        g.drawArc(50, 100,100,200, 120, 150);
        
        Graphics2D g2 = (Graphics2D) g;
        
        Rectangle2D rectangle = new Rectangle2D.Double(100,100,200,150);
        
        g2.draw(rectangle);
        
        Ellipse2D ellipse = new Ellipse2D.Double();
        
        ellipse.setFrame(rectangle);
        
        g2.draw(ellipse);
        
        g2.draw(new Line2D.Double(100,100,300,250));
        
        double centerX = rectangle.getCenterX();
        
        double centerY = rectangle.getCenterY();
        
        double radius = 150;
        
        Ellipse2D circle = new Ellipse2D.Double();
        
        circle.setFrameFromCenter(centerX, centerY, centerX + radius, centerY + radius);
             
        g2.draw(circle);
    }
}

class windowWithDrawings extends JFrame
{

    public windowWithDrawings()
    {
        setTitle("drawings");

        setSize(400, 400);

        panelWithFigures pf = new panelWithFigures();
        
        add(pf);
    }
}

class panel extends JPanel
{

    public void paintComponent(Graphics g)
    {

        super.paintComponent(g);

        g.drawString("Swing", 100, 100);
    }
}

class windowWithText extends JFrame
{
    public windowWithText()
    {
        setVisible(true);

        setSize(600, 450);

        setLocation(400, 200);

        setTitle("first text");

        panel mypanel = new panel();

        add(mypanel);
    }
}
