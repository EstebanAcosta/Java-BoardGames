package JavaBoardGames.Mancala;

/***
 * @author estebanacosta
 */
public class Stone
{

    private Player player;

    private Tile tile;



    /**
     * Initializes the Piece appropriately, and saves the supplied arguments.
     * @param player
     * @param tile
     * @param direction
     */
    public Stone(Player player)
    {

        this.player = player;

        this.setCurrentTile(tile);

    }

    /***
     * Sets the tile for this specific piece
     * @param tile
     */
    public void setCurrentTile(Tile tile)
    {
        this.tile = tile;

    }

    /***
     * @return the tile this piece is currently on
     */
    public Tile getCurrentTile()
    {
        return tile;
    }



    /**
     * @return the Player that this Piece belongs to
     */
    public Player getPlayer()
    {
        return this.player;
    }

    public String toString()
    {
        return "S";
        
    }

}
