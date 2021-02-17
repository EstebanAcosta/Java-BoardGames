package JavaBoardGames.Mancala;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Mancala
{

    private Player[] players = new Player[2];

    public void playersSetup()
    {

        System.out.println("Welcome to Mancala \n");

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

            System.out.println("____________________________________________________");

        }

        Random rand = new Random();

        int whichSide = rand.nextInt(1);

        players[0].setPlayerSide(whichSide);

        if (players[0].getPlayerSide() == 0)
        {
            players[1].setPlayerSide(1);

        }

        else
        {
            players[1].setPlayerSide(0);

        }

    }

    public void startGame()
    {

        Board board = new Board();
        
        board.addPlayers(players);

        Random rand = new Random();

        Scanner kbd = new Scanner(System.in);

        int whoseTurn = rand.nextInt(players.length);

        while (endGame(board) == false)
        {
            System.out.println(players[whoseTurn].getName() + "'s turn \n");

            int turn = 1;

            System.out.println("Turn " + turn);

            board.displayBoard();

            turn++;

            System.out.println();

            System.out.println("Which hole do you choose?");

            ArrayList<Integer> availableHoles = board.returnListOfAvailableHoles(players[whoseTurn].getPlayerSide());

            for (int choice : availableHoles)
            {
                System.out.println(choice + ": " + choice);
            }
            
            System.out.println();

            int choice = 0;

            String c = "";

            while (availableHoles.contains(choice) == false)
            {
                System.out.println("Enter the column # that is in the list of available holes");

                // ask for the user for the row position
                c = kbd.nextLine();

                while (c.matches("[0-9]+") == false)
                {
                    System.out.println("Please enter a number");
                    c = kbd.nextLine();

                }

                // convert the column value into an integer
                choice = Integer.parseInt(c);
            }

            System.out.println();

            whoseTurn = changeTurn(whoseTurn);

            System.out.println("_____________________________________________________________________________________________");

        }

    }

    /***

     * @param board
     * @return true if on any side of the board there are no stones
     */
    public boolean endGame(Board board)
    {
        // get the mancala board
        Hole[][] mancalaBoard = board.getBoard();

        // loop through the board
        // first through the rows
        for (int r = 0; r < mancalaBoard.length; r++)
        {
            // create a var that keeps track of how many columns don't have any stones in them
            int allColumnsCleared = 0;

            // then through the columns
            for (int c = 0; c < mancalaBoard[r].length; c++)
            {

                // if that specific hole isn't occupied (there aren't any holes in them)
                if (mancalaBoard[r][c].isOccupied() == false)
                {
                    // add one to the var
                    allColumnsCleared++;
                }

            }

            // if every column in that row has no stones
            if (allColumnsCleared == mancalaBoard[r].length)
            {
                // the game is over
                return true;
            }
        }
        return false;
    }

    /**
     * Determine which player goes next
     * @param currentTurn
     * @return
     */
    public int changeTurn(int currentTurn)
    {
        // If it's the last person's turn then we need to reset whoseTurn to 0
        // So we can start off with the first player in the list of players
        if (currentTurn + 1 == players.length)
        {

            currentTurn = 0;
        }

        // It's the next player's turn, add one more to whoseTurn
        else
        {
            currentTurn++;
        }

        return currentTurn;
    }

    public static void main(String[] args)
    {
        Mancala m = new Mancala();

        m.playersSetup();

        m.startGame();
    }
}
