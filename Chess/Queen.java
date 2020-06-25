package Chess;

public class Queen extends Piece
{
    public Queen(Player player, Tile position)
    {
        super(player, position);

        super.setPieceType(PieceType.QUEEN);
    }
}
