package Game2048wGUI;

import javax.swing.*;

import java.awt.*;

public class Game2048 extends JFrame
{

    public void displayGame()
    {
        Toolkit tk = Toolkit.getDefaultToolkit();

        Dimension dimensions = tk.getScreenSize();

        int height = dimensions.height;

        int width = dimensions.width;

        setLocation(width / 4, height / 4);

        setSize(width / 2, height / 2);

        setVisible(true);
        
        setTitle("2048");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String[] args)
    {
        Game2048 myGUI = new Game2048();
        
        myGUI.displayGame();

    }

}
