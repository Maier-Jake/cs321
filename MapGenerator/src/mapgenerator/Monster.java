package mapgenerator;

/**
 *
 * @author CS 321 Team 1
 */
public class Monster
{
    private final Coordinates coords;
    private String name;
    
    public Monster(int newXCoord, int newYCoord, String name)
    {
        coords = new Coordinates (newXCoord, newYCoord);
        this.name = name;
    }
    public Monster(Coordinates c, String name)
    {
        coords = c;
        this.name = name;
    }
    public Monster copyMonster()
    {
        Monster returnMonster = new Monster(new Coordinates(coords.getX(),coords.getY()), name);
        return returnMonster;
    }
    
    public String getName()
    {
        return name;
    }
    public int getX()
    {
        return coords.getX();
    }
    public int getY()
    {
        return coords.getY();
    }
}
