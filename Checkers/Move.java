package Checkers;
import java.util.ArrayList;

public class Move
{

    private Tile newHome;
    private boolean canEat;
    private Tile removedHome;
    private ArrayList<Move> jumpSequence = new ArrayList<Move>();
    private Piece thisPiece;


    Move(Piece thisPiece, Tile newHome, Tile removedHome ,boolean canEat){
        setNewHome(newHome);
        setRemovedHome(removedHome);
        eat(canEat);
        setPiece(thisPiece);

    }
    
    public void setPiece(Piece thisPiece) {
        this.thisPiece= thisPiece;
    }

    public Piece getPiece()
    {
        return this.thisPiece;
    }
    
    /***
     * Sets the tile that holds that piece that is going to be removed
     * @param removedTile
     */
    public void setRemovedHome(Tile removedHome) {
        this.removedHome = removedHome;
    }

    /***
     * 
     * @return the tile that holds that piece that is going to be removed
     */
    public Tile getRemovedHome() {
        return this.removedHome;
    }

    /***
     * Sets the new tile that this piece will land on after capturing or moving 
     * @param newHome
     */
    public void setNewHome(Tile newHome) {
        this.newHome = newHome;
    }


    /***
     * 
     * @return the new tile that this piece will land on after capturing or moving 
     */
    public Tile getNewHome() {
        return this.newHome;
    }

    /***
     * Add all the jumps in this variation
     * @param jumpTile
     */
    public void addJumps(Move jumpTile) {
        
        jumpSequence.add(jumpTile);
        
    }

    /**
     * Get the different jump variations that can arise from this one move
     * @return
     */
    public ArrayList<Move> getJumps(){

        return this.jumpSequence;
    }

    /***
     * Set true if this piece can make a capture/multiple captures
     * @param canEat
     */
    public void eat(boolean canEat) {
        this.canEat = canEat;
    }


    /***
     * 
     * @return true if this piece can make a capture/multiple captures
     */
    public boolean canEat() {
        return this.canEat;
    }

    @Override
    public boolean equals(Object o) { 

        // If the object is compared with itself then return true   
        if(o == this) {
            return true;
        }

        if(o == null || !(o instanceof Move)) { 
            return false; 
        } 

        Move m = (Move) o;

        return this.canEat() == m.canEat() 
        && this.getNewHome().equals(m.getNewHome()) 
        && this.getRemovedHome().equals(m.getRemovedHome());

    }

    @Override
    public int hashCode() {

        int hash =7;

        int result = 31;

        hash = hash * result + newHome.hashCode();

        hash = hash * result + removedHome.hashCode();

        result = hash * result + (canEat ? 1 : 0);

        return result;

    }

    /***
     * String representation of a move object
     */
    public String toString() {
        return newHome.toString();
    }



}
