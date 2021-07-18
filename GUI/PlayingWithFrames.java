package GUI;

import javax.swing.*;

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.*;

public class PlayingWithFrames
{

    public static void main(String[] args)
    {

        
         myWindow2 myW2 = new myWindow2();

//         myW2.setVisible(true);
//        
//         myW2.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

         centeredFrame cf = new centeredFrame();
        
         cf.setVisible(true);
        
         cf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//         windowWithText wt = new windowWithText();
//        
//         wt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

         windowWithDrawings wd = new windowWithDrawings();
        
         wd.setVisible(true);
        //
        // wd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // windowWColor wc = new windowWColor();

        // windowWithFonts wf = new windowWithFonts();

        windowWithImages wi = new windowWithImages();
    }

}

class windowWithImages extends JFrame
{
    public windowWithImages()
    {
        setTitle("Images");

        setBounds(750, 300, 300, 200);

        imagePanel ip = new imagePanel();

        add(ip);

        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

class imagePanel extends JPanel
{
    public imagePanel()
    {
        try
        {
            image = ImageIO.read(new File("/Users/estebanacosta/Desktop/Photos/small-music-note-icon-14.png"));
        }

        catch (IOException e)
        {
            System.out.println("Image is missing");
        }

    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        // File myImage = new File("/Users/estebanacosta/Desktop/Photos/FB_IMG_1568311517068.jpg");


        int width = image.getWidth(this);
        
        int height = image.getHeight(this);
        
        g.drawImage(image, 0, 0, null);

        for (int i = 0; i < 200; i++)
        {
            for (int j = 0; j < 200; j++)
            {
                g.copyArea(0, 0, width, height, width*i, height*j);

            }
        }
        // g.copyArea(0, 0, 200, 200, 150, 67);
    }

    private BufferedImage image;

}

/***
 * Playing with fonts
 * @author estebanacosta
 */
class windowWithFonts extends JFrame
{
    public windowWithFonts()
    {

        setTitle("playing with fonts");

        setSize(400, 400);

        fontPanel fp = new fontPanel();

        add(fp);

        fp.setForeground(Color.red);

        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}

class fontPanel extends JPanel
{
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        Font f = new Font("Times New Roman", Font.BOLD, 24);

        g2.setFont(f);

        g2.drawString("John", 100, 100);

        // g2.setColor(Color.RED);

        g2.drawString("Sin", 200, 100);

        g2.drawString("You", 300, 100);

        g2.setFont(new Font("Arial", Font.ITALIC, 13));

        // g2.setColor(new Color(123,234,89));

        g2.drawString("curso de py", 100, 300);
    }
}

/***
 * Playing with color
 * @author estebanacosta
 */
class windowWColor extends JFrame
{
    public windowWColor()
    {

        setTitle("Window with color");

        setSize(400, 400);

        colorPanel pc = new colorPanel();

        add(pc);

        pc.setBackground(SystemColor.window);

        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}

class colorPanel extends JPanel
{
    public void paintComponent(Graphics g)
    {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        Rectangle2D rectangle = new Rectangle2D.Double(100, 100, 200, 150);

        g2.setPaint(new Color(109, 162, 58).brighter());

        g2.setPaint(new Color(109, 162, 58).brighter().brighter());

        g2.setPaint(new Color(109, 162, 58).darker().darker());

        g2.setPaint(new Color(109, 162, 58).darker());

        Color myColor = new Color(123, 122, 89);

        myColor = myColor.brighter();

        g2.draw(rectangle);

        g2.fill(rectangle);

        Ellipse2D ellipse = new Ellipse2D.Double();

        ellipse.setFrame(rectangle);

        g2.setPaint(myColor);

        g2.fill(ellipse);

        g2.draw(ellipse);

        // g2.draw(new Line2D.Double(100, 100, 300, 250));

        // double centerX = rectangle.getCenterX();
        //
        // double centerY = rectangle.getCenterY();
        //
        // double radius = 150;
        //
        // Ellipse2D circle = new Ellipse2D.Double();
        //
        // circle.setFrameFromCenter(centerX, centerY, centerX + radius, centerY + radius);
        //
        // g2.draw(circle);

    }
}

/***
 * Creating frames
 * @author estebanacosta
 */
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

/***
 * Playing with shapes
 * @author estebanacosta
 */
class panelWithFigures extends JPanel
{
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        // g.drawRect(50, 50, 200, 200);
        //
        // g.drawLine(100, 100, 300, 200);
        //
        // g.drawArc(50, 100,100,200, 120, 150);

        Graphics2D g2 = (Graphics2D) g;

        Rectangle2D rectangle = new Rectangle2D.Double(100, 100, 200, 150);

        g2.draw(rectangle);

        /***
         * 
         */
        Ellipse2D ellipse = new Ellipse2D.Double();

        ellipse.setFrame(rectangle);

        g2.draw(ellipse);

        /**
         * 
         */
        g2.draw(new Line2D.Double(100, 100, 300, 250));

        /***
         * 
         */
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

/***
 * Playing with text
 * @author estebanacosta
 */
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
