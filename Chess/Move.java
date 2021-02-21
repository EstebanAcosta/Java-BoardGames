package JavaBoardGames.Chess;

/***
 * @author estebanacosta
 */
public class Move
{

    private Tile newSquare;

    private Piece thisPiece;

    private Tile removedSquare;

    Move(Piece thisPiece, Tile newSquare, Tile removedSquare)
    {
        setNewSquare(newSquare);
        setRemovedSquare(removedSquare);
        setPiece(thisPiece);

    }

    public void setPiece(Piece thisPiece)
    {
        this.thisPiece = thisPiece;
    }

    public Piece getPiece()
    {
        return this.thisPiece;
    }

    /***
     * Sets the tile that holds that piece that is going to be removed
     * @param removedTile
     */
    public void setRemovedSquare(Tile removedSquare)
    {
        this.removedSquare = removedSquare;
    }

    /***
     * @return the tile that holds that piece that is going to be removed
     */
    public Tile getRemovedSquare()
    {
        return this.removedSquare;
    }

    /***
     * Sets the new tile that this piece will land on after capturing or moving
     * @param newHome
     */
    public void setNewSquare(Tile newSquare)
    {
        this.newSquare = newSquare;
    }

    /***
     * @return the new tile that this piece will land on after capturing or moving
     */
    public Tile getNewSquare()
    {
        return this.newSquare;
    }

    @Override
    public boolean equals(Object o)
    {

        // If the object is compared with itself then return true
        if (o == this)
        {
            return true;
        }

        if (o == null || !(o instanceof Move))
        {
            return false;
        }

        Move m = (Move) o;

        return this.getNewSquare().equals(m.getNewSquare())
        && this.getRemovedSquare().equals(m.getRemovedSquare());

    }

    @Override
    public int hashCode()
    {

        int hash = 7;

        int result = 31;

        hash = hash * result + newSquare.hashCode();

        hash = hash * result + removedSquare.hashCode();

        return result;

    }

    /***
     * String representation of a move object
     */
    public String toString()
    {
        return newSquare.toString();
    }

}
