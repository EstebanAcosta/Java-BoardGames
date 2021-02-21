package Mancala;

import java.util.ArrayList;

/***
 * @author estebanacosta
 */
public class Hole
{

    private int col;

    private int row;

    private ArrayList<Stone> stones = new ArrayList<Stone>();

    private Player player;

    /**
     * Initializes the hole with the given row and column
     * @param col
     * @param row
     */
    public Hole(int row, int col)
    {
        this.setColumn(col);
        this.setRow(row);
    }

    public Player getPlayer()
    {
        return player;
    }

    public void setPlayer(Player player)
    {
        this.player = player;
    }

    private void setRow(int r)
    {
        this.row = r;
    }

    public int getRow()
    {
        return this.row;
    }

    private void setColumn(int c)
    {
        this.col = c;
    }

    public int getCol()
    {
        return this.col;
    }

    public int getNumStones()
    {
        return stones.size();
    }

    /***
     * Remove all stones from this hole
     */
    public void clearOccupants()
    {
        stones.clear();
    }

    /**
     * Create a list of stones that this hole has
     * @return the stone that is occupying this hole, or null if the hole has no
     *         occupant.
     */
    public ArrayList<Stone> getOccupants()
    {

        return this.stones;
    }

    /**
     * Add a new stone to this hole.
     * @param newOccupant
     */
    public void addStone(Stone stone)
    {

        this.stones.add(stone);
    }

    /***
     * Add multiple stones to this hole
     * @param stones
     */
    public void addStones(ArrayList<Stone> stones)
    {
        this.stones.addAll(stones);
    }

    /**
     * @return true if the hole has a stone on it, false otherwise
     */
    public boolean isOccupied()
    {

        if (getNumStones() != 0)
        {
            return true;
        }

        return false;
    }

    @Override
    public boolean equals(Object o)
    {

        // If the object is compared with itself then return true
        if (o == this)
        {
            return true;
        }

        /*
         * Check if o is an instance of Complex or not "null instanceof [type]" also
         * returns false
         */
        if (!(o instanceof Hole))
        {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        Hole t = (Hole) o;

        boolean similarOccupants = true;

        // Compare the data members and return accordingly
        return this.row == t.getRow() && this.col == t.getCol() && this.getOccupants().size() == t.getOccupants().size();
    }

    @Override
    public int hashCode()
    {

        int hash = 7;

        int result = 31;

        hash = hash * result + col;

        hash = hash * result + row;

        result = hash * result;

        return result;

    }

    public String toString()
    {
        return " # of Stones: " + stones.size() + "\n " + this.getRow() + " " + this.getCol();
    }

}
