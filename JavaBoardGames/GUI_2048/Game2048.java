package JavaBoardGames.GUI_2048;

import javax.swing.*;

public class Game2048
{

    public Game2048()
    {

    }

    class createFrame extends JFrame
    {

        public createFrame()
        {
            createPanel cp = new createPanel();

            add(cp);
        }
    }

    class createPanel extends JPanel
    {
        
    }

    public static void main(String[] args)
    {
        createFrame cf = new createFrame();
        
        
    }
}
