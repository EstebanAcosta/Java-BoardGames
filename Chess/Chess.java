package Chess;

import java.util.Random;
import java.util.Scanner;

public class Chess
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

            players[i].setName(name);

            System.out.println();

            System.out.println("__________________________________________________\n");

        }

        System.out.println();
    }
    
    public boolean gameOver()
    {
        return false;
    }

    public static void main(String[] args)
    {
        Chess game = new Chess();

        game.setUpPlayers();

    }
}
