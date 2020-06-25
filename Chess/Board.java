package Chess;

/***
 * @author estebanacosta
 */
public class Board
{

    private Tile[][] board = new Tile[8][8];

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

    public void setWinnerValue(String value)
    {
        this.winnerValue = value;
    }

    public String getWinnerValue()
    {
        return this.winnerValue;
    }

    public void placeValue(Piece value, int row, int col)
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

        for (int r = 0; r < 8; r++)
        {
            System.out.print(" " + (r + 1) + "  |");

            for (int c = 0; c < 8; c++)
            {
                if (board[r][c].isOccupied())
                {

                    if (board[r][c].getOccupant().getPieceType() == PieceType.PAWN)
                    {
                        System.out.print("|  P  |");
                    }

                    else if (board[r][c].getOccupant().getPieceType() == PieceType.BISHOP)
                    {
                        System.out.print("|  B  |");
                    }

                    else if (board[r][c].getOccupant().getPieceType() == PieceType.ROOK)
                    {
                        System.out.print("|  R  |");
                    }

                    else if (board[r][c].getOccupant().getPieceType() == PieceType.KNIGHT)
                    {
                        System.out.print("|  KN  |");
                    }

                    else if (board[r][c].getOccupant().getPieceType() == PieceType.QUEEN)
                    {
                        System.out.print("|  Q  |");
                    }

                    else
                    {
                        System.out.print("|  K  |");
                    }

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
