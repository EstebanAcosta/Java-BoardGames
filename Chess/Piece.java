package JavaBoardGames.Chess;

import java.util.ArrayList;

/***
 * @author estebanacosta
 */
public class Piece
{

    private Player player;

    private Tile tile;

    private PieceColor pieceColor;

    private PieceType pieceType;

    private ArrayList<Move> legalMoves = new ArrayList<Move>();

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

    public Piece()
    {

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
     * @param other
     * @return true if the supplied Piece
     *         belongs to a Player other than this Piece's Player. false otherwise.
     */
    public boolean canCapture(Piece other)
    {

        if (other.getPlayer().getPlayerColor() == this.getPlayer().getPlayerColor())
        {
            return false;
        }
        return true;
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

        else if (tile.getRow() < 0 || tile.getRow() > 7)
        {
            return false;
        }

        else if (tile.getCol() < 0 || tile.getCol() > 7)
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
    

    public PieceColor getPieceColor()
    {
        return pieceColor;
    }

    public void setPieceColor(PieceColor pieceColor)
    {
        this.pieceColor = pieceColor;
    }

    public PieceType getPieceType()
    {
        return pieceType;
    }

    public void setPieceType(PieceType pieceType)
    {
        this.pieceType = pieceType;
    }

    /***
     * @return the set of legal moves this piece can take at this state of time
     */
    public ArrayList<Move> getLegalMoves()
    {
        return legalMoves;
    }


    public void setLegalMoves(ArrayList<Move> legalMoves)
    {
        this.legalMoves = legalMoves;
    }
   

}
