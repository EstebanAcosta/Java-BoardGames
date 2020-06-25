package Chess;

import java.util.Random;
import java.util.Scanner;

public class chess
{

    Player[] players = new Player[2];

    public void setUpPlayers()
    {
        System.out.println("Welcome To Chess \n");

        Random rand = new Random();

        Scanner kbd = new Scanner(System.in);

        for (int i = 0; i < players.length; i++)
        {
            System.out.println("What is player " + (i + 1) + "'s name ? ");

            Player p = new Player();

            String name = kbd.nextLine();

            players[i] = new Player();

            players[i].setName(name);

            System.out.println();

            System.out.println("__________________________________________________\n");

        }

        // generate a random boolean
        boolean randomValue = rand.nextBoolean();

        PieceColor randomColor = randomValue ? PieceColor.WHITE : PieceColor.BLACK;

        // pass the value that was randomly generated
        players[0].setPlayerColor(randomColor);

        // if the first player's piece color is red
        if (randomColor == PieceColor.BLACK)
        {
            // then the second player's piece color is yellow
            players[1].setPlayerColor(PieceColor.WHITE);
        }
        // if the first player's piece color is yellow
        else
        {
            // then the second player's piece color is redx
            players[1].setPlayerColor(PieceColor.BLACK);
        }

        System.out.println();
    }

    public void setUpBoard()
    {

        Board b = new Board();

        Tile[][] board = b.getBoard();

        Player black = null;
        Player white = null;

        if (players[0].getPlayerColor() == PieceColor.BLACK)
        {
            black = players[0];

            white = players[1];
        }

        else
        {
            black = players[1];

            white = players[0];
        }

        // white player setup
        for (int col = 0; col < 8; col++)
        {
            Pawn pawn = new Pawn(white, new Tile(6,col));
            
            board[1][col].setOccupant(pawn);

            white.addPiece(pawn);
        }

        // black player setup
        for (int col = 0; col < 8; col++)
        {
            Pawn pawn = new Pawn(black, new Tile(6,col));
            
            board[6][col].setOccupant(pawn);

            black.addPiece(pawn);
        }

        // loop through the player's pieces and set each one of their 21 pieces to the player's assigned color
        for (Piece piece : players[0].getPieces())
        {
            piece.setPieceColor(players[0].getPlayerColor());
        }

        for (Piece piece : players[1].getPieces())
        {
            piece.setPieceColor(players[1].getPlayerColor());
        }

        System.out.println(players[0].getName() + " is " + players[0].getPlayerColor() + "\n");

        System.out.println(players[1].getName() + " is " + players[1].getPlayerColor() + "\n");

        System.out.println("---------------------------------------------------\n");

        startGame(b);
    }

    public void startGame(Board board)
    {
        System.out.println("Welcome To Chess\n");

        board.displayBoard();

        int whoseTurn;

        if (players[0].getPlayerColor() == PieceColor.WHITE)
        {
            whoseTurn = 0;
        }

        else
        {
            whoseTurn = 1;
        }

        while (gameOver() == false)
        {

            System.out.println(players[0].getName() + " has " + players[0].getNumPieces() + " pieces left \n ");

            System.out.println(players[1].getName() + " has " + players[1].getNumPieces() + " pieces left \n ");

            System.out.println("It's player " + (whoseTurn + 1) + players[whoseTurn].getName() + "'s turn");

            changeTurn(whoseTurn);

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

    public boolean gameOver()
    {
        return false;
    }

    public static void main(String[] args)
    {
        chess game = new chess();

        game.setUpPlayers();

        game.setUpBoard();

    }
}
