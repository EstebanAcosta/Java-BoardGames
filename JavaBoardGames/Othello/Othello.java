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
        Board b = new Board();

        Tile[][] board = b.getBoard();

        Player black;

        Player white;

        // if the first player is black
        if (players[0].getPlayerColor() == PieceColor.BLACK)
        {

            black = players[0];

            white = players[1];
        }

        // if the second player is black
        else
        {
            black = players[1];

            white = players[0];
        }
        // create two black pieces and two white pieces
        Piece blackP1 = new Piece(black, new Tile(4, 5));
        Piece blackP2 = new Piece(black, new Tile(5, 4));

        Piece whiteP1 = new Piece(white, new Tile(4, 4));
        Piece whiteP2 = new Piece(white, new Tile(5, 5));

        //add the black pieces and white pieces to each player's bag of pieces
        black.addPiece(blackP1);
        black.addPiece(blackP2);

        white.addPiece(whiteP1);
        white.addPiece(whiteP2);

        // set two white pieces in the center
        board[4][4].setOccupant(whiteP1);
        board[5][5].setOccupant(whiteP2);

        // set two black pieces in the center
        board[4][5].setOccupant(blackP1);
        board[5][4].setOccupant(blackP2);

        
        for (Piece p : white.getPieces())
        {
            p.setPieceColor(PieceColor.WHITE);
        }

        for (Piece p : black.getPieces())
        {
            p.setPieceColor(PieceColor.BLACK);
        }

        startGame(b);
    }

    public void startGame(Board board)
    {
        System.out.println("Welcome to Othello \n");
        
        Scanner kbd = new Scanner(System.in);

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

        while (endGame() == false)
        {
            board.setLegalMoves();

            System.out.println(players[0].getName() + " has " + players[0].getNumPieces() + " pieces left \n ");

            System.out.println(players[1].getName() + " has " + players[1].getNumPieces() + " pieces left \n ");

            System.out.println("It's " + players[whoseTurn].getName() + "'s turn");

            System.out.println("It's " + players[whoseTurn].getPlayerColor() + "'s turn\n");

            board.displayBoard();
            
            System.out.println("Please enter a row # and column # that isn't occupied");

            int row = 0;

            while (row < 1 || row > 8)
            {
                System.out.println("Enter the row # that is greater than 0 and less than 9");

                // ask for the user for the row position of their X or O
                String r = kbd.nextLine();

                while (r.matches("[0-9]+") == false)
                {
                    System.out.println("Please enter a number");

                    r = kbd.nextLine();

                }

                //convert the row value into an integer
                row = Integer.parseInt(r);
            }

            System.out.println();

            int col = 0;

            while (col < 1 || col > 8)
            {
                System.out.println("Enter the column # that is greater than 0 and less than 9");

                // ask for the user for the row position
                String c = kbd.nextLine();

                while (c.matches("[0-9]+") == false)
                {
                    System.out.println("Please enter a number");

                    c = kbd.nextLine();

                }

                // convert the column value into an integer
                col = Integer.parseInt(c);
            }

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
        for (Player p : players)
        {

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
