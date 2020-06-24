package ConnectFour;

import java.util.ArrayList;

/***
 * @author estebanacosta
 */
public class Player
{

    private ArrayList<Piece> pieces = new ArrayList<Piece>();

    private Color playerColor;

    private String name;

    /***
     * Initializes the Player appropriately.
     */
    public Player()
    {

    }

    public void setPlayerColor(Color playerColor)
    {
        this.playerColor = playerColor;
    }

    public Color getPlayerColor()
    {
        return this.playerColor;
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


}
