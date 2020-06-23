package ConnectFour;
import java.util.Random;
/***
 * 
 * @author esteban acosta
 *
 */
public class Board
{

    private Tile[][] board = new Tile[6][6];

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
                board[row][col].setOccupant(0);
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


    public boolean reached4InARow()
    {

       

        return false;
    }
    
    private void setWinnerValue(int occupant)
    {
        // TODO Auto-generated method stub
        
    }


    public void displayBoard()
    {
        // print the board to the screen

        System.out.println();
        System.out.println("-----------------------------");

        for (int r = 0; r < 4; r++)
        {
            
            for (int c = 0; c < 4; c++)
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
            System.out.println("-----------------------------");
        }
    }

}
