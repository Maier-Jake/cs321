package mapgenerator;

/**
 *
 * @author CS 321 Team 1
 */
public class Monster
{
    private int monsterIndex;
    public final Coordinates coords;
    private String name;
    
    public Monster(int newXCoord, int newYCoord, int newMonsterIndex, String name)
    {
        coords = new Coordinates (newXCoord, newYCoord);
        monsterIndex = newMonsterIndex;
        this.name = name;
    }
    public Monster(Coordinates c, int monsterIndex, String name)
    {
        this.monsterIndex = monsterIndex;
        coords = c;
        this.name = name;
    }
    public Monster copyMonster()
    {
        Monster returnMonster = new Monster(new Coordinates(coords.getX(),coords.getY()), monsterIndex, name);
        return returnMonster;
    }
    
    public int getMonsterIndex()
    {
        return monsterIndex;
    }
    
    public void setMonsterIndex(int newMonsterIndex)
    {
        monsterIndex = newMonsterIndex;
    }
    public String getName()
    {
        return name;
    }
}
