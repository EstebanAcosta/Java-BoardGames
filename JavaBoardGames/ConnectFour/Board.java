package JavaBoardGames.ConnectFour;

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

        /**
         * loop through each row of the grid to see if the same color pops up four times
         */

        for (int row = 0; row < board.length; row++)
        {
            if (board[row][0].getOccupant() != null && board[row][1].getOccupant() != null && board[row][2].getOccupant() != null && board[row][3].getOccupant() != null)
            {
                if (board[row][0].getOccupant().getColor() == board[row][1].getOccupant().getColor() && board[row][1].getOccupant().getColor() == board[row][2].getOccupant().getColor()
                && board[row][2].getOccupant().getColor() == board[row][3].getOccupant().getColor())
                {

                    setWinnerValue(board[row][0].getOccupant().getColor());

                    return true;
                }
            }

            if (board[row][1].getOccupant() != null && board[row][2].getOccupant() != null && board[row][3].getOccupant() != null && board[row][4].getOccupant() != null)
            {

                if (board[row][1].getOccupant().getColor() == board[row][2].getOccupant().getColor() && board[row][2].getOccupant().getColor() == board[row][3].getOccupant().getColor()
                && board[row][3].getOccupant().getColor() == board[row][4].getOccupant().getColor())
                {
                    setWinnerValue(board[row][1].getOccupant().getColor());

                    return true;
                }

            }

            if (board[row][2].getOccupant() != null && board[row][3].getOccupant() != null && board[row][4].getOccupant() != null && board[row][5].getOccupant() != null)
            {

                if (board[row][2].getOccupant().getColor() == board[row][3].getOccupant().getColor() && board[row][3].getOccupant().getColor() == board[row][4].getOccupant().getColor()
                && board[row][4].getOccupant().getColor() == board[row][5].getOccupant().getColor())
                {
                    setWinnerValue(board[row][2].getOccupant().getColor());

                    return true;
                }

            }

        }

        /***
         * Loop through each column of the grid to see if the same color pops up four times
         */

        for (int col = 0; col < board[0].length; col++)
        {

            // if the value in the first entry of that column is equal to the current entry of that same column
            if (board[5][col].getOccupant() != null && board[4][col].getOccupant() != null && board[3][col].getOccupant() != null && board[2][col].getOccupant() != null)
            {

                if (board[5][col].getOccupant().getColor() == board[4][col].getOccupant().getColor() && board[4][col].getOccupant().getColor() == board[3][col].getOccupant().getColor() &&
                board[3][col].getOccupant().getColor() == board[2][col].getOccupant().getColor())
                {
                    setWinnerValue(board[5][col].getOccupant().getColor());

                    return true;
                }

            }

            if (board[4][col].getOccupant() != null && board[3][col].getOccupant() != null && board[2][col].getOccupant() != null && board[1][col].getOccupant() != null)
            {
                if (board[4][col].getOccupant().getColor() == board[3][col].getOccupant().getColor() && board[3][col].getOccupant().getColor() == board[2][col].getOccupant().getColor() &&
                board[2][col].getOccupant().getColor() == board[1][col].getOccupant().getColor())
                {
                    setWinnerValue(board[4][col].getOccupant().getColor());

                    return true;
                }

            }

            if (board[3][col].getOccupant() != null && board[2][col].getOccupant() != null && board[1][col].getOccupant() != null && board[0][col].getOccupant() != null)
            {

                if (board[3][col].getOccupant().getColor() == board[2][col].getOccupant().getColor() && board[2][col].getOccupant().getColor() == board[1][col].getOccupant().getColor() &&
                board[1][col].getOccupant().getColor() == board[0][col].getOccupant().getColor())
                {
                    setWinnerValue(board[3][col].getOccupant().getColor());

                    return true;
                }
            }

        }

        final int ROW = board.length;

        final int COL = board.length;

        // There will be ROW+COL-1 lines in the output
        for (int line = 1; line <= (ROW + COL - 1); line++)
        {

            // Get column index of the first element in this
            // line of output.The index is 0 for first ROW
            // lines and line - ROW for remaining lines
            int start_col = max(0, line - ROW);

            // Get count of elements in this line. The count
            // of elements is equal to minimum of line number,
            // COL-start_col and ROW
            int count = min(line, (COL - start_col), ROW);

            //if the number of elements in that specific diaganal line is more than or equal to 4
            if (count >= 4)
            {
                int countSameColor = 0;

                // Print elements of this line
                for (int j = 0; j < count; j++)
                {

                    int currentRow = min(ROW, line) - j - 1;

                    int currentCol = start_col + j;

                    if (board[currentRow][currentCol].isOccupied())
                    {

                        System.out.print(board[currentRow][currentCol].getOccupant() + " ");

                    }
                }

                if (countSameColor == 4)
                {
                    return true;
                }
            }

            // Print elements of next diagonal on next line
            System.out.println();
        }
        return false;
    }

    /***
     * A utility function to find min of two integers
     * @param a
     * @param b
     * @return
     */
    public int min(int a, int b)
    {
        return (a < b) ? a : b;
    }

    /***
     * A utility function to find min of three integers
     * @param a
     * @param b
     * @param c
     * @return
     */

    public int min(int a, int b, int c)
    {
        return min(min(a, b), c);
    }

    /***
     * A utility function to find max of two integers
     * @param a
     * @param b
     * @return
     */

    public int max(int a, int b)
    {
        return (a > b) ? a : b;
    }

    /***
     * Displays the board on the screen
     */
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

    /***
     * Finds the winner of the game and stores the color of the winning planner
     * @param winnerColor
     */
    public void setWinnerValue(Color winnerColor)
    {
        this.winnerColor = winnerColor;
    }

    /***
     * @returns the color of the winner of the game
     */
    public Color getWinnerValue()
    {
        return this.winnerColor;
    }

}
