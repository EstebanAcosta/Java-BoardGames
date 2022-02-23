package GUI_TicTacToe;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class TicTacToe
{
    static Player[] players = new Player[2];

    static String totalRounds = "";

    static String randNum = "";

    public static Player[] setUpPlayers()
    {

        int minLen = 2;

        int maxLen = 15;

        // loop through the players of this game
        for (int i = 0; i < 2; i++)

        {
            // give each player an id number
            players[i] = new Player(i + 1);

            // ask each player what's their name and set that name for each player

            String name = JOptionPane.showInputDialog(new JFrame(), "What's your name Player " + (i + 1), "Enter Player " + (i+1)+ " Name", JOptionPane.INFORMATION_MESSAGE);

            // if the name is a number or contains a number or isn't within the character length
            while (name.matches("[0-9]+") || name.length() < minLen || name.length() > maxLen)
            {

                if (name.matches("[0-9]+"))
                {
                    // display an error message showing that the player has entered a number instead of a name
                    JOptionPane.showMessageDialog(new JFrame(), "The name you have provided isn't a name but a number. Please enter a real name",
                    "Provided A Number Instead Of A Name", JOptionPane.ERROR_MESSAGE);

                }

                else if (name.length() < minLen)
                {
                    // display an error message showing that the playe's name is too short
                    JOptionPane.showMessageDialog(new JFrame(), "The name you have provided needs to be at least " + minLen + " characters long",
                    "Name Is Too Short", JOptionPane.ERROR_MESSAGE);

                }

                else if (name.length() > maxLen)
                {
                    // display an error message showing that the player's name is too long
                    JOptionPane.showMessageDialog(new JFrame(), "The name you have provided needs to be less than or equal to " + maxLen + " characters long",
                    "Name Is Too Long", JOptionPane.ERROR_MESSAGE);

                }

                name = JOptionPane.showInputDialog(new JFrame(), "What's your name Player " + (i + 1), "Enter Player Name", JOptionPane.INFORMATION_MESSAGE);

            }

            players[i].setName(name);

            // display the player's name
            JOptionPane.showMessageDialog(new JFrame(), "Player " + (i + 1) + "'s name is " + players[i].getName(), "Player's " + (i+1)+ " Name", JOptionPane.INFORMATION_MESSAGE);

        }

        return players;
    }

    public static void setUpXO()
    {
        JFrame determineXO = new JFrame();

        determineXO.setTitle("Determine Who Gets X or O");

        determineXO.setBounds(400, 400, 400, 150);

        JSpinner randNumSpinner = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));

        JPanel determineXOPanel = new JPanel();

        JPanel spinnerPanel = new JPanel();

        JLabel chooseRandQuestion = new JLabel("Choose a random number");

        chooseRandQuestion.setHorizontalAlignment(JLabel.CENTER);

        JButton submit = new JButton("Submit");
        
        determineXOPanel.setLayout(new BorderLayout());

        submit.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {

                randNum = randNumSpinner.getValue().toString();

                // if the number that the player provided is an even integer
                if (Integer.parseInt(randNum) % 2 == 0)
                {

                    // this player gets to use X's for this round
                    players[0].setXorO("X");

                }

                // if the number that the player provided is an odd integer
                else
                {
                    // this player gets to use O's for this round
                    players[0].setXorO("O");

                }

                // Display the letter the second player gets to use in this round
                JOptionPane.showMessageDialog(new JFrame(), "Player " + (0 + 1) + " " + players[0].getName() + " gets to place an " + players[0].getXorO(),
                "Player " + players[0].getPlayerId() + " Uses " + players[0].getXorO(), JOptionPane.INFORMATION_MESSAGE);

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

                determineXO.dispose();
                
                settingUpRounds();
            }

        });

        spinnerPanel.add(randNumSpinner);

        determineXOPanel.add(spinnerPanel, BorderLayout.CENTER);

        determineXOPanel.add(chooseRandQuestion, BorderLayout.NORTH);

        determineXOPanel.add(submit, BorderLayout.SOUTH);

        determineXO.add(determineXOPanel);

        determineXO.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        determineXO.setVisible(true);

    }

    public static void settingUpRounds()
    {

        JFrame setUpRounds = new JFrame();

        setUpRounds.setTitle("Set Up Rounds");

        setUpRounds.setBounds(400, 400, 400, 150);

        int maxRound = 5;

        JPanel setUpRoundsPanel = new JPanel();

        JPanel spinnerPanel = new JPanel();

        JLabel roundQuestion = new JLabel("Please choose how many rounds both players want to play");

        roundQuestion.setHorizontalAlignment(JLabel.CENTER);

        JButton submit = new JButton("Submit");

        setUpRoundsPanel.setLayout(new BorderLayout());

        JSpinner roundSpinner = new JSpinner(new SpinnerNumberModel(1, 1, maxRound, 1));

        submit.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {

                totalRounds = roundSpinner.getValue().toString();

                JOptionPane.showMessageDialog(new JFrame(), "The number of rounds has been set to " + totalRounds + (Integer.parseInt(totalRounds) != 1 ? " rounds" : " round"),
                "New Total Rounds", JOptionPane.INFORMATION_MESSAGE);

                setUpRounds.dispose();
                

                TicTacFrame tt = new TicTacFrame(players, Integer.parseInt(totalRounds));

                tt.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                tt.setVisible(true);

            }

        });

        spinnerPanel.add(roundSpinner);

        setUpRoundsPanel.add(spinnerPanel, BorderLayout.CENTER);

        setUpRoundsPanel.add(roundQuestion, BorderLayout.NORTH);

        setUpRoundsPanel.add(submit, BorderLayout.SOUTH);

        setUpRounds.add(setUpRoundsPanel);

        setUpRounds.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setUpRounds.setVisible(true);

    }

    public static void main(String[] args)
    {

        players = setUpPlayers();
        
        setUpXO();

    }
}
