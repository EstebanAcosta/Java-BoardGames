package TicTacToeGUI;

import javax.swing.JFrame;

public class TicTacFrame extends JFrame
{
    int count = 0;

    int originalFrameX = 500;

    int originalFrameY = 200;

    public TicTacFrame(Player[] players, int rounds)
    {

        setTitle("Tic-Tac-Toe");

        setBounds(originalFrameX, originalFrameY, 700, 500);

        add(new TicTacPanel(players, rounds));
    }
}
