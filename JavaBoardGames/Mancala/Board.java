package JavaBoardGames.Mancala;

import java.util.ArrayList;

/***
 * @author estebanacosta
 */
public class Board
{

    private Hole[][] board = new Hole[2][6];

    private Player[] players = new Player[2];

    public Player[] getPlayers()
    {
        return players;
    }

    public void addPlayers(Player[] players)
    {
        this.players = players;
    }

    public Board()
    {

        constructBoard();

    }

    public void constructBoard()
    {

        for (int row = 0; row < board.length; row++)
        {
            for (int col = 0; col < board[row].length; col++)
            {
                board[row][col] = new Hole(row, col);

                for (int i = 0; i < 20; i++)
                {
                    board[row][col].addStone(new Stone());

                }
            }
        }
    }

    public Hole[][] getBoard()
    {
        return this.board;
    }

    public void placeStone(Stone stone, int row, int col)
    {
        board[row][col].addStone(stone);

    }

    public boolean isThisSideEmpty(int whichSide)
    {

        for (int col = 0; col < board[whichSide].length; col++)
        {

            if (board[whichSide][col].isOccupied())
            {
                return false;
            }
        }

        return true;

    }

    public int sumUpStonesOnThisSide(int whichSide)
    {
        int sum = 0;

        for (int col = 0; col < board[whichSide].length; col++)
        {
            sum += board[whichSide][col].getNumStones();
        }

        return sum;

    }

    /***
     * Method loops through the player's side of the board and checks to see how many holes
     * on their side has at least one stone in them
     * @param side
     * @return a list of holes that is occupied
     */
    public ArrayList<Integer> returnListOfAvailableHoles(int side)
    {
        // create an array list
        ArrayList<Integer> availableHoles = new ArrayList<Integer>();

        // loop through the player's side of the board
        // loop only through the player's side columns
        for (int col = 0; col < board[side].length; col++)
        {
            // if this hole is occupied
            if (board[side][col].isOccupied())
            {
                // add that column # in the list
                availableHoles.add(col + 1);
            }
        }

        return availableHoles;
    }

    public void displayBoard()
    {
        System.out.println();
        System.out.print("                     ||");
        // print the board to the screen
        for (int i = 0; i < board[0].length; i++)
        {

            // if it's the last column
            // print this
            if (i == 6)
            {
                System.out.print("     " + (i + 1) + "      |");
            }

            // if it's any column but the last one
            // print this
            else
            {
                System.out.print("     " + (i + 1) + "      ||");
            }

        }

        System.out.println();
        System.out.println("-------------------------------------------------------------------------------------------------------------------");

        for (int r = 0; r < board.length; r++)
        {
            // if it's the first row and the first player has this side
            // print this statement
            if (r == 0 && players[0].getPlayerSide() == 0)
            {
                System.out.print("Player 1's Mancala:  |");
            }

            // if it's the first row and the second player has this side
            // print this statement
            else if (r == 0 && players[1].getPlayerSide() == 0)
            {
                System.out.print("Player 2's Mancala:  |");
            }

            // otherwise just print an empty space followed by a vertical bar
            else
            {
                System.out.print("                     |");

            }

            // loop through the columns of the board
            for (int c = 0; c < board[r].length; c++)
            {
                // if it's the last column
                // print the number of stones in that hole but with two vertical bars
                if (c == board[r].length - 1)
                {

                    if (board[r][c].getNumStones() < 10)
                    {
                        System.out.print("| " + board[r][c].getNumStones() + (board[r][c].getNumStones() != 1 ? " stones   ||" : " stone    ||"));

                    }

                    else
                    {
                        System.out.print("| " + board[r][c].getNumStones() + (board[r][c].getNumStones() != 1 ? " stones  ||" : " stone   ||"));

                    }

                }

                // if it's any other column but the last one
                // print the number of stones in that hole but with one vertical bar
                else
                {
                    if (board[r][c].getNumStones() < 10)
                    {
                        System.out.print("| " + board[r][c].getNumStones() + (board[r][c].getNumStones() != 1 ? " stones   |" : " stone    |"));

                    }

                    else
                    {
                        System.out.print("| " + board[r][c].getNumStones() + (board[r][c].getNumStones() != 1 ? " stones  |" : " stone   |"));
                    }
                }

            }

            // if it's the second row and the first player has this side
            // print this statement
            if (r == 1 && players[0].getPlayerSide() == 1)
            {
                System.out.print("Player 1's Mancala: ");
            }

            // if it's the second row and the second player has this side
            // print this statement
            else if (r == 1 && players[1].getPlayerSide() == 1)
            {
                System.out.print("Player 2's Mancala: ");
            }

            // skip to the next line
            System.out.println();

            // if it's the first row and the first player has this side
            // print the number of stones the first player has in their mancala
            if (r == 0 && players[0].getPlayerSide() == 0)
            {
                if (players[0].getNumMancalaStones() < 10)
                {
                    System.out.print(players[0].getNumMancalaStones() + (players[0].getNumMancalaStones() == 1 ? "  stone             ||" : " stones             ||"));

                }

                else
                {
                    System.out.print(players[0].getNumMancalaStones() + (players[0].getNumMancalaStones() == 1 ? "  stone            ||" : " stones            ||"));

                }

            }

            // if it's the first row and the second player has this side
            // print the number of stones the second player has in their mancala
            else if (r == 0 && players[1].getPlayerSide() == 0)
            {
                if (players[1].getNumMancalaStones() < 10)
                {
                    System.out.print(players[1].getNumMancalaStones() + (players[1].getNumMancalaStones() == 1 ? "  stone             ||" : " stones             ||"));

                }

                else
                {
                    System.out.print(players[1].getNumMancalaStones() + (players[1].getNumMancalaStones() == 1 ? "  stone            ||" : " stones            ||"));

                }

            }

            // otherwise just print an empty space followed by two vertical bars
            else
            {

                System.out.print("                     ||");
            }

            // loop through columns of the board
            // print an empty space followed by two vertical bars
            for (int c = 0; c < board[r].length; c++)
            {
                System.out.print("            ||");

            }

            // if it's the second row and the first player has this side
            // print the # of stones in the first player's mancala
            if (r == 1 && players[0].getPlayerSide() == 1)
            {
                System.out.print(players[0].getNumMancalaStones() + (players[0].getNumMancalaStones() == 1 ? "  stone            " : " stones            "));

            }

            // if it's the second row and the second player has this side
            // print the # of stones in the second player's mancala
            else if (r == 1 && players[1].getPlayerSide() == 1)
            {
                System.out.print(players[1].getNumMancalaStones() + (players[1].getNumMancalaStones() == 1 ? "  stone            " : " stones            "));

            }

            // skip to the new line
            System.out.println();

            // if it's the second row
            // print this
            if (r == 1)
            {
                System.out.println("--------------------------------------------------------------------------------------------------------------------");

            }

            // if it's the first row
            // print this
            else
            {
                System.out.println("                     ||----------------------------------------------------------------------------------||");

            }
        }
    }

}
