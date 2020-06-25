package ConnectFour;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Connect_Four

{
    Player[] players = new Player[2];

    public void setUpPlayers()
    {
        System.out.println("Welcome To Connect Four \n");

        Random rand = new Random();

        Scanner kbd = new Scanner(System.in);

        for (int i = 0; i < 2; i++)
        {
            System.out.println("What is player " + (i + 1) + "'s name ? ");

            players[i] = new Player();

            String name = kbd.nextLine();

            players[i].setName(name);

            for (int j = 0; j < 21; j++)
            {
                players[i].addPiece(new Piece(players[i]));
            }

            System.out.println();

            System.out.println("21 pieces have been added to player " + (i + 1) + "'s pocket");

            System.out.println("__________________________________________________\n");

        }

        // generate a random boolean
        boolean randomValue = rand.nextBoolean();

        Color randomColor = randomValue ? Color.RED : Color.YELLOW;

        // pass the value that was randomly generated
        players[0].setPlayerColor(randomColor);

        // if the first player's piece color is red
        if (randomColor == Color.RED)
        {
            // then the second player's piece color is yellow
            players[1].setPlayerColor(Color.YELLOW);;
        }
        // if the first player's piece color is yellow
        else
        {
            // then the second player's piece color is redx
            players[1].setPlayerColor(Color.RED);
        }

        // loop through the player's pieces and set each one of their 21 pieces to the player's assigned color
        for (Piece piece : players[0].getPieces())
        {
            piece.setColor(players[0].getPlayerColor());
        }

        for (Piece piece : players[1].getPieces())
        {
            piece.setColor(players[1].getPlayerColor());
        }

        System.out.println();

        System.out.println(players[0].getName() + "'s color is " + players[0].getPlayerColor() + "\n");

        System.out.println(players[1].getName() + "'s color is " + players[1].getPlayerColor() + "\n");

        System.out.println("---------------------------------------------------\n");

    }

    public void startGame()
    {
        System.out.println("Welcome To Connect Four \n");

        // create a new board
        Board board = new Board();

        Random rand = new Random();

        Scanner kbd = new Scanner(System.in);

        int whoseTurn = rand.nextInt(2);

        while (gameOver(board) == false)
        {

            ArrayList<Integer> unoccupiedColumns = board.whichOcuppiedColumns();

            System.out.println("It's player " + (whoseTurn + 1) + " " + players[whoseTurn].getName() + "'s turn\n");

            System.out.println(players[0].getName() + "'s pieces: " + players[0].getNumPieces() + "\n");

            System.out.println(players[1].getName() + "'s pieces: " + players[1].getNumPieces() + "\n");

            board.displayBoard();

            System.out.println("Where would you like to put your " + players[whoseTurn].getPlayerColor() + " piece, " + players[whoseTurn].getName() + " ?\n");

            // ask for the user for the column position of their X or O
            String c = "";

            // store the second value in the array in the variable col (stores column #)
            int col = -1;

            // print all available columns to the screen
            printAllAvailableColumns(unoccupiedColumns);

            System.out.println("Please enter a column # that isn't full");

            System.out.println();

            while (unoccupiedColumns.contains(col) == false)
            {
                System.out.println("Please enter a number for the column # that is greater than 0 and less than 7");

                // ask for the user for the row position of their X or O
                c = kbd.nextLine();

                while (c.matches("[0-9]+") == false)
                {
                    System.out.println("Please enter a number");

                    c = kbd.nextLine();

                }

                // store the first value in the array in the variable row (stores row #)

                col = Integer.parseInt(c);
            }

            // put the player's piece in that column
            board.placeValue(players[whoseTurn].removePiece(), col - 1);

            // change turns
            whoseTurn = changeTurn(whoseTurn);

            System.out.println("__________________________________________________\n");

        }
    }

    /**
     * Determines who's the next player
     * @param currentTurn
     * @return
     */
    public int changeTurn(int currentTurn)
    {
        // If it's the last person's turn then we need to reset current turn to 0
        // So we can start off with the first player in the list of players
        if (currentTurn + 1 == players.length)
        {

            currentTurn = 0;
        }

        // It's the next player's turn, add one more to current turn
        else
        {
            currentTurn++;
        }

        return currentTurn;
    }

    /***
     * Determines when the game is over
     * @param board
     * @return
     */
    public boolean gameOver(Board board)
    {
        if (board.reached4InARow())
        {

            board.displayBoard();

            if (players[0].getPlayerColor() == board.getWinnerValue())
            {
                System.out.println("Congratulations Player 1 " + players[0].getName() + " You have won!");
            }

            else
            {
                System.out.println("Congratulations Player 2 " + players[1].getName() + "! You have won!");

            }
            return true;
        }

        else if (board.allOccupied())
        {
            System.out.println("It's a tie");

            return true;
        }

        return false;
    }

    /***
     * Prints all the unoccupied columns
     * @param availableCols
     */
    public void printAllAvailableColumns(ArrayList<Integer> availableCols)
    {
        System.out.print("Available Columns: ");

        int count = 0;
        for (int cols : availableCols)
        {
            if (count == availableCols.size() - 1)
            {
                System.out.print(cols + 1);
            }
            else
            {
                System.out.print(cols + 1 + " , ");
            }

            count++;

        }

        System.out.println();
        System.out.println();
    }

    public static void main(String[] args)
    {
        Connect_Four game = new Connect_Four();

        game.setUpPlayers();

        game.startGame();

    }
}
