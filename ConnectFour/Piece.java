package ConnectFour;

/***
 * @author estebanacosta
 */
public class Piece
{

    private Player player;

    private Tile tile;

    private Color color;

    /**
     * Initializes the Piece appropriately, and saves the supplied arguments.
     * @param player
     * @param tile
     * @param direction
     */
    public Piece(Player player)
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
     * Set the type of piece
     * @param ct
     */
    public void setColor(Color color)
    {
        this.color = color;
    }

    /***
     * @return the type of piece
     */
    public Color getColor()
    {
        return color;
    }

    /**
     * @return the Player that this Piece belongs to
     */
    public Player getPlayer()
    {
        return this.player;
    }

}
