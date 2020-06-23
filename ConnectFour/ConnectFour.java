package ConnectFour;

import java.util.ArrayList;

public class ConnectFour

{
    ArrayList<Player> players = new ArrayList<Player>();
    
    public void setUpGame()
    {

    }

    public void setUpPlayers()
    {
        System.out.println("Welcome To Connect Four \n");
    }

    public void startGame()
    {
        System.out.println("Welcome To Connect Four \n");
    }

    public static void main(String[] args)
    {
        ConnectFour game = new ConnectFour();

        game.setUpPlayers();
    }
}
