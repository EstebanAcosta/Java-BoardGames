package Chess;

import java.util.ArrayList;

public class King extends Piece
{

    public King(Player player, Tile position)
    {
        super(player, position);

        super.setPieceType(PieceType.KING);
    }



}
