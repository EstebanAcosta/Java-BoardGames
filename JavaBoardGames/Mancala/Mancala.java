package JavaBoardGames.Mancala;

import java.util.Random;
import java.util.Scanner;

import JavaBoardGames.Othello.Piece;
import JavaBoardGames.Othello.Player;

public class Mancala
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
        
    }
    

    
    public void startGame()
    {
        
        Random rand = new Random(players.length);
        
        
        
        int whoseTurn = rand.nextInt();
        
        
        
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
    
    public static void main(String[] args)
    {
        Mancala m =  new Mancala();
        
        m.playersSetup();
    }
}
