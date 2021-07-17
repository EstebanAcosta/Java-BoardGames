package TicTacToeGUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.KeyStroke;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

import com.sun.glass.events.KeyEvent;

public class TicTacPanel extends JPanel
{

    int whoseTurn = 0;

    int currentRound = 1;

    int totalRounds = 0;

    JButton[][] tiles;

    JButton round, P1score, P2score;

    JFrame thisFrame = (JFrame) SwingUtilities.getWindowAncestor(this);

    String winnerXO = "";

    public TicTacPanel(Player[] players, int rounds, JMenuItem exiting, JButton dontStartNewGame)
    {

        totalRounds = rounds;

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

        JMenuItem getNewGame = new JMenuItem("New Game");

        getNewGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.SHIFT_DOWN_MASK));

        getNewGame.addActionListener(new startNewGame());

        newGame.add(getNewGame);

        ////////////////////////////////////////////////////////////////////////////////////////////////////

        JMenu restartRound = new JMenu("Restart Round");

        JMenuItem restartingRound = new JMenuItem("Restart Round");

        restartingRound.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.SHIFT_DOWN_MASK));

        restartingRound.addActionListener(new restartingIt());

        restartRound.add(restartingRound);

        ////////////////////////////////////////////////////////////////////////////////////////////////////

        JMenu addRemoveRounds = new JMenu("Rounds");

        JMenuItem addRounds = new JMenuItem("Add Rounds");

        JMenuItem reduceRounds = new JMenuItem("Reduce Rounds");

        addRounds.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.SHIFT_DOWN_MASK));

        reduceRounds.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.SHIFT_DOWN_MASK));

        reduceRounds.addActionListener(new reduceRounds());

        addRounds.addActionListener(new addRounds());

        addRemoveRounds.add(addRounds);

        addRemoveRounds.add(reduceRounds);

        ////////////////////////////////////////////////////////////////////////////////////////////////////

        JMenu exit = new JMenu("Exit");

        exiting.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.SHIFT_DOWN_MASK));

        exit.add(exiting);

        ////////////////////////////////////////////////////////////////////////////////////////////////////

        menuBar.add(newGame);

        menuBar.add(restartRound);

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
        round = new JButton("Round " + currentRound + " of " + totalRounds);

        P1score = new JButton("Player " + players[0].getPlayerId() + ": " + players[0].getCurrentScore() +
        (players[0].getCurrentScore() != 1 ? " points" : " point"));

        P2score = new JButton("Player " + players[1].getPlayerId() + ": " + players[1].getCurrentScore() +
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

        GridLayout layout = new GridLayout(3, 3);

        // the game panel will have a grid layout (3 by 3)
        gamePanel.setLayout(layout);

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

                        if (hasWonRound(tiles) || allOccupied(tiles))
                        {
                            if (hasWonRound(tiles))
                            {
                                if (players[0].getXorO() == winnerXO)
                                {
                                    JOptionPane.showMessageDialog(new JFrame(), "Player 1 " + players[0].getName() + " has won round " + currentRound, "Player 1 Wins " + currentRound, JOptionPane.INFORMATION_MESSAGE);

                                    players[0].setCurrentScore(players[0].getCurrentScore() + 1);

                                    P1score.setText("Player " + players[0].getPlayerId() + ": " + players[0].getCurrentScore() +
                                    (players[0].getCurrentScore() != 1 ? " points" : " point"));
                                }

                                else if (players[1].getXorO() == winnerXO)
                                {
                                    JOptionPane.showMessageDialog(new JFrame(), "Player 2 " + players[1].getName() + " has won round " + currentRound, "Player 2 Wins " + currentRound, JOptionPane.INFORMATION_MESSAGE);

                                    players[1].setCurrentScore(players[1].getCurrentScore() + 1);

                                    P2score.setText("Player " + players[1].getPlayerId() + ": " + players[1].getCurrentScore() +
                                    (players[1].getCurrentScore() != 1 ? " points" : " point"));

                                }
                            }

                            else
                            {
                                JOptionPane.showMessageDialog(new JFrame(), "Neither player has won this round", "Tie Game", JOptionPane.INFORMATION_MESSAGE);

                            }

                            currentRound++;

                            // if there are more rounds to play
                            if (currentRound <= totalRounds)
                            {
                                for (int row = 0; row < tiles.length; row++)
                                {
                                    for (int col = 0; col < tiles[row].length; col++)
                                    {

                                        // reset the grid
                                        tiles[row][col].setText("");
                                    }
                                }

                                round.setText("Round " + currentRound + " of " + totalRounds);

                            }

                            // if this is the final round
                            else
                            {

                                if (players[0].getCurrentScore() > players[1].getCurrentScore())
                                {
                                    JOptionPane.showMessageDialog(new JFrame(), "Player 1 " + players[0].getName() + " has won Tic-Tac-Toe with " + players[0].getCurrentScore() +
                                    (players[0].getCurrentScore() != 1 ? " points" : " point"), "Player 1 Wins The Game", JOptionPane.INFORMATION_MESSAGE);

                                }

                                else if (players[1].getCurrentScore() > players[0].getCurrentScore())
                                {
                                    JOptionPane.showMessageDialog(new JFrame(), "Player 2 " + players[1].getName() + " has won Tic-Tac-Toe with " +
                                    players[1].getCurrentScore() + (players[1].getCurrentScore() != 1 ? " points" : " point"), "Player 2 Wins The Game", JOptionPane.INFORMATION_MESSAGE);

                                }

                                else
                                {
                                    JOptionPane.showMessageDialog(new JFrame(), "Player 2 " + players[1].getName() + " and Player 1 " + players[0].getName() +
                                    " scored the same number of points. So there will be no winner ", "No Winner", JOptionPane.INFORMATION_MESSAGE);

                                }

                                JFrame continueGame = new JFrame();

                                continueGame.setTitle("Continue Game");

                                JLabel continueQuestion = new JLabel("Would you like to start another game?");

                                continueQuestion.setHorizontalAlignment(JLabel.CENTER);

                                continueGame.setLayout(new BorderLayout());

                                JPanel questionArea = new JPanel();

                                questionArea.add(continueQuestion);

                                continueGame.add(questionArea, BorderLayout.NORTH);

                                JButton yes = new JButton("Yes");

                                yes.addActionListener(new ActionListener()
                                {
                                    public void actionPerformed(ActionEvent e)
                                    {

                                        continueGame.dispose();

                                        TicTacToe ttt = new TicTacToe();

                                        ttt.players = ttt.setUpPlayers();

                                        ttt.setUpXO();

                                    }
                                });

                                JPanel submitArea = new JPanel();

                                submitArea.add(yes);

                                submitArea.add(dontStartNewGame);

                                continueGame.add(submitArea, BorderLayout.SOUTH);

                                continueGame.setBounds(400, 400, 400, 100);

                                continueGame.setVisible(true);

                                continueGame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                            }

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

    public boolean allOccupied(JButton[][] board)
    {
        for (int row = 0; row < board.length; row++)
        {
            for (int col = 0; col < board[row].length; col++)
            {
                if (board[row][col].getText().equalsIgnoreCase(""))
                {
                    return false;
                }
            }
        }

        return true;
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
                winnerXO = board[row][0].getText();

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
                winnerXO = board[0][col].getText();

                return true;
            }

            // otherwise reset the counter to 0
            else
            {
                count = 0;
            }
        }

        // if the entries in the diagonals are all equal
        if (!board[0][0].getText().equals("") && board[0][0].getText().equals(board[1][1].getText()) && board[1][1].getText().equals(board[2][2].getText()))
        {

            winnerXO = board[0][0].getText();

            return true;

        }

        else if (!board[0][2].getText().equals("") && board[0][2].getText().equals(board[1][1].getText()) && board[1][1].getText().equals(board[2][0].getText()))
        {
            winnerXO = board[0][2].getText();

            return true;

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

    private class restartingIt implements ActionListener
    {
        int originalX = 400;

        int originalY = 400;

        public void actionPerformed(ActionEvent e)
        {

            JFrame restart = new JFrame();

            JButton yes = new JButton("Yes");

            JButton no = new JButton("No");

            yes.addActionListener(new ActionListener()
            {

                public void actionPerformed(ActionEvent e)
                {
                    for (int row = 0; row < tiles.length; row++)
                    {
                        for (int col = 0; col < tiles[row].length; col++)
                        {

                            tiles[row][col].setText("");
                        }
                    }

                    restart.dispose();
                }

            });

            no.addActionListener(new ActionListener()
            {

                public void actionPerformed(ActionEvent e)
                {
                    restart.dispose();
                }
            });

            JLabel question = new JLabel("Are you sure you want to restart the game?");

            question.setHorizontalAlignment(JLabel.CENTER);

            restart.setBounds(originalX, originalX, 400, 100);

            restart.setVisible(true);

            restart.setTitle("Restart Round?");

            JPanel restartPanel = new JPanel();

            restart.add(restartPanel);

            restartPanel.setLayout(new BorderLayout());

            JPanel submitPanel = new JPanel();

            submitPanel.add(yes);

            submitPanel.add(no);

            restartPanel.add(question, BorderLayout.CENTER);

            restartPanel.add(submitPanel, BorderLayout.SOUTH);

            restart.add(restartPanel);

            originalX += 75;

            originalY += 75;

        }

    }

    private class startNewGame implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {

            TicTacToe ttt = new TicTacToe();

            ttt.players = ttt.setUpPlayers();

            ttt.setUpXO();

        }

    }

    private class reduceRounds implements ActionListener
    {

        int originalX = 400;

        int originalY = 400;

        int minRound = 1;

        public void actionPerformed(ActionEvent e)
        {

            if (totalRounds == minRound || totalRounds == currentRound)
            {
                if (totalRounds == minRound)
                {
                    JOptionPane.showMessageDialog(new JFrame(), "Can't reduce the number of rounds of the game since 1 is the mininum ",
                    "Can't Go Below The Min Number Of Rounds", JOptionPane.ERROR_MESSAGE);
                }

                else
                {
                    JOptionPane.showMessageDialog(new JFrame(), "Can't go below the current round ",
                    "Can't Go Below The Current Number Of Rounds", JOptionPane.ERROR_MESSAGE);
                }

            }

            else
            {

                JFrame reduceRounds = new JFrame();

                reduceRounds.setTitle("Reduce Rounds ");

                reduceRounds.setBounds(originalX, originalX, 300, 300);

                reduceRounds.setVisible(true);

                JPanel rrPanel = new JPanel();

                reduceRounds.add(rrPanel);

                rrPanel.setLayout(new BorderLayout());

                JSpinner reduce = new JSpinner(new SpinnerNumberModel(0, 0, totalRounds - currentRound, 1));

                reduce.setPreferredSize(new Dimension(200, 20));

                rrPanel.add(reduce, BorderLayout.NORTH);

                JButton submit = new JButton("Submit");

                submit.addActionListener(new ActionListener()
                {

                    public void actionPerformed(ActionEvent e)
                    {

                        JOptionPane.showMessageDialog(new JFrame(), "The number of rounds has been changed from " + totalRounds + " rounds to " +
                        Math.abs(totalRounds - Integer.parseInt(reduce.getValue().toString())) +
                        (Math.abs(totalRounds - Integer.parseInt(reduce.getValue().toString())) != 1 ? " rounds" : " round"),
                        "New Total Rounds", JOptionPane.INFORMATION_MESSAGE);

                        totalRounds = Math.abs(totalRounds - Integer.parseInt(reduce.getValue().toString()));

                        round.setText("Round " + currentRound + " of " + totalRounds);

                        reduceRounds.dispose();

                    }

                });

                rrPanel.add(submit, BorderLayout.SOUTH);

                originalX += 75;

                originalY += 75;
            }

        }
    }

    private class addRounds implements ActionListener
    {

        int originalX = 300;

        int originalY = 300;

        int maxRound = 5;

        public void actionPerformed(ActionEvent e)
        {

            if (totalRounds == maxRound)
            {
                JOptionPane.showMessageDialog(new JFrame(), "Can't add more to the number of rounds of the game since 5 is the maximum ",
                "Can't Go Above The Max Number Of Rounds", JOptionPane.ERROR_MESSAGE);
            }

            else
            {
                JFrame addRounds = new JFrame();

                addRounds.setTitle("Add Rounds ");

                addRounds.setBounds(originalX, originalX, 300, 300);

                addRounds.setVisible(true);

                JPanel arPanel = new JPanel();

                addRounds.add(arPanel);

                arPanel.setLayout(new BorderLayout());

                JSpinner add = new JSpinner(new SpinnerNumberModel(0, 0, maxRound - totalRounds, 1));

                add.setPreferredSize(new Dimension(200, 20));

                arPanel.add(add, BorderLayout.NORTH);

                JButton submit = new JButton("Submit");

                submit.addActionListener(new ActionListener()
                {

                    public void actionPerformed(ActionEvent e)
                    {

                        JOptionPane.showMessageDialog(new JFrame(), "The number of rounds has been changed from " + totalRounds +
                        (totalRounds != 1 ? " rounds " : " round ") + "to " + (totalRounds + Integer.parseInt(add.getValue().toString())) + " rounds",
                        "New Total Rounds", JOptionPane.INFORMATION_MESSAGE);

                        totalRounds += Integer.parseInt(add.getValue().toString());

                        round.setText("Round " + currentRound + " of " + totalRounds);

                        addRounds.dispose();

                    }

                });
                arPanel.add(submit, BorderLayout.SOUTH);

                originalX += 75;

                originalY += 75;
            }

        }

    }

}
