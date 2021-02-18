package JavaBoardGames.Mancala;

import java.util.ArrayList;

/***
 * @author estebanacosta
 */
public class Player
{

    private ArrayList<Stone> stonesInHand = new ArrayList<Stone>();

    private String name;

    private int playerID;

    private int playerSide;

    private int mancalaStones;

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
        return mancalaStones;
    }

    public void setNumMancalaStones(int mancalaStones)
    {
        this.mancalaStones = mancalaStones;
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

    /***
     * Creates a new stone and adds it to the player's hand. Returns the stone that was recently added.
     * @param initialPosition
     */
    public Stone addStoneToHand(Stone newStone)
    {

        stonesInHand.add(newStone);

        return newStone;
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
     * Removes all the stones the player currently has (used only for the undo method)
     */
    public void clearStones()
    {
        stonesInHand.clear();
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
