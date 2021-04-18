package GUI_2048;

import javax.swing.*;

import java.awt.*;
import java.util.Random;

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

        framePanel fp = new framePanel();

        add(fp);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}

class framePanel extends JPanel

{
    private JButton score;
    
    private JPanel gamePanel;

    public framePanel()
    {   
        score = new JButton("Score: 0");
        
        gamePanel = new JPanel();
        
        setLayout(new BorderLayout());
        
        add(score,BorderLayout.NORTH);
        
        gamePanel.setLayout(new GridLayout(4, 4));
       
        Random rand = new Random();

        int whichTile = 0, whichTile2 = 0;

        while (whichTile == whichTile2)
        {
            whichTile = rand.nextInt(17);

            whichTile2 = rand.nextInt(17);
        }
        
        for (int i = 1; i < 17; i++)
        {
            if (i == whichTile || i == whichTile2)
            {
                boolean twoOrFour = rand.nextBoolean();

                if (twoOrFour)
                {
                    JButton two = new JButton("2");

                    // two.setBackground(Color.blue);
                    //
                    // two.setOpaque(true);
                    //
                    //// two.setBorderPainted(false);

                    gamePanel.add(two);
                }

                else
                {
                    JButton four = new JButton("4");

                    // four.setBackground(Color.blue);
                    //
                    // four.setOpaque(true);
                    //
                    // // four.setBorderPainted(false);

                    gamePanel.add(four);
                }

            }

            else
            {
                gamePanel.add(new JButton());
            }

        }
        
        add(gamePanel, BorderLayout.CENTER);
    }
    

    
}
