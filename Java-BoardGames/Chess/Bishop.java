package Chess;

public class Bishop extends Piece
{

    public Bishop(Player player, Tile position)
    {
        super(player, position);

        super.setPieceType(PieceType.BISHOP);
    }

}
