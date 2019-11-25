package mapgenerator;

/**
 *
 * @author CS 321 Team 1
 */
public class Monster
{
    private int index;
    private final Coordinates coords;
    private String name;
    
    public Monster(int newXCoord, int newYCoord, int newMonsterIndex, String name)
    {
        coords = new Coordinates (newXCoord, newYCoord);
        index = newMonsterIndex;
        this.name = name;
    }
    public Monster(Coordinates c, int monsterIndex, String name)
    {
        this.index = monsterIndex;
        coords = c;
        this.name = name;
    }
    public Monster copyMonster()
    {
        Monster returnMonster = new Monster(new Coordinates(coords.getX(),coords.getY()), index, name);
        return returnMonster;
    }
    
    public int getMonsterIndex()
    {
        return index;
    }
    
    public void setMonsterIndex(int newMonsterIndex)
    {
        index = newMonsterIndex;
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
