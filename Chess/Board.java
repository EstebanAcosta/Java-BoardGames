package JavaBoardGames.Chess;

import java.util.ArrayList;

/***
 * @author estebanacosta
 */
public class Board
{

    private Tile[][] board = new Tile[8][8];

    public Board()
    {

        constructBoard();

    }

    public void constructBoard()
    {

        for (int row = 0; row < board.length; row++)
        {
            for (int col = 0; col < board[row].length; col++)
            {
                board[row][col] = new Tile(row, col);
                board[row][col].setOccupant(null);
            }
        }
    }

    public void setLegalMoves()
    {
        for (int row = 0; row < board.length; row++)
        {
            for (int col = 0; col < board[row].length; col++)
            {
                if (board[row][col].isOccupied())
                {
                    if (board[row][col].getOccupant().getPieceType() == PieceType.BISHOP)
                    {
                        setBishopLegalMoves((Bishop) board[row][col].getOccupant());
                    }

                    else if (board[row][col].getOccupant().getPieceType() == PieceType.KNIGHT)
                    {
                        setKnightLegalMoves((Knight) board[row][col].getOccupant());
                    }

                    else if (board[row][col].getOccupant().getPieceType() == PieceType.ROOK)
                    {
                        setRookLegalMoves((Rook) board[row][col].getOccupant());
                    }

                    else if (board[row][col].getOccupant().getPieceType() == PieceType.QUEEN)
                    {
                        setQueenLegalMoves((Queen) board[row][col].getOccupant());
                    }

                    else if (board[row][col].getOccupant().getPieceType() == PieceType.KING)
                    {
                        setKingLegalMoves((King) board[row][col].getOccupant());
                    }

                    else
                    {
                        setPawnLegalMoves((Pawn) board[row][col].getOccupant());
                    }
                }
            }
        }

    }

    public void setKnightLegalMoves(Knight knight)
    {
        ArrayList<Move> legalMoves = new ArrayList<Move>();
        
        knight.setLegalMoves(legalMoves);
    }

    public void setRookLegalMoves(Rook rook)
    {
        ArrayList<Move> legalMoves = new ArrayList<Move>();
        
        rook.setLegalMoves(legalMoves);
    }

    public void setBishopLegalMoves(Bishop bishop)
    {
        ArrayList<Move> legalMoves = new ArrayList<Move>();
        
        bishop.setLegalMoves(legalMoves);
    }

    public void setKingLegalMoves(King king)
    {
        ArrayList<Move> legalMoves = new ArrayList<Move>();
        
        king.setLegalMoves(legalMoves);
    }

    public void setQueenLegalMoves(Queen queen)
    {
        ArrayList<Move> legalMoves = new ArrayList<Move>();
        
        queen.setLegalMoves(legalMoves);
    }

    public void setPawnLegalMoves(Pawn pawn)
    {
        ArrayList<Move> legalMoves = new ArrayList<Move>();
        
      
       
        
        pawn.setLegalMoves(legalMoves);
    }

    public Tile[][] getBoard()
    {
        return this.board;
    }

    public void setBoard(Tile[][] board)
    {
        this.board = board;
    }

    public void displayBoard()
    {
        System.out.println();
        System.out.print("    ||");
        // print the board to the screen
        for (int i = 0; i < 8; i++)
        {

            if (i == 7)
            {
                System.out.print("   " + (i + 1) + "  |");
            }
            else
            {
                System.out.print("   " + (i + 1) + "  ||");
            }

        }

        System.out.println();
        System.out.println("---------------------------------------------------------------------");

        for (int r = 0; r < 8; r++)
        {
            System.out.print(" " + (r + 1) + "  |");

            for (int c = 0; c < 8; c++)
            {
                if (board[r][c].isOccupied())
                {

                    if (board[r][c].getOccupant().getPieceColor() == PieceColor.WHITE)
                    {
                        if (board[r][c].getOccupant().getPieceType() == PieceType.PAWN)
                        {
                            System.out.print("|  WP  |");
                        }

                        else if (board[r][c].getOccupant().getPieceType() == PieceType.BISHOP)
                        {
                            System.out.print("|  WB  |");
                        }

                        else if (board[r][c].getOccupant().getPieceType() == PieceType.ROOK)
                        {
                            System.out.print("|  WR  |");
                        }

                        else if (board[r][c].getOccupant().getPieceType() == PieceType.KNIGHT)
                        {
                            System.out.print("|  WKN  |");
                        }

                        else if (board[r][c].getOccupant().getPieceType() == PieceType.QUEEN)
                        {
                            System.out.print("|  WQ  |");
                        }

                        else
                        {
                            System.out.print("|  WK  |");
                        }
                    }

                    else
                    {
                        if (board[r][c].getOccupant().getPieceType() == PieceType.PAWN)
                        {
                            System.out.print("|  BP  |");
                        }

                        else if (board[r][c].getOccupant().getPieceType() == PieceType.BISHOP)
                        {
                            System.out.print("|  BB  |");
                        }

                        else if (board[r][c].getOccupant().getPieceType() == PieceType.ROOK)
                        {
                            System.out.print("|  BR  |");
                        }

                        else if (board[r][c].getOccupant().getPieceType() == PieceType.KNIGHT)
                        {
                            System.out.print("|  BKN  |");
                        }

                        else if (board[r][c].getOccupant().getPieceType() == PieceType.QUEEN)
                        {
                            System.out.print("|  BQ  |");
                        }

                        else
                        {
                            System.out.print("|  BK  |");
                        }
                    }

                }

                else
                {
                    System.out.print("|     |");
                }
            }
            System.out.println();
            System.out.println("---------------------------------------------------------------------");
        }
    }

}
