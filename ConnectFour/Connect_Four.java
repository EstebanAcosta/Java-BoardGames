package ConnectFour;

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

            System.out.println();

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

        System.out.println();

        System.out.println(players[0].getName() + "'s color is " + players[0].getPlayerColor() + "\n");

        System.out.println(players[0].getName() + "'s color is " + players[0].getPlayerColor() + "\n");

        System.out.println("---------------------------------------------------\n");

        startGame();

    }

    public void startGame()
    {
        System.out.println("Welcome To Connect Four \n");

        Board board = new Board();

        Random rand = new Random();

        Scanner kbd = new Scanner(System.in);

        int whoseTurn = rand.nextInt(2);

        while (gameOver(board) == false)
        {
            System.out.println("It's player " + (whoseTurn + 1) + " " + players[whoseTurn].getName() + "'s turn");

            board.displayBoard();

            System.out.println("Where would you like to put your " + players[whoseTurn].getPlayerColor() + " piece, " + players[whoseTurn].getName() + " ?\n");

            boolean spotIsOccupied = true;

            int row = 0;

            String r = "";

            // ask for the user for the column position of their X or O
            String c = "";

            // store the second value in the array in the variable col (stores column #)
            int col = 0;

            while (spotIsOccupied == true)
            {
                System.out.println("Please enter a row # and column # that isn't occupied");

                while (row < 1 || row > 6)
                {
                    System.out.println("Please enter a number for the row # that is greater than 0 and less than 7");

                    // ask for the user for the row position of their X or O
                    r = kbd.nextLine();

                    while (r.matches("[0-9]+") == false)
                    {
                        System.out.println("Please enter a number");

                        r = kbd.nextLine();

                    }

                    // store the first value in the array in the variable row (stores row #)

                    row = Integer.parseInt(r);
                }
                
                System.out.println();

                while (col < 1 || col > 6)
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

                if (board.getBoard()[row - 1][col - 1].isOccupied() == false)
                {
                    spotIsOccupied = false;
                }

                else
                {
                    // reset the values
                    row = 0;
                    col = 0;
                    System.out.println();
                }

            }

            // put the player's X or O in that position of the board
//            board.placeValue(players[whoseTurn].getPlayerColor(), row - 1, col - 1);
            
            // change turns
            whoseTurn = changeTurn(whoseTurn);

            System.out.println("__________________________________________________\n");
            
            break;

        }
    }

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

    public static void main(String[] args)
    {
        Connect_Four game = new Connect_Four();

        game.setUpPlayers();

    }
}
