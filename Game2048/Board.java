package Game2048;
import java.util.Random;
/***
 * 
 * @author estebanacosta
 *
 */
public class Board
{

    private Tile[][] board = new Tile[4][4];

    public Board()
    {

        constructBoard();

    }

    public void randomlyAssignToBoard()
    {
        Random rand = new Random();

        int randCol = rand.nextInt(4);

        int randRow = rand.nextInt(4);

        boolean randomlyChoose = rand.nextBoolean();

        int randNum = randomlyChoose ? 2 : 4;

        while (board[randRow][randCol].isOccupied())
        {

            randCol = rand.nextInt(4);

            randRow = rand.nextInt(4);

        }

        board[randRow][randCol].setOccupant(randNum);
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

    public boolean reachedWinningNumber()
    {

        for (int row = 0; row < board.length; row++)
        {
            for (int col = 0; col < board[row].length; col++)
            {

                if (board[row][col].getOccupant() == 2048)
                {
                    return true;
                }

            }
        }
        return false;
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
