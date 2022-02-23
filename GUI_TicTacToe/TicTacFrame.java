package GUI_TicTacToe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;

public class TicTacFrame extends JFrame
{
    public TicTacFrame(Player[] players, int rounds)
    {
        JMenuItem exitButton = new JMenuItem("Exit");

        JButton dontStartNewGame = new JButton("No");

        setTitle("Tic-Tac-Toe");

        setBounds(500, 200, 700, 500);

        add(new TicTacPanel(players, rounds, exitButton, dontStartNewGame));

        exitButton.addActionListener(new closeWindow());

        dontStartNewGame.addActionListener(new closeWindow());
    }

    class closeWindow implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            dispose();
        }

    }
}
