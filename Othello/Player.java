package JavaBoardGames.Othello;

import java.util.ArrayList;

/***
 * @author estebanacosta
 */
public class Player
{

    private String name;

    private PieceColor playerColor;

    private ArrayList<Piece> pieces = new ArrayList<Piece>();
    
    private int playerId;

    /***
     * Initializes the Player appropriately.
     */
    public Player()
    {

    }

    public Player(int i)
    {
        setPlayerId(i);
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getPlayerId()
    {
        return playerId;
    }

    public void setPlayerId(int playerId)
    {
        this.playerId = playerId;
    }
    /***
     * Creates a new Piece and adds it to the Player's list of Pieces. Returns the created Piece.
     * @param initialPosition
     */
    public Piece addPiece(Piece piece)
    {

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
    public void setPlayerColor(PieceColor pc)
    {

        this.playerColor = pc;

    }

    /***
     * @return the Player's color
     */
    public PieceColor getPlayerColor()
    {

        return this.playerColor;

    }



}
