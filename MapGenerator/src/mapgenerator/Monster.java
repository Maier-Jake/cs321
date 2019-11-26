package mapgenerator;

/**
 *
 * @author CS 321 Team 1
 */
public class Monster
{
    private int monsterIndex;
    public Coordinates coords;
    private String name;
    private float CR;
    
    public Monster(int newXCoord, int newYCoord, int newMonsterIndex, String name, float newCR)
    {
        coords = new Coordinates (newXCoord, newYCoord);
        monsterIndex = newMonsterIndex;
        this.name = name;
        CR = newCR;
    }
    
    public Monster(Coordinates c, int monsterIndex, String name, float newCR)
    {
        this.monsterIndex = monsterIndex;
        coords = c;
        this.name = name;
        CR = newCR;
    }
    
    public Monster copyMonster()
    {
        Monster returnMonster = new Monster(new Coordinates(coords.getX(),coords.getY()), monsterIndex, name, CR);
        return returnMonster;
    }
    
    public Coordinates getCoords()
    {
        return coords;
    }
    
    public void setCoords(int x, int y)
    {
        coords = new Coordinates(x, y);
    }
    
    public int getMonsterIndex()
    {
        return monsterIndex;
    }
    
    public void setMonsterIndex(int newMonsterIndex)
    {
        monsterIndex = newMonsterIndex;
    }
    
    public float getCR()
    {
        return CR;
    }
    
    public void setCR(float newCR)
    {
        CR = newCR;
    }
    
    public String getName()
    {
        return name;
    }
}
