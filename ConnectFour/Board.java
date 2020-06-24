package ConnectFour;

import java.util.Random;

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

    public void placeValue(Color color, int row, int col)
    {
        board[row][col].setOccupant(color);
    }

    public boolean reached4InARow()
    {
        return false;
    }
    
    public void displayBoard()
    {
        // print the board to the screen

        System.out.println();
        System.out.println("------------------------------------------");

        for (int r = 0; r < 6; r++)
        {

            for (int c = 0; c < 6; c++)
            {
                if (board[r][c].isOccupied())
                {

                    if(board[r][c].getOccupant() == Color.RED)
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
