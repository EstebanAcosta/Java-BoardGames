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

        // if the first player's value is an X
        if (randomColor == Color.RED)
        {
            // then the second player's value is an O
            players[1].setPlayerColor(Color.YELLOW);;
        }
        // if the first player's value is an O
        else
        {
            // then the second player's value is an X
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

        while (gameOver(board) == false)
        {

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
