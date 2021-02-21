package JavaBoardGames.Othello;

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

    public void placeValue(Piece piece, int row, int col)
    {
        board[row][col].setOccupant(piece);
    }


    public void displayBoard()
    {
        System.out.println();
        System.out.print("    ||");
        // print the board to the screen
        for (int i = 0; i < 8; i++)
        {

            if (i == 7)
            {
                System.out.print("   " + (i + 1) + "  |");
            }
            else
            {
                System.out.print("   " + (i + 1) + "  ||");
            }

        }

        System.out.println();
        System.out.println("---------------------------------------------------------------------");

        for (int r = 0; r < 8; r++)
        {
            System.out.print(" " + (r + 1) + "  |");

            for (int c = 0; c < 8; c++)
            {
                if (board[r][c].isOccupied())
                {

                    if(board[r][c].getOccupant().getPieceColor() == PieceColor.BLACK)
                    {
                        System.out.print("|  B  |");
                    }
                    
                    else
                    {
                        System.out.print("|  W  |");
                    }
                    

                }

                else
                {
                    System.out.print("|     |");
                }
            }
            System.out.println();
            System.out.println("---------------------------------------------------------------------");
        }
    }

    public void setLegalMoves()
    {

    }

}
