package JavaBoardGames.Othello;

import java.util.Random;
import java.util.Scanner;

public class Othello
{

    Player[] players = new Player[2];

    public void playersSetup()
    {

        System.out.println("Welcome to Othello \n");

        // loop through the players in the game
        for (int i = 0; i < players.length; i++)
        {
            System.out.println("What is the name of player " + (i + 1) + " ?");

            Scanner kbd = new Scanner(System.in);

            // ask for their name
            String name = kbd.nextLine();

            // create a new player in this spot
            players[i] = new Player(i + 1);

            // pass the player's name to the player object
            players[i].setName(name);

            for (int j = 0; j < 30; j++)
            {
                players[i].addPiece(new Piece());
            }

            System.out.println(players[i].getName() + " has " + players[i].getNumPieces() + " pieces");

            System.out.println("____________________________________________________");

        }

        Random rand = new Random();

        // generate a random boolean
        boolean randomValue = rand.nextBoolean();

        PieceColor randomColor = randomValue ? PieceColor.WHITE : PieceColor.BLACK;

        // pass the value that was randomly generated
        players[0].setPlayerColor(randomColor);

        // if the first player's piece color is black
        if (randomColor == PieceColor.BLACK)
        {
            // then the second player's piece color is white
            players[1].setPlayerColor(PieceColor.WHITE);
        }
        // if the first player's piece color is white
        else
        {
            // then the second player's piece color is black
            players[1].setPlayerColor(PieceColor.BLACK);
        }

        System.out.println();

    }

    public void setUpBoard()
    {
        Board board = new Board();

        startGame(board);
    }

    public void startGame(Board board)
    {
        System.out.println("Welcome to Othello \n");

        int whoseTurn = 0;

        // if the first player is black
        if (players[0].getPlayerColor() == PieceColor.BLACK)
        {
            // they will be the first player to put down a piece
            whoseTurn = 0;
        }

        // if the second player is black
        else
        {
            // they will be the first player to put down a piece
            whoseTurn = 1;
        }

        while (endGame())
        {
            System.out.println("It's " + players[whoseTurn].getName() + "'s turn");
            
            board.displayBoard();

            whoseTurn = changeTurn(whoseTurn);

            break;
        }
    }

    /**
     * Determine which player goes next
     * @param currentTurn
     * @return
     */
    public int changeTurn(int currentTurn)
    {
        // If it's the last person's turn then we need to reset whoseTurn to 0
        // So we can start off with the first player in the list of players
        if (currentTurn + 1 == players.length)
        {

            currentTurn = 0;
        }

        // It's the next player's turn, add one more to whoseTurn
        else
        {
            currentTurn++;
        }

        return currentTurn;
    }
    
    public boolean endGame()
    {
        for(Player p : players)
        {
            return true;
        }
        
        return false;
    }

    public static void main(String[] args)
    {

        Othello o = new Othello();

        o.playersSetup();

        o.setUpBoard();
    }
}
