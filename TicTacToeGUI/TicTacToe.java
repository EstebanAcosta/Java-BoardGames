package TicTacToeGUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TicTacToe
{
    static Player[] players = new Player[2];

    public static void main(String[] args)
    {
        TicTacFrame tt = new TicTacFrame();

        int min = 1;

        int max = 100;

        for (int i = 0; i < 2; i++)
        {
            players[i] = new Player(i + 1);

            players[i].setName(JOptionPane.showInputDialog("What's your name, Player " + (i + 1)));

            JOptionPane.showMessageDialog(new JFrame(), "Player " + (i + 1) + "'s name is " + players[i].getName());

            if (i == 0)
            {
                String number = JOptionPane.showInputDialog("Pick a number between " + min + " and " + max);

                while (!number.matches("[0-9]+"))
                {
                    JOptionPane.showMessageDialog(new JFrame(), "Please enter a whole number between " + min + " and " + max);

                    number = JOptionPane.showInputDialog("Pick a whole number between " + min + " and " + max);

                }

                if (Integer.parseInt(number) % 2 == 0)
                {
                    JOptionPane.showMessageDialog(new JFrame(), "Player " + (i + 1) + " " + players[i].getName() + " gets to place an X");

                    players[i].setXorO("X");

                }

                else
                {
                    JOptionPane.showMessageDialog(new JFrame(), "Player " + (i + 1) + " " + players[i].getName() + " gets to place an O");

                    players[i].setXorO("O");

                }

            }

        }

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
