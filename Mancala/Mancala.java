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

        int limit = 8;
        // loop through the players in the game
        for (int i = 0; i < players.length; i++)
        {
            System.out.println("What is the name of player " + (i + 1) + " ?");

            Scanner kbd = new Scanner(System.in);

            // ask for their name
            String name = kbd.nextLine();

            // Continue prompting the player until they put a name that is less than the character limit
            // and doesn't have a number in it
            while (name.length() > limit || !name.matches("[A-Za-z]+"))
            {
                if (name.length() > limit)
                {
                    System.out.println("Please enter a name with less than or equal to " + limit + " characters\n");

                }

                else if (!name.matches("[A-Za-z]+"))
                {
                    System.out.println("Please write a name with no number in it\n");
                }

                name = kbd.nextLine();
            }

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

    public void setUpGame()
    {
        Scanner kbd = new Scanner(System.in);

        int min = 1;

        int max = 4;

        System.out.println("What is the number of rounds you want?\n");

        System.out.println("The min # is " + min + " and the max # is " + max);

        String numRounds = kbd.nextLine();

        // if player gives a non-numerical answer
        // continue prompting player until they give a numeric answer
        while (!numRounds.matches("[0-9]+"))
        {
            System.out.println("Please enter a number for the number of rounds in this new game");

            numRounds = kbd.nextLine();
        }

        // convert input into an integer
        int numberOfRounds = Integer.parseInt(numRounds);

        // Continue promoting the player until they provide
        // a number between 0 and 5
        while (numberOfRounds > max || numberOfRounds < min)
        {
            System.out.println("Please enter a number that is between " + min + " and " + max);

            // get player input
            numRounds = kbd.nextLine();

            // continue prompting player until the player gives a number
            while (!numRounds.matches("[0-9]+"))
            {
                System.out.println("Please enter a number for the number of rounds in this new game");

                numRounds = kbd.nextLine();
            }
            // convert the player input into an integer
            numberOfRounds = Integer.parseInt(numRounds);
        }

        System.out.println("__________________________________________________\n");

        startGame(numberOfRounds);
    }

    public void startGame(int rounds)
    {

        Board board = new Board();

        int currentRound = 0;

        Hole[][] mancalaBoard = board.getBoard();

        // add the players to the board
        board.addPlayers(players);

        Random rand = new Random();

        Scanner kbd = new Scanner(System.in);

        int whoseTurn = rand.nextInt(players.length);

        while (currentRound < rounds)
        {
            while (endGame(board) == false)
            {

                int turn = 1;

                int whichSide = players[whoseTurn].getPlayerSide();

                boolean endedInMancala = false;

                int nextRow = 0;

                int nextCol = 0;

                boolean endTurn = false;

                do
                {

                    System.out.println("Round " + (currentRound + 1) + "\n");

                    System.out.println("Player " + players[whoseTurn].getPlayerID() + " " + players[whoseTurn].getName() + "'s turn \n");

                    System.out.println("Turn " + turn);

                    board.displayBoard();

                    int choiceCol = 0;

                    String c = "";

                    if (turn == 1 || endedInMancala == true)
                    {
                        System.out.println();

                        System.out.println("Which hole do you choose, " + players[whoseTurn].getName() + " ?");

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

                        System.out.println(players[whoseTurn].getName() + " has grabbed " + mancalaBoard[whichSide][choiceCol].getNumStones() + (mancalaBoard[whichSide][choiceCol].getNumStones() == 1 ? " stone " : " stones ") + "from hole "
                        + (choiceCol + 1) + "\n");

                        players[whoseTurn].addStonesToHand(mancalaBoard[whichSide][choiceCol].getOccupants());

                        mancalaBoard[whichSide][choiceCol].clearOccupants();

                        System.out.println(players[whoseTurn].getName() + " has " + players[whoseTurn].getNumStonesInHand() + (players[whoseTurn].getNumStonesInHand() == 1 ? " stone " : " stones ") + "in their hand\n ");

                        String next = "";

                        while (!next.equalsIgnoreCase("n"))
                        {
                            System.out.println("Please enter n for next");

                            next = kbd.nextLine();
                        }

                        System.out.println();

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

                    // if the next column that was computed from the method is the player's mancala
                    // it's time to put a stone in the player's mancala
                    if ((nextCol == 6 && whichSide == 1) || (nextCol == -1 && whichSide == 0))
                    {

                        // since the last stone was dropped in the mancala, this variable needs to be set to true
                        if (players[whoseTurn].getNumStonesInHand() == 1)
                        {

                            endedInMancala = true;
                            
                            // after the player has moved their piece into their mancala, if their side has no stones left
                            if (board.isThisSideEmpty(whichSide))
                            { // end their turn
                                endTurn = true;
                            }
                        }

                        // remove the only stone in the player's hand and place it in their store
                        players[whoseTurn].addStoneToMancala(players[whoseTurn].removeStone());

                        System.out.println(players[whoseTurn].getName() + " has added a stone to their mancala \n");

                        System.out.println(players[whoseTurn].getName() + " has " + players[whoseTurn].getNumStonesInHand() + (players[whoseTurn].getNumStonesInHand() == 1 ? " stone " : " stones ") + "left in their hand\n ");

                    }

                    // if the player hits the opponent's mancala,
                    // skip their mancala
                    else if ((nextCol == 6 && whichSide == 0) || (nextCol == -1 && whichSide == 1))
                    {
                        continue;
                    }

                    // if it's a hole within the bounds of the board
                    // place a stone in the hole
                    // and move to the next hole
                    else
                    {
                        System.out.println(players[whoseTurn].getName() + " has added a stone to hole " + (nextCol + 1) + " on " + (whichSide == nextRow ? "their" : "their opponent's") + " side of the board\n");

                        // take one stone from the player's hand and place it in that hole

                        if (mancalaBoard[nextRow][nextCol].isOccupied() && players[whoseTurn].getNumStonesInHand() == 1)
                        {
                            mancalaBoard[nextRow][nextCol].addStone(players[whoseTurn].removeStone());

                            System.out.println(players[whoseTurn].getName() + " has grabbed " + mancalaBoard[nextRow][nextCol].getNumStones() + (mancalaBoard[nextRow][nextCol].getNumStones() == 1 ? " stone " : " stones ") + "from hole "
                            + (nextCol + 1) + "\n");

                            players[whoseTurn].addStonesToHand(mancalaBoard[nextRow][nextCol].getOccupants());

                            mancalaBoard[nextRow][nextCol].clearOccupants();

                        }

                        // if the hole the player places this stone in is empty
                        // it is officially the end of their turn
                        else if (mancalaBoard[nextRow][nextCol].isOccupied() == false && players[whoseTurn].getNumStonesInHand() == 1)
                        {
                            // set this variable to true to end the player's turn
                            endTurn = true;

                            // add the stone to the empty hole
                            mancalaBoard[nextRow][nextCol].addStone(players[whoseTurn].removeStone());

                            // if the empty hole happens to be on the player's side
                            if (nextRow == whichSide)
                            {
                                // make sure to that stone to the mancala
                                players[whoseTurn].addStonesToMancala(mancalaBoard[nextRow][nextCol].getOccupants());

                                // and remove that stone from the empty hole
                                mancalaBoard[nextRow][nextCol].clearOccupants();

                                // if the current hole is on the second row
                                if (nextRow == 1)
                                {
                                    // pick up all the stones across from the current hole (first row, same column)
                                    // and put in the player's mancala
                                    players[whoseTurn].addStonesToMancala(mancalaBoard[0][nextCol].getOccupants());

                                    // make sure to clear the occupants from this hole
                                    mancalaBoard[0][nextCol].clearOccupants();

                                }

                                // if the current hole is on the first row
                                else
                                {
                                    // pick up all the stones from the hole across from the current hole (second row, same column)
                                    // and put in the player's mancala
                                    players[whoseTurn].addStonesToMancala(mancalaBoard[1][nextCol].getOccupants());

                                    // make sure to clear the occupants from this hole
                                    mancalaBoard[1][nextCol].clearOccupants();

                                }
                            }
                        }

                        else
                        {
                            mancalaBoard[nextRow][nextCol].addStone(players[whoseTurn].removeStone());

                        }



                        System.out.println(players[whoseTurn].getName() + " has " + players[whoseTurn].getNumStonesInHand() + (players[whoseTurn].getNumStonesInHand() == 1 ? " stone " : " stones ") + "left in their hand\n ");

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

                while (endTurn == false);

                whoseTurn = changeTurn(whoseTurn);
            }

            Player winner = null;

            // if the first row of the board isn't empty
            if (board.isThisSideEmpty(0) == false)
            {
                // Calculate how many stones are on that side
                int stonesOnThisSide = board.sumUpStonesOnThisSide(0);

                // Create an empty list of stones
                ArrayList<Stone> stones = new ArrayList<Stone>();

                // Add the number of stones on that side to the list
                for (int i = 0; i < stonesOnThisSide; i++)
                {
                    stones.add(new Stone());
                }

                // if the first player's side is the first row
                if (players[0].getPlayerSide() == 0)
                {

                    // add the stones to the player's mancala
                    players[0].addStonesToMancala(stones);
                }

                // if the second player's side is the second row
                else
                {
                    // add the stones to the player's mancala
                    players[1].addStonesToMancala(stones);

                }
            }

            else if (board.isThisSideEmpty(1) == false)
            {
                // Calculate how many stones are on that side
                int stonesOnThisSide = board.sumUpStonesOnThisSide(1);

                // Create an empty list of stones
                ArrayList<Stone> stones = new ArrayList<Stone>();

                // Add the number of stones on that side to the list
                for (int i = 0; i < stonesOnThisSide; i++)
                {
                    stones.add(new Stone());
                }

                // if the first player's side is the second row
                if (players[0].getPlayerSide() == 1)
                {
                    // add the stones to the player's mancala
                    players[0].addStonesToMancala(stones);

                }

                // if the second player's side is the second row
                else
                {
                    // add the stones to the player's mancala
                    players[1].addStonesToMancala(stones);

                }
            }

            // if the first player has more stones in their mancala than their rival
            if (players[0].getNumMancalaStones() > players[1].getNumMancalaStones())
            {
                // make them the winner
                winner = players[0];
            }

            // if the second player has more stones in their mancala than their vial
            else
            {
                // make them the winner
                winner = players[1];
            }

            System.out.println();

            // declare winner
            System.out.println("The winner of round " + (currentRound + 1) + " is Player " + winner.getPlayerID() + " " + winner.getName() + " with " + winner.getNumMancalaStones() + " stones");

            System.out.println("_____________________________________________________________________________________________\n");

            // add one more to the round
            currentRound++;

            // loop through the list of player
            for (Player p : players)
            {
                // clear the stones in the player's hand
                p.clearHandStones();

                // clear the stones in the player's mancala
                p.clearMancalaStones();
            }

            // reset the board
            board = new Board();

            board.addPlayers(players);

            // The first player to go in the next round is the winner
            whoseTurn = winner.getPlayerID() - 1;

            if(currentRound < rounds)
            {
                System.out.println("Please enter n for next to commence the next round \n");
                String next = kbd.nextLine();

                while (!next.equalsIgnoreCase("n"))
                {
                    System.out.println("Please enter n for next");
                    
                    next = kbd.nextLine();
                }  
            }

        }

    }

    /**
     * Takes the current row # and column # and tries to determine where to move from the current position. Method returns
     * an array that contains the new row # and the new column #
     * @param row
     * @param col
     * @param whichSide
     * @return an array where the first element contains the new row and the second element contains the new column
     */
    public int[] findNextRowAndCol(int row, int col)
    {
        int[] nextRowNCol = new int[2];

        // if we find ourselves currently in the first row
        if (row == 0)
        {
            // and if the current column number is one of the six holes on that side of the board
            if (col >= 0)
            {
                // keep the row number the same
                nextRowNCol[0] = row;

                // and subtract one from the column number(since we are on the first row, in order to place stones in a counterclock wise rotation
                // we would need to move left of that side of the board. And in order to do that in this program the column number needs to go down by one)
                nextRowNCol[1] = col - 1;

            }
            // if the current column number isn't one of the six holes on that side of the board
            else
            {
                // that means we are going to the other side of the board
                // the other side of the board is the second row
                // so we need to just store 1 for the new row
                nextRowNCol[0] = 1;

                // And since we are moving up from the mancala, we need to add one to the column number in order to
                // be on the correct column number
                nextRowNCol[1] = col + 1;

            }
        }
        // if we find ourselves currently in the second row
        else
        {
            // and if the current column number is one of the six holes on that side of the board
            if (col <= 5)
            {
                // keep the row number the same
                nextRowNCol[0] = row;

                // and add one to the column number(since we are on the second row, that means that when placing a
                // stone in the mancala, we are going to be moving to the right. And in order to do that in this program,
                // the column number needs to be go up by one)
                nextRowNCol[1] = col + 1;

            }

            // if the current column number isn't one of the six holes on that side of the board
            else
            {
                // that means we are going to the other side of the board
                // and we need to move up a row, in this case since there are only two rows, we will need to just
                // hardcode which row we are going to be placing this stone, which is the zeroth row.
                nextRowNCol[0] = 0;

                // And since we are moving up from the mancala, we need to subtract one from the column number in order to
                // be on the correct column number
                nextRowNCol[1] = col - 1;

            }
        }

        return nextRowNCol;
    }

    /***
     * @param board
     * @return true if on any side of the board there are no stones
     */
    public boolean endGame(Board board)
    {
        if (board.isThisSideEmpty(0) == true || board.isThisSideEmpty(1) == true)
        {
            return true;
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

        m.setUpGame();
    }
}
