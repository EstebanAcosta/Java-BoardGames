package JavaBoardGames.Mancala;

/***
 * @author estebanacosta
 */
public class Stone
{

    private Hole tile;

    /**
     * Initializes the Piece appropriately, and saves the supplied arguments.
     * @param player
     * @param tile
     * @param direction
     */
    public Stone()
    {

        this.setCurrentHole(tile);

    }

    /***
     * Sets the tile for this specific piece
     * @param tile
     */
    public void setCurrentHole(Hole tile)
    {
        this.tile = tile;

    }

    /***
     * @return the tile this piece is currently on
     */
    public Hole getCurrentHole()
    {
        return tile;
    }

}
