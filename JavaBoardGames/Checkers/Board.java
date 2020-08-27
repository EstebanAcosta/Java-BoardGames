package JavaBoardGames.Checkers;

import java.util.ArrayList;

/***
 * @author estebanacosta
 */

public class Board
{
    private Tile[][] board;

    private Board parent;

    private ArrayList<Board> children = new ArrayList<Board>();

    private Player red;

    private Player black;

    private boolean blackTurn;

    private boolean redTurn;

    public Board(Tile[][] board, Player black, Player red, boolean blackTurn, boolean redTurn)
    {
        setTileBoard(board);
        setBlackPlayer(black);
        setRedPlayer(red);
        setBlackTurn(blackTurn);
        setRedTurn(redTurn);
    }

    public void setTileBoard(Tile[][] board)
    {
        this.board = board;
    }

    public Tile[][] getTileBoard()
    {
        return this.board;
    }

    public void setNextBoard(Board child)
    {
        children.add(child);
    }

    public ArrayList<Board> getChildren()
    {
        return this.children;
    }

    public void setLastBoard(Board parent)
    {
        this.parent = parent;
    }

    public Board getLastBoard()
    {
        return this.parent;
    }

    public void setRedPlayer(Player red)
    {
        this.red = red;
    }

    public Player getRedPlayer()
    {
        return this.red;
    }

    public void setBlackPlayer(Player black)
    {
        this.black = black;
    }

    public Player getBlackPlayer()
    {
        return this.black;
    }

    public void setBlackTurn(boolean blackTurn)
    {
        this.blackTurn = blackTurn;
    }

    public void setRedTurn(boolean redTurn)
    {
        this.redTurn = redTurn;
    }

    public boolean isBlackTurn()
    {
        return this.blackTurn;
    }

    public boolean isRedTurn()
    {
        return this.redTurn;
    }

}
