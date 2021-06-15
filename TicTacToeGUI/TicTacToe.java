package TicTacToeGUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
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

    int whoseTurn = 0;

    JButton[] tiles;

    public TicTacPanel()
    {
        setLayout(new BorderLayout());

        JButton score = new JButton();

        score.setEnabled(false);

        JPanel scorePanel = new JPanel();

        scorePanel.add(score);

        JPanel gamePanel = new JPanel();

        gamePanel.setLayout(new GridLayout(3, 3));

        tiles = new JButton[9];

        for (int i = 0; i < 9; i++)
        {
            JButton tile = new JButton();

            tile.addActionListener(new ActionListener()
            {

                public void actionPerformed(ActionEvent e)
                {
                    if (tile.getText().length() == 0)
                    {
                        if (whoseTurn == 0)
                        {
                            tile.setText("X");

                        }

                        else
                        {
                            tile.setText("O");

                        }

                        whoseTurn = changeTurns(whoseTurn);

                    }

                }

            });

            gamePanel.add(tile);
        }

        add(gamePanel, BorderLayout.CENTER);

        add(scorePanel, BorderLayout.NORTH);

    }

    public int changeTurns(int thisTurn)
    {
        int whoseTurn;

        if (thisTurn == 0)
        {
            whoseTurn = 1;
        }

        else
        {
            whoseTurn = 0;
        }

        return whoseTurn;
    }

}
