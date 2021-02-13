package JavaBoardGames.Mancala;

import java.util.ArrayList;

/***
 * @author estebanacosta
 */
public class Board
{

    private Hole[][] board = new Hole[2][6];

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

                for (int i = 0; i < 4; i++)
                {
                    board[row][col].addOccupant(new Stone());

                }
            }
        }
    }

    public Hole[][] getBoard()
    {
        return this.board;
    }

    public int howManyOccupied()
    {
        int count = 0;
        for (int row = 0; row < board.length; row++)
        {
            for (int col = 0; col < board[row].length; col++)
            {
                if (board[row][col].isOccupied() == true)
                {
                    count++;
                }
            }
        }

        return count;
    }

    public boolean allOccupied()
    {
        for (int row = 0; row < board.length; row++)
        {
            for (int col = 0; col < board[row].length; col++)
            {
                if (board[row][col].isOccupied() == false)
                {
                    return false;
                }
            }
        }

        return true;
    }

    public void placeValue(Stone piece, int row, int col)
    {
        board[row][col].addOccupant(piece);

    }

    /***
     * Method loops through the player's side of the board and checks to see how many holes
     * on their side has at least one stone in them
     * @param side
     * @return a list of holes that is occupied
     */
    public ArrayList<Integer> returnListOfAvailableHoles(int side)
    {
        //create an array list
        ArrayList<Integer> availableHoles = new ArrayList<Integer>();

        //loop through the player's side of the board
        //loop only through the player's side columns
        for (int col = 0; col < board[side].length; col++)
        {
            //if this hole is occupied
            if (board[side][col].isOccupied())
            {
                //add that column # in the list
                availableHoles.add(col + 1);
            }
        }

        return availableHoles;
    }

    public void displayBoard()
    {
        System.out.println();
        System.out.print("    ||");
        // print the board to the screen
        for (int i = 0; i < board[0].length; i++)
        {

            if (i == 6)
            {
                System.out.print("   " + (i + 1) + "  |");
            }
            else
            {
                System.out.print("   " + (i + 1) + "  ||");
            }

        }

        System.out.println();
        System.out.println("-------------------------------------------------------");

        for (int r = 0; r < board.length; r++)
        {
            System.out.print(" " + (r + 1) + "  |");

            for (int c = 0; c < board[r].length; c++)
            {
                if (board[r][c].isOccupied())
                {

                    // if(board[r][c].getOccupant().getPieceColor() == PieceColor.BLACK)
                    // {
                    // System.out.print("| B |");
                    // }
                    //
                    // else
                    // {
                    // System.out.print("| W |");
                    // }
                    //

                }

                else
                {
                    System.out.print("|     |");
                }
            }
            System.out.println();
            System.out.println("-------------------------------------------------------");
        }
    }

}
