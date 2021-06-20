package TicTacToeGUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TicTacToe
{
    static Player[] players = new Player[2];

    public static Player[] setUpPlayers()
    {
        // loop through the players of this game
        for (int i = 0; i < 2; i++)
        {
            // give each player an id number
            players[i] = new Player(i + 1);

            // ask each player what's their name and set that name for each player
            players[i].setName(JOptionPane.showInputDialog(new JFrame(), "What's your name Player " + (i + 1), "Enter Player Name", JOptionPane.INFORMATION_MESSAGE));

            // display the player's name
            JOptionPane.showMessageDialog(new JFrame(), "Player " + (i + 1) + "'s name is " + players[i].getName(), "Player's Name", JOptionPane.INFORMATION_MESSAGE);

            // if this is the first player
            if (i == 0)
            {
                // Ask the user to enter a number
                String number = JOptionPane.showInputDialog(new JFrame(), "Enter an Integer", "Enter an Integer", JOptionPane.INFORMATION_MESSAGE);

                // if the response isn't an integer
                while (!number.matches("[0-9]+"))
                {

                    // display an error message showing that the player hasn't entered an integer
                    JOptionPane.showMessageDialog(new JFrame(), "The response you have provided isn't an integer. Please enter a whole number",
                    "Not An Integer", JOptionPane.ERROR_MESSAGE);

                    // try to get an input from this player again
                    number = JOptionPane.showInputDialog(new JFrame(), "Enter an Integer", "Please enter a whole number", JOptionPane.ERROR_MESSAGE);

                }

                // if the number that the player provided is an even integer
                if (Integer.parseInt(number) % 2 == 0)
                {

                    // this player gets to use X's for this round
                    players[i].setXorO("X");

                }

                // if the number that the player provided is an odd integer
                else
                {
                    // this player gets to use O's for this round
                    players[i].setXorO("O");

                }

                // Display the letter the second player gets to use in this round
                JOptionPane.showMessageDialog(new JFrame(), "Player " + (i + 1) + " " + players[i].getName() + " gets to place an " + players[i].getXorO(),
                "Player " + players[i].getPlayerId() + " Uses " + players[i].getXorO(), JOptionPane.INFORMATION_MESSAGE);

            }

        }

        // if the first player who got to decide which letter they wanted got an X
        if (players[0].getXorO().equalsIgnoreCase("X"))
        {
            // second player by default gets an O
            players[1].setXorO("O");

        }

        // if the first player who got to decide which letter they wanted got an O
        else
        {
            // second player by default gets an X
            players[1].setXorO("X");

        }

        JOptionPane.showMessageDialog(new JFrame(), "Player " + players[1].getPlayerId() + " " + players[1].getName() + " gets to place an " + players[1].getXorO(),
        "Player " + players[1].getPlayerId() + " Uses " + players[1].getXorO(), JOptionPane.INFORMATION_MESSAGE);

        return players;
    }

    public static int setUpRounds()
    {
        int minRound = 1;

        int maxRound = 5;

        String rounds = JOptionPane.showInputDialog("Please enter a whole number for the number of rounds both players want to play? Minimum is " + minRound + " and Maximum is " + maxRound);

        // if the response isn't an integer
        while (!rounds.matches("[0-9]+") || Integer.parseInt(rounds) < minRound || Integer.parseInt(rounds) > maxRound)
        {

            if (rounds.matches("[0-9]+") && Integer.parseInt(rounds) < minRound)
            {
                // display an error message showing that the player hasn't entered an integer between the min and max range
                JOptionPane.showMessageDialog(new JFrame(), "Please enter a number for the number of rounds that is above or equal to " + minRound,
                "Number Is Below The Min Number", JOptionPane.ERROR_MESSAGE);
            }

            else if (rounds.matches("[0-9]+") && Integer.parseInt(rounds) > maxRound)
            {
                // display an error message showing that the player hasn't entered an integer between the min and max range
                JOptionPane.showMessageDialog(new JFrame(), "Please enter a number for the number of rounds that is below or equal to " + maxRound,
                "Number Is Above The Max Number", JOptionPane.ERROR_MESSAGE);
            }

            else
            {
                // display an error message showing that the player hasn't entered an integer
                JOptionPane.showMessageDialog(new JFrame(), "The response you have provided isn't an integer. Please enter a whole number",
                "Not An Integer", JOptionPane.ERROR_MESSAGE);
            }

            // try to get an input from this player again
            rounds = JOptionPane.showInputDialog(new JFrame(), "Please enter a whole number for the number of rounds. Minimum is " + minRound + " and Maximum is " + maxRound, "Enter an integer", JOptionPane.ERROR_MESSAGE);
        }

        return Integer.parseInt(rounds);
    }

    public static void main(String[] args)
    {

        TicTacFrame tt = new TicTacFrame(setUpPlayers(), setUpRounds());

        tt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tt.setVisible(true);

    }
}

class TicTacFrame extends JFrame
{
    public TicTacFrame(Player[] players, int rounds)
    {
        setTitle("Tic-Tac-Toe");

        setBounds(500, 200, 500, 500);

        add(new TicTacPanel(players, rounds));
    }

}

class TicTacPanel extends JPanel
{

    int whoseTurn = 0;

    int currentRound = 1;

    JButton[][] tiles;

    JButton round, P1score, P2score;

