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

        // loop through the players of this game
        for (int i = 0; i < 2; i++)
        {
            // give each player an id number
            players[i] = new Player(i + 1);

            // ask each player what's their name and set that name for each player
            players[i].setName(JOptionPane.showInputDialog("What's your name, Player " + (i + 1)));

            // display the player's name
            JOptionPane.showMessageDialog(new JFrame(), "Player " + (i + 1) + "'s name is " + players[i].getName());

            // if this is the first player
            if (i == 0)
            {
                // Ask the user to enter a number
                String number = JOptionPane.showInputDialog("Enter an integer");

                // if the response isn't an integer
                while (!number.matches("[0-9]+"))
                {

                    //display an error message showing that the player hasn't entered an integer
                    JOptionPane.showMessageDialog(new JFrame(), "The response you have provided isn't an integer. Please enter a whole number");

                    //try to get an input from this player again
                    number = JOptionPane.showInputDialog("Please enter a whole number");

                }

                //if the number that the player provided is an even integer
                if (Integer.parseInt(number) % 2 == 0)
                {

                    //this player gets to use X's for this round
                    players[i].setXorO("X");

                }

                //if the number that the player provided is an odd integer
                else
                {
                    //this player gets to use O's for this round
                    players[i].setXorO("O");

                }

                //Display the letter the second player gets to use in this round
                JOptionPane.showMessageDialog(new JFrame(), "Player " + (i + 1) + " " + players[i].getName() + " gets to place an " + players[i].getXorO());

            }

        }

        //if the first player who got to decide which letter they wanted got an X
        if (players[0].getXorO().equalsIgnoreCase("X"))
        {
            //second player by default gets an O
            players[1].setXorO("O");

        }

        //if the first player who got to decide which letter they wanted got an O
        else
        {
            //second player by default gets an X
            players[1].setXorO("X");

        }

        JOptionPane.showMessageDialog(new JFrame(), "Player " + players[1].getPlayerId() + " " + players[1].getName() + " gets to place an " + players[1].getXorO());

        int minRound = 1;
        
        int maxRound = 5;
        
        String rounds = JOptionPane.showInputDialog("Please enter a whole number for the number of rounds both players want to play? Minimum is " + minRound + " and Maximum is " + maxRound );

        
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
        //Make the panel layout a border layout
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
