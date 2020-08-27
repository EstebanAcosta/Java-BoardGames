package TicTacToe;
/***
 * 
 * @author estebanacosta
 *
 */
public class Player
{
    private String name;

    private int playerId;

    private String value;

    public Player(int id)
    {
        setPlayerId(id);
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getPlayerId()
    {
        return playerId;
    }

    public void setPlayerId(int playerId)
    {
        this.playerId = playerId;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    public String getWhichValue()
    {
        return this.value;
    }

}
