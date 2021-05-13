package TicTacToeGUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TicTacToe
{
    public static void main(String[] args)
    {
        TicTacFrame tt = new TicTacFrame();

        tt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tt.setVisible(true);
    }
}

class TicTacFrame extends JFrame
{
    public TicTacFrame()
    {
        setTitle("Tic-Tac-Toe");

        setBounds(500, 200, 500, 500);

        add(new TicTacPanel());
    }
}

class TicTacPanel extends JPanel
{
    public TicTacPanel()
    {
        setLayout(new BorderLayout());

        JPanel gamePanel = new JPanel();

        gamePanel.setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 9; i++)
        {
            gamePanel.add(new JButton());
        }

        add(gamePanel, BorderLayout.CENTER);

    }
}
