package JavaBoardGames.Mancala;

/***
 * @author estebanacosta
 */
public class Stone
{

    private Hole hole;


    public Stone()
    {

        this.setCurrentHole(hole);

    }

    /***
     * Sets the tile for this specific piece
     * @param tile
     */
    public void setCurrentHole(Hole hole)
    {
        this.hole = hole;

    }

    /***
     * @return the tile this piece is currently on
     */
    public Hole getCurrentHole()
    {
        return hole;
    }

}
