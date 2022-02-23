package ChessGUI;

import javax.swing.JFrame;

public class Chess
{
    public static void main(String[] args)
    {
        chessFrame cFrame = new chessFrame();

    }

}

class chessFrame extends JFrame
{
    public chessFrame()
    {
        chessPanel cPanel = new chessPanel();

        setTitle("Chess");

        setBounds(300, 100, 1000, 600);

        add(cPanel);
        
        setVisible(true);
    }

}
