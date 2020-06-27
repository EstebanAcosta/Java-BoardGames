package Chess;


public class Knight extends Piece
{

    public Knight(Player player, Tile position)
    {
        super(player, position);

        super.setPieceType(PieceType.KNIGHT);
    }

}
