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

        // randomly choose an number between 0 and 1
        int whichSide = rand.nextInt(2);

        // set the player row to that random number
        players[0].setPlayerSide(whichSide);

        // give the other player the other row depending on what the first player's row is
        // if the first player's row is the first row
        if (players[0].getPlayerSide() == 0)
        {
            // the second player's side is the second row
            players[1].setPlayerSide(1);

        }

        // if the first player's row is the second row
        else
        {
            // the second player's side is the first row
            players[1].setPlayerSide(0);

        }

        System.out.println();

        // loop through the players and print which side of the board is their
        for (Player p : players)
        {
            System.out.println("Player " + p.getPlayerID() + " " + p.getName() + " has row " + (p.getPlayerSide() + 1));

            System.out.println();

        }

        System.out.println("____________________________________________________");

    }

    public void startGame()
    {

        Board board = new Board();

        Hole[][] mancalaBoard = board.getBoard();

        // add the players to the board
        board.addPlayers(players);

        Random rand = new Random();

        Scanner kbd = new Scanner(System.in);

        int whoseTurn = rand.nextInt(players.length);

        while (endGame(board) == false)
        {
            int turn = 1;

            int whichSide = players[whoseTurn].getPlayerSide();

            boolean endedInMancala = false;

            int nextRow = 0;

            int nextCol = 0;

            do
            {

                System.out.println("Player " + players[whoseTurn].getPlayerID() + " " + players[whoseTurn].getName() + "'s turn \n");

                System.out.println("Turn " + turn);

                board.displayBoard();

                int choiceCol = 0;

                String c = "";

                if (turn == 1 || endedInMancala == true)
                {
                    System.out.println();

                    System.out.println("Which hole do you choose?");

                    // get all the holes that have stones in them
                    ArrayList<Integer> availableHoles = board.returnListOfAvailableHoles(players[whoseTurn].getPlayerSide());

                    // print all the available holes
                    for (int choices : availableHoles)
                    {
                        System.out.println(choices + ": " + choices);
                    }

                    System.out.println();

                    // continue prompting the player until they choose an available choice
                    while (availableHoles.contains(choiceCol) == false)
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
                        choiceCol = Integer.parseInt(c);
                    }

                    // since the choices don't directly represent the array indices
                    // the choice that the player made has to be converted to an array index
                    // the way to do is to take whatever choice they made and subtract 1 from it
                    // and store that value back into the variable
                    choiceCol -= 1;

                    System.out.println();

                    System.out.println(players[whoseTurn].getName() + " has grabbed " + mancalaBoard[whichSide][choiceCol].getNumStones() + " stones from hole " + (choiceCol + 1));

                    players[whoseTurn].addStonesToHand(mancalaBoard[whichSide][choiceCol].getOccupants());

                    mancalaBoard[whichSide][choiceCol].clearOccupants();

                    System.out.println(players[whoseTurn].getName() + " has " + players[whoseTurn].getNumStonesInHand() + (players[whoseTurn].getNumStonesInHand() == 1 ? " stone " : " stones ") + "in their hand ");

                    String next = "";

                    while (!next.equalsIgnoreCase("n"))
                    {
                        System.out.println("Please enter n for next");

                        next = kbd.nextLine();
                    }

                    board.displayBoard();

                    System.out.println();

                    nextRow = whichSide;

                    nextCol = choiceCol;

                    endedInMancala = false;

                }

                // Find the next row and the next column number on the board
                int[] nextRowNCol = findNextRowAndCol(nextRow, nextCol);

                // the first element has the next row
                nextRow = nextRowNCol[0];

                // the second element has the next column
                nextCol = nextRowNCol[1];
                
                // if the next column that was computed from the method is after the last column of the board
                // and the mancala is theirs, it's time to put a stone in the player's mancala
                if ((nextCol == 6 && whichSide == 1) || (nextCol == -1 && whichSide == 0))
                {

                    // since the last stone was dropped in the mancala, this variable needs to be set to true
                    if (players[whoseTurn].getNumStonesInHand() == 1)
                    {

                        endedInMancala = true;
                    }

                    // remove the only stone in the player's hand and place it in their store
                    players[whoseTurn].addStoneToMancala(players[whoseTurn].removeStone());

                    System.out.println(players[whoseTurn].getName() + " has " + players[whoseTurn].getNumStonesInHand() + (players[whoseTurn].getNumStonesInHand() == 1 ? " stone " : " stones ") + "left in their hand ");

                    System.out.println();
                    
                    System.out.println(players[whoseTurn].getName() + " has added a stone to their mancala");

                    System.out.println();

                }

                else
                {
                    mancalaBoard[nextRow][nextCol].addStone(players[whoseTurn].removeStone());

                    System.out.println(players[whoseTurn].getName() + " has " + players[whoseTurn].getNumStonesInHand() + (players[whoseTurn].getNumStonesInHand() == 1 ? " stone " : " stones ") + "left in their hand ");

                    System.out.println();

                    System.out.println(players[whoseTurn].getName() + " has added a stone to hole " + (nextCol + 1) + " on " + (whichSide == nextRow ? "their" : "their opponent's") + " side of the board");

                    System.out.println();
                }

                String next = "";

                while (!next.equalsIgnoreCase("n"))
                {
                    System.out.println("Please enter n for next");

                    next = kbd.nextLine();
                }

                turn++;

                System.out.println("_____________________________________________________________________________________________");
            }

            while (endTurn(whichSide, whoseTurn) == false);

            whoseTurn = changeTurn(whoseTurn);
        }

    }

    public int[] findNextRowAndCol(int row, int col)
    {
        int[] nextRowNCol = new int[2];

        if (row == 0)
        {
            if (col >= 0)
            {
                nextRowNCol[0] = row;

                nextRowNCol[1] = col - 1;

            }

            else
            {
                nextRowNCol[0] = 1;

                nextRowNCol[1] = col + 1;

            }
        }

        else
        {
            if (col <= 5)
            {
                nextRowNCol[0] = row;

                nextRowNCol[1] = col + 1;

            }

            else
            {
                nextRowNCol[0] = 0;

                nextRowNCol[1] = col - 1;

            }
        }

        return nextRowNCol;
    }

    /***
     * @param whoseTurn
     */
    public boolean endTurn(int whichSide, int whoseTurn)
    {

        return false;
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
