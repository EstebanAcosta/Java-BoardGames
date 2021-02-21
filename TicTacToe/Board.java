package JavaBoardGames.TicTacToe;

/***
 * @author estebanacosta
 */
public class Board
{

    private Tile[][] board = new Tile[3][3];

    private String winnerValue;

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
                board[row][col].setOccupant("");
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

    public void setWinnerValue(String value)
    {
        this.winnerValue = value;
    }

    public String getWinnerValue()
    {
        return this.winnerValue;
    }

    public boolean reached3InARow()
    {

        int count = 0;

        for (int row = 0; row < board.length; row++)
        {
            for (int col = 0; col < board[row].length; col++)
            {

                // if the value in the first entry of that row is equal to the current entry of that same row
                if (board[row][0].getOccupant().equals(board[row][col].getOccupant()) && !board[row][0].getOccupant().equals(""))
                {
                    // add one more to count
                    count++;
                }

            }

            // if all three numbers in that row are equal return true
            if (count == 3)
            {
                setWinnerValue(board[row][0].getOccupant());

                return true;
            }

            // otherwise reset the counter to 0
            else
            {
                count = 0;
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
            if (count == 3)
            {
                setWinnerValue(board[0][col].getOccupant());

                return true;
            }

            // otherwise reset the counter to 0
            else
            {
                count = 0;
            }
        }

        // if the entries in the diagonals are all equal
        if (!board[0][0].getOccupant().equals("") && board[0][0].getOccupant().equals(board[1][1].getOccupant()) && board[1][1].getOccupant().equals(board[2][2].getOccupant()))
        {
            setWinnerValue(board[0][0].getOccupant());
            return true;

        }

        else if (!board[0][2].getOccupant().equals("") && board[0][2].getOccupant().equals(board[1][1].getOccupant()) && board[1][1].getOccupant().equals(board[2][0].getOccupant()))
        {
            setWinnerValue(board[0][2].getOccupant());
            return true;

        }

        return false;
    }

    public void placeValue(String value, int row, int col)
    {
        board[row][col].setOccupant(value);
    }

    public void displayBoard()
    {
        System.out.println();
        System.out.print("    ||");
        // print the board to the screen
        for (int i = 0; i < 3; i++)
        {

            if (i == 2)
            {
                System.out.print("   " + (i + 1) + " |");
            }

            else
            {
                System.out.print("   " + (i + 1) + " ||");
            }
        }

        System.out.println();
        System.out.println("-------------------------");

        for (int r = 0; r < 3; r++)
        {
            System.out.print(" " + (r + 1) + "  |");

            for (int c = 0; c < 3; c++)
            {
                if (board[r][c].isOccupied())
                {

                    System.out.print("|  " + board[r][c].getOccupant() + "  |");

                }

                else
                {
                    System.out.print("|     |");
                }
            }
            System.out.println();
            System.out.println("--------------------------");
        }
    }

}
