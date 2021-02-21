package JavaBoardGames.Chess;

public class Pawn extends Piece
{

    public Pawn(Player player, Tile position)
    {
        super(player, position);

        super.setPieceType(PieceType.PAWN);
    }

}
