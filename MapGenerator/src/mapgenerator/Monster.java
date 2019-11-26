package mapgenerator;

/**
 *
 * @author CS 321 Team 1
 */
public class Monster
{
    private Coordinates coords;
    private String name;
    private float CR;
    
    public Monster(int newXCoord, int newYCoord, String name, float newCR)
    {
        coords = new Coordinates (newXCoord, newYCoord);
        this.name = name;
        CR = newCR;
    }
    
    public Monster(Coordinates c, String name, float newCR)
    {
        coords = c;
        this.name = name;
        CR = newCR;
    }
    
    public Monster copyMonster()
    {
        Monster returnMonster = new Monster(new Coordinates(coords.getX(),coords.getY()), name, CR);
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
    
    public int getX()
    {
        return coords.getX();
    }
    
    public int getY()
    {
        return coords.getY();
    }
}
