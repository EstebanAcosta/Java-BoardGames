package JavaBoardGames.Mancala;

import java.util.ArrayList;

/***
 * @author estebanacosta
 */
public class Player
{

    private ArrayList<Stone> stones = new ArrayList<Stone>();

    private String name;

    private int playerID;

    /***
     * Initializes the Player appropriately.
     * @param i
     */
    public Player(int i)
    {

    }

    public int getPlayerID()
    {
        return playerID;
    }

    public void setPlayerID(int playerID)
    {
        this.playerID = playerID;
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
     * Creates a new Stone and adds it to the Player's list of Stones. Returns the created Stone.
     * @param initialPosition
     */
    public Stone addStone(Stone newStone)
    {

        stones.add(newStone);

        return newStone;
    }

    /***
     * Removes all the Stones the player currently has (used only for the undo method)
     */
    public void clearStones()
    {
        stones.clear();
    }

    /***
     * @return all of the Player's remaining Stones as an array list of Stones.
     */
    public ArrayList<Stone> getStones()
    {
        return stones;
    }

    /***
     * Returns the number of Stones this Player has remaining.
     */
    public int getNumStones()
    {

        return stones.size();

    }

    /***
     * Removes the provided Stone from the player's list of Stones.
     * @param remove
     * @return
     */
    public Stone removeStone()
    {
        return stones.remove(0);
    }

}