    public TicTacPanel(Player[] players, int rounds)
    {

        // Make the panel layout a border layout
        setLayout(new BorderLayout());

        // create an upper panel
        JPanel upperPanel = new JPanel();

        // make that upper panel a border layout
        upperPanel.setLayout(new BorderLayout());

        // create a panel specifically for the menu bar
        JPanel menuPanel = new JPanel();

        JMenuBar menuBar = new JMenuBar();

        JMenu newGame = new JMenu("New Game");

        newGame.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {

            }

        });

        ////////////////////////////////////////////////////////////////////////////////////////////////////

        JMenu refreshGame = new JMenu("Refresh Game");

        refreshGame.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {

            }

        });

        ////////////////////////////////////////////////////////////////////////////////////////////////////

        JMenu addRemoveRounds = new JMenu("Rounds");

        JMenuItem addRounds = new JMenuItem("Add Rounds");

        addRounds.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {

            }

        });

        JMenuItem reduceRounds = new JMenuItem("Reduce Rounds");

        reduceRounds.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {

            }

        });

        addRemoveRounds.add(addRounds);

        addRemoveRounds.add(reduceRounds);

        ////////////////////////////////////////////////////////////////////////////////////////////////////

        JMenu exit = new JMenu("Exit");

        exit.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {

            }

        });

        ////////////////////////////////////////////////////////////////////////////////////////////////////

        menuBar.add(newGame);

        menuBar.add(refreshGame);

        menuBar.add(addRemoveRounds);

        menuBar.add(exit);

        menuPanel.add(menuBar);

        ////////////////////////////////////////////////////////////////////////////////////////////////////

        // add the menu bar panel to the northern part of the upper panel
        upperPanel.add(menuPanel, BorderLayout.NORTH);

        // create a panel for keeping track of the current score of each player
        JPanel scorePanel = new JPanel();

        // add the score panel to the central part of the upper panel
        upperPanel.add(scorePanel, BorderLayout.CENTER);

        // create three buttons that keep track of the current round
        // and the current score for two players
        round = new JButton("Round " + currentRound);

        P1score = new JButton("Player " + players[0].getPlayerId() + " Score: " + players[0].getCurrentScore() +
        (players[0].getCurrentScore() != 1 ? " points" : " point"));

        P2score = new JButton("Player " + players[1].getPlayerId() + " Score: " + players[1].getCurrentScore() +
        (players[0].getCurrentScore() != 1 ? " points" : " point"));

        // disable the three buttons
        round.setEnabled(false);

        P1score.setEnabled(false);

        P2score.setEnabled(false);

        // add all three buttons to the score panel
        scorePanel.add(round);

        scorePanel.add(P1score);

        scorePanel.add(P2score);

        // create a game panel for the main game
        JPanel gamePanel = new JPanel();

        // the game panel will have a grid layout (3 by 3)
        gamePanel.setLayout(new GridLayout(3, 3));

        // create a 2D array for all nine buttons
        tiles = new JButton[3][3];

        Random rand = new Random();

        // randomly determine who goes first
        whoseTurn = rand.nextInt(2);

        // loop through each row in the button grid
        for (int row = 0; row < 3; row++)
        {
            // loop through each column in the button grid
            for (int col = 0; col < 3; col++)
            {
                // create a new button
                JButton tile = new JButton();

                // add an action listener to each button
                tile.addActionListener(new ActionListener()
                {

                    public void actionPerformed(ActionEvent e)
                    {
                        // if the button is currently empty
                        // (doesn't have any text in it)
                        if (tile.getText().length() == 0)
                        {
                            // set the text in that button to an X or an O
                            tile.setText(players[whoseTurn].getXorO());

                            // change turns
                            whoseTurn = changeTurns(whoseTurn);

                        }

                    }

                });

                // add the button tile to the 2D array of buttons
                tiles[row][col] = tile;

                // add the button to the panel
                gamePanel.add(tile);

            }

        }

        add(gamePanel, BorderLayout.CENTER);

        add(upperPanel, BorderLayout.NORTH);

    }

    /***
     * Determines if either player has the same letter three times in a row
     * @param board
     * @return true if the player has the same letter three times in a row
     */
    public boolean hasWonRound(JButton[][] board)
    {
        int count = 0;

        for (int row = 0; row < board.length; row++)
        {
            for (int col = 0; col < board[row].length; col++)
            {

                // if the value in the first entry of that row is equal to the current entry of that same row
                if (board[row][0].getText().equals(board[row][col].getText()) && !board[row][0].getText().equals(""))
                {
                    // add one more to count
                    count++;
                }

            }

            // if all three numbers in that row are equal return true
            if (count == 3)
            {

                return true;
            }

            // otherwise reset the counter to 0
            else
            {
                count = 0;
            }
        }

        count = 0;

        for (int col = 0; col < board[0].length; col++)
        {
            for (int row = 0; row < board.length; row++)
            {

                // if the value in the first entry of that column is equal to the current entry of that same column
                if (board[0][col].getText().equals(board[row][col].getText()) && !board[0][col].getText().equals(""))
                {
                    // add one more to count
                    count++;
                }

            }

            // if all three numbers in that column are equal return true
            if (count == 3)
            {

                return true;
            }

            // otherwise reset the counter to 0
            else
            {
                count = 0;
            }
        }

        return false;
    }

    /***
     * Determines who gets to put down a letter next
     * @param thisTurn
     * @return 1 for player 2, 0 for player1
     */
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
