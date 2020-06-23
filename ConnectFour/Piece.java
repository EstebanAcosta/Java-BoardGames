package ConnectFour;

import java.util.ArrayList;


/***
 * @author estebanacosta
 */
public class Piece
{

    private Player player;

    private Tile tile;

    private ArrayList<Move> legalMoves = new ArrayList<Move>();

    private Color color;

    /**
     * Initializes the Piece appropriately, and saves the supplied arguments.
     * @param player
     * @param tile
     * @param direction
     */
    public Piece(Player player, Tile tile)
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

    /***
     * Set the legal moves this piece can take at this state of time
     * @param legalMoves
     */
    public void setLegalMoves(ArrayList<Move> legalMoves)
    {
        this.legalMoves = legalMoves;
    }

    /***
     * @return the set of legal moves this piece can take at this state of time
     */
    public ArrayList<Move> getLegalMoves()
    {
        return legalMoves;
    }


    /**
     * @param tile
     * @return true if this Piece can legally move to the specified Tile.
     */
    public boolean canMoveTo(Tile tile)
    {

        if (tile.isOccupied())
        {
            return false;
        }

        else if (tile.getRow() < 0 || tile.getRow() > 6)
        {
            return false;
        }

        else if (tile.getCol() < 0 || tile.getCol() > 6)
        {
            return false;
        }
        return true;
    }

    /**
     * @return the Player that this Piece belongs to
     */
    public Player getPlayer()
    {
        return this.player;
    }

}
