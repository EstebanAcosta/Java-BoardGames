package ConnectFour;

import java.util.ArrayList;

/***
 * @author esteban acosta
 */
public class Board
{

    private Tile[][] board = new Tile[6][6];

    private Color winnerColor;

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
                board[row][col] = new Tile(row, col);
                board[row][col].setOccupant(null);
            }
        }
    }

    public Tile[][] getBoard()
    {
        return this.board;
    }

    public void setBoard(Tile[][] board)
    {
        this.board = board;
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

    public void placeValue(Piece piece, int col)
    {
        int row = whichUnoccupiedRow(col);

        board[row][col].setOccupant(piece);

    }

    /**
     * Loop through each column and determine which columns are unoccupied
     * @return
     */
    public ArrayList<Integer> whichOcuppiedColumns()
    {
        ArrayList<Integer> unoccupiedColumns = new ArrayList<Integer>();

        for (int col = 0; col < board[0].length; col++)
        {
            for (int row = 0; row < board.length; row++)
            {
                if (board[row][col].isOccupied() == false)
                {
                    unoccupiedColumns.add(col + 1);

                    break;
                }
            }
        }

        return unoccupiedColumns;
    }

    /***
     * Helper function determines if there is a certain row in that column that is unoccupied
     * @param col
     * @return
     */
    private int whichUnoccupiedRow(int col)
    {
        int unoccupiedRow = -1;

        for (int row = 0; row < board.length; row++)
        {
            if (board[row][col].isOccupied() == false)
            {
                unoccupiedRow = row;
            }
        }

        return unoccupiedRow;
    }

    public boolean reached4InARow()
    {

        int count = 0;

        for (int r = 0; r < board.length; r++)
        {

            for (int c = 0; c < board[r].length; c++)
            {
                // if the value in the first entry of that row is equal to the current entry of that same row
                if (!board[r][0].getOccupant().equals(null) && board[r][0].getOccupant().getColor().equals(board[r][c].getOccupant().getColor()))
                {

                    // add one more to count
                    count++;
                }

                if (count == 4)
                {
                    setWinnerValue(board[0][c].getOccupant().getColor());

                    return true;
                }

                // otherwise reset the counter to 0
                else
                {
                    count = 0;
                }

            }

        }

        count = 0;

        for (int col = 0; col < board[0].length; col++)
        {
            for (int row = 0; row < board.length; row++)
            {

                // if the value in the first entry of that column is equal to the current entry of that same column
                if (board[0][col].getOccupant().equals(board[row][col].getOccupant()) && !board[0][col].getOccupant().equals(""))
                {
                    // add one more to count
                    count++;
                }

            }

            // if all three numbers in that column are equal return true
            if (count == 4)
            {
                setWinnerValue(board[0][col].getOccupant().getColor());

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

    public void displayBoard()
    {
        // print the board to the screen

        System.out.println();
        System.out.println("------------------------------------------");

        for (int r = 0; r < board.length; r++)
        {

            for (int c = 0; c < board[r].length; c++)
            {
                if (board[r][c].isOccupied())
                {

                    if (board[r][c].getOccupant().getColor() == Color.RED)
                    {
                        System.out.print("|  R  |");
                    }

                    else
                    {
                        System.out.print("|  Y  |");
                    }

                }

                else
                {
                    System.out.print("|     |");
                }
            }
            System.out.println();
            System.out.println("------------------------------------------");
        }
    }

    public void setWinnerValue(Color winnerColor)
    {
        this.winnerColor = winnerColor;
    }

    public Color getWinnerValue()
    {
        return this.winnerColor;
    }

}
