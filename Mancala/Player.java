package Mancala;

import java.util.ArrayList;

/***
 * @author estebanacosta
 */
public class Player
{

    private ArrayList<Stone> stonesInHand = new ArrayList<Stone>();

    private ArrayList<Stone> mancala = new ArrayList<Stone>();

    private String name;

    private int playerID;

    private int playerSide;

    /***
     * Initializes the Player appropriately.
     * @param i
     */
    public Player(int id)
    {
        setPlayerID(id);
    }

    public int getPlayerID()
    {
        return playerID;
    }

    public void setPlayerID(int playerID)
    {
        this.playerID = playerID;
    }

    public int getNumMancalaStones()
    {
        return mancala.size();
    }

    public int getPlayerSide()
    {
        return playerSide;
    }

    public void setPlayerSide(int playerSide)
    {
        this.playerSide = playerSide;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void addStoneToMancala(Stone stone)
    {
        mancala.add(stone);
    }

    /****
     * Adds a bunch of stones to the player's mancala
     * @param stones
     */
    public void addStonesToMancala(ArrayList<Stone> stones)
    {
        mancala.addAll(stones);
    }

    public ArrayList<Stone> getMancala()
    {
        return this.mancala;
    }

    /***
     * Adds a stone to the player's hand. Returns the stone that was recently added.
     * @param initialPosition
     */
    public void addStoneToHand(Stone newStone)
    {

        stonesInHand.add(newStone);

    }

    /****
     * Adds a list of stones to the list of stones in the player's hand
     * @param stones
     */
    public void addStonesToHand(ArrayList<Stone> stones)
    {
        stonesInHand.addAll(stones);
    }

    /***
     * Removes all the stones the player currently has
     */
    public void clearHandStones()
    {
        stonesInHand.clear();
    }

    /***
     * Clears all the stones in the player's mancala
     */
    public void clearMancalaStones()
    {
        mancala.clear();
    }

    /***
     * @return all of the Player's remaining Stones as an array list of Stones.
     */
    public ArrayList<Stone> getStonesInHand()
    {
        return stonesInHand;
    }

    /***
     * Returns the number of stones this Player has remaining.
     */
    public int getNumStonesInHand()
    {
        return stonesInHand.size();
    }

    /***
     * Removes the provided Stone from the player's list of Stones.
     * @param remove
     * @return
     */
    public Stone removeStone()
    {
        return stonesInHand.remove(0);
    }

}
