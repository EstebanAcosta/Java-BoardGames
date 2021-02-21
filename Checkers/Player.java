package Checkers;

import java.util.ArrayList;

/***
 * @author estebanacosta
 */
public class Player
{

    private PlayerType color;

    private ArrayList<Piece> pieces = new ArrayList<Piece>();

    /***
     * Initializes the Player appropriately.
     */
    public Player(PlayerType pt)
    {
        this.setColor(pt);

    }

    /***
     * Creates a new Piece and adds it to the Player's list of Pieces. Returns the created Piece.
     * @param initialPosition
     */
    public Piece addPiece(Tile initialPosition)
    {

        Piece piece = new Piece(this, initialPosition);

        pieces.add(piece);

        return piece;
    }

    /***
     * Removes all the pieces the player currently has (used only for the undo method)
     */
    public void clearPieces()
    {
        pieces.clear();
    }

    /***
     * @return all of the Player's remaining Pieces as an array list of Pieces.
     */
    public ArrayList<Piece> getPieces()
    {
        return pieces;
    }

    /***
     * Returns the number of Pieces this Player has remaining.
     */
    public int getNumPieces()
    {

        return pieces.size();

    }

    /***
     * Removes the provided Piece from the player's list of Pieces.
     * @param remove
     */
    public void removePiece(Piece remove)
    {
        pieces.remove(remove);
    }

    /***
     * Sets this Player's color to the color specified
     */
    public void setColor(PlayerType pt)
    {

        this.color = pt;

    }

    /***
     * @return the Player's color
     */
    public PlayerType getColor()
    {

        return this.color;

    }

    /***
     * Kings the supplied Piece.
     * @param piece
     */
    public void king(Piece piece)
    {

        piece.king();

    }

}
