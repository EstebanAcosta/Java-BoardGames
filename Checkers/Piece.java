package Checkers;
import java.util.ArrayList;

public class Piece
{

    private Player player;

    private Tile tile;

    private CheckerType ct;

    private ArrayList<Move> legalMoves = new ArrayList<Move>();

    /**
     * 
     * Initializes the Piece appropriately, and saves the supplied arguments.
     * @param player
     * @param tile
     * @param direction
     */
    public Piece(Player player, Tile tile) {

        this.player = player;

        if(this.player.getColor() == PlayerType.RED) {
            this.setCheckerType(CheckerType.RED_REGULAR);
        }

        else if (this.player.getColor() == PlayerType.BLACK){
            this.setCheckerType(CheckerType.BLACK_REGULAR);
        }

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
     * 
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
    public void setCheckerType(CheckerType ct) {
        this.ct = ct;
    }

    /***
     * 
     * @return  the type of piece
     */
    public CheckerType getCheckerType() {
        return ct;
    }

    /***
     * Set the legal moves this piece can take at this state of time
     * @param legalMoves
     */
    public void setLegalMoves(ArrayList<Move> legalMoves) {
        this.legalMoves = legalMoves;
    }

    /***
     * 
     * @return the set of legal moves this piece can take at this state of time
     */
    public ArrayList<Move> getLegalMoves(){
        return legalMoves;
    }

    /**
     * 
     * @param other
     * @return true if the supplied Piece 
     * belongs to a Player other than this Piece's Player. false otherwise.
     */
    public boolean canCapture(Piece other) {


        if(other.getPlayer().getColor() == this.getPlayer().getColor()) {
            return false;
        }
        return true;
    }

    /**
     * @param tile
     * @return true if this Piece can legally move to the specified Tile.
     */
    public boolean canMoveTo(Tile tile) {

        if(tile.isOccupied()) {
            return false;
        }

        else if(tile.getRow() < 0 || tile.getRow() > 7) {
            return false;
        }

        else if(tile.getCol() < 0 || tile.getCol() > 7) {
            return false;
        }
        return true;
    }

    /**
     * 
     * @return the Player that this Piece belongs to
     */
    public Player getPlayer() {
        return this.player;
    }


    /**
     * 
     * @return true if this Piece has been kinged, false otherwise.
     */
    public boolean isKinged() {

        if(this.ct == CheckerType.BLACK_KING || this.ct == CheckerType.RED_KING ) {
            return true;
        }

        return false;

    }

    /***
     * Sets this Piece to be kinged so that it can move in all directions.
     */
    public void king() {
        if(this.player.getColor() ==  PlayerType.RED) {
            this.setCheckerType(CheckerType.RED_KING);
        }

        else if (this.player.getColor() == PlayerType.BLACK) {
            this.setCheckerType(CheckerType.BLACK_KING);
        }

    }
    
}
