package game;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jeanpierre
 */
public class Player
{

    private int id;
    private String name;
    private PlayerType type;
    private String symbol;
    private Integer spots[];

    public Player()
    {
        spots = new Integer[9];
    }

    public Player(int id, String name, PlayerType type)
    {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public Player(int id, String name, PlayerType type, String symbol)
    {
        this.id = id;
        this.name = name;
        this.type = type;
        this.symbol = symbol;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public PlayerType getType()
    {
        return type;
    }

    public void setType(PlayerType type)
    {
        this.type = type;
    }

    public String getSymbol()
    {
        return symbol;
    }

    public void setSymbol(String symbol)
    {
        this.symbol = symbol;
    }

    public Integer[] getSpots()
    {
        return spots;
    }

    public void setSpots(Integer[] spots)
    {
        this.spots = spots;
    }

    @Override
    public String toString()
    {
        return "Player{name=" + name + ", symbol=" + symbol + '}';
    }

}
