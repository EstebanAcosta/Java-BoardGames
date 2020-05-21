package TicTacToe;
/***
 * 
 * @author estebanacosta
 *
 */
import java.util.Random;
import java.util.Scanner;

public class TicTacToe
{

    Player[] players = new Player[2];

    public void playersSetup()
    {

        // loop through the players in the game
        for (int i = 0; i < players.length; i++)
        {
            System.out.println("What is the name of player " + (i + 1) + " ?");

            Scanner kbd = new Scanner(System.in);

            // ask for their name
            String name = kbd.nextLine();

            // create a new player in this spot
            players[i] = new Player(i + 1);

            // pass the player's name to the player object
            players[i].setName(name);

            System.out.println();

        }

        Random rand = new Random();

        // generate a random boolean
        boolean randomValue = rand.nextBoolean();

        // if the boolean is true, the value for the first player is an X, otherwise the value
        // for the first player is an O
        String value = randomValue ? "X" : "O";

        // pass the value that was randomly generated
        players[0].setValue(value);

        // if the first player's value is an X
        if (value.equals("X"))
        {
            // then the second player's value is an O
            players[1].setValue("O");
        }
        // if the first player's value is an O
        else
        {
            // then the second player's value is an X
            players[1].setValue("X");
        }

        System.out.println("Player 1 " + players[0].getName() + " will use " + players[0].getWhichValue() + "\n");
        System.out.println("Player 2 " + players[1].getName() + " will use " + players[1].getWhichValue() + "\n");

    }

    public void startGame()
    {
        Board board = new Board();

        System.out.println("Welcome to Tic Tac Toe");

        Random rand = new Random();

        Scanner kbd = new Scanner(System.in);

        int whoseTurn = rand.nextInt(2);

        // continue prompting the users to put down an X or an O until there is a draw or a winner
        while (gameOver(board) == false)
        {
            System.out.println("It's player " + (whoseTurn + 1) + " " + players[whoseTurn].getName() + "'s turn");

            // show the board to the screen
            board.displayBoard();

            System.out.println("Where would you like to put your " + players[whoseTurn].getWhichValue() + ", " + players[whoseTurn].getName() + " ?\n");

            int row = 0;

            String r = "";

            // ask for the user for the column position of their X or O
            String c = "";

            // store the second value in the array in the variable col (stores column #)
            int col = 0;

            boolean spotIsOccupied = true;

            while (spotIsOccupied == true)
            {
                System.out.println("Please enter a row # and column # that isn't occupied");

                while (row < 1 || row > 3)
                {
                    System.out.println("Please enter a number for the row # that is greater than 0 and less than 4");

                    // ask for the user for the row position of their X or O
                    r = kbd.nextLine();

                    while (r.matches("[0-9]+") == false)
                    {
                        System.out.println("Please enter a number");

                        r = kbd.nextLine();

                    }

                    // store the first value in the array in the variable row (stores row #)

                    row = Integer.parseInt(r);
                }
                
                System.out.println();

                while (col < 1 || col > 3)
                {
                    System.out.println("Please enter a number for the column # that is greater than 0 and less than 4");

                    // ask for the user for the row position of their X or O
                    c = kbd.nextLine();

                    while (c.matches("[0-9]+") == false)
                    {
                        System.out.println("Please enter a number");

                        c = kbd.nextLine();

                    }

                    // store the first value in the array in the variable row (stores row #)

                    col = Integer.parseInt(c);
                }

                if (board.getBoard()[row - 1][col - 1].isOccupied() == false)
                {
                    spotIsOccupied = false;
                }

                else
                {
                    // reset the values
                    row = 0;
                    col = 0;
                    System.out.println();
                }

            }

            // put the player's X or O in that position of the board
            board.placeValue(players[whoseTurn].getWhichValue(), row - 1, col - 1);

            // change turns
            whoseTurn = changeTurn(whoseTurn);

            System.out.println("__________________________________________________\n");

        }

        System.out.println("Would you like to play again? y/n");

        String answer = kbd.nextLine();

        while (answer.equalsIgnoreCase("y") == false && answer.equalsIgnoreCase("n") == false)
        {
            System.out.println("Please enter y/n if you wish to play again?");
            answer = kbd.nextLine();
        }

        if (answer.equalsIgnoreCase("y"))
        {
            System.out.println();
            startGame();
        }
        else
        {
            System.out.println("Thank you for playing");
        }

    }

    public boolean gameOver(Board board)
    {

        if (board.reached3InARow())
        {

            board.displayBoard();

            if (players[0].getWhichValue().equals(board.getWinnerValue()))
            {
                System.out.println("Congratulations Player 1 " + players[0].getName() + " You have won!");
            }

            else
            {
                System.out.println("Congratulations Player 2 " + players[1].getName() + "! You have won!");

            }
            return true;
        }

        else if (board.allOccupied())
        {
            System.out.println("It's a tie");
            
            return true;
        }

        return false;
    }

    public int changeTurn(int currentTurn)
    {
        // If it's the last person's turn then we need to reset current turn to 0
        // So we can start off with the first player in the list of players
        if (currentTurn + 1 == players.length)
        {

            currentTurn = 0;
        }

        // It's the next player's turn, add one more to current turn
        else
        {
            currentTurn++;
        }

        return currentTurn;
    }

    public static void main(String[] args)
    {
        TicTacToe game = new TicTacToe();
        game.playersSetup();
        game.startGame();

    }
}
