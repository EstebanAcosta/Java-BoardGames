package JavaBoardGames.Othello;

import java.util.Random;
import java.util.Scanner;

import JavaBoardGames.TicTacToe.Player;

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

            System.out.println();

        }

        Random rand = new Random();


        System.out.println("Player 1 " + players[0].getName() + " will use " + players[0].getWhichValue() + "\n");
        System.out.println("Player 2 " + players[1].getName() + " will use " + players[1].getWhichValue() + "\n");

    }
    
    
    public void startGame()
    {
        while(true)
        {
            break;
        }
    }
    
    public static void main(String[] args)
    {
        
        Othello o = new Othello();
        
        o.playersSetup();
    }
}
