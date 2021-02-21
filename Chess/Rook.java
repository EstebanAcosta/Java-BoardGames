package JavaBoardGames.Chess;

public class Rook extends Piece
{

    public Rook(Player player, Tile position)
    {
        super(player, position);

        super.setPieceType(PieceType.ROOK);
    }

}
