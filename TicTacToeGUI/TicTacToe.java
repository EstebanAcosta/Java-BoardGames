package TicTacToeGUI;

import javax.swing.JFrame;

import javax.swing.JOptionPane;

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

        tt.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        tt.setVisible(true);

    }
}
