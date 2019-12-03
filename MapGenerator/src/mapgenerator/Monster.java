package mapgenerator;

/**
 * This class represents the info of a single monster and is used as a part
 * of the model.
 * @author CS 321 Team 1
 */
public class Monster
{
    private Coordinates coords;
    private String name;
    private float CR;
    
    /**
     *
     * @param newXCoord
     * @param newYCoord
     * @param name
     * @param newCR
     */
    public Monster(int newXCoord, int newYCoord, String name, float newCR)
    {
        coords = new Coordinates (newXCoord, newYCoord);
        this.name = name;
        CR = newCR;
    }
    
    /**
     *
     * @param c
     * @param name
     * @param newCR
     */
    public Monster(Coordinates c, String name, float newCR)
    {
        coords = c;
        this.name = name;
        CR = newCR;
    }
    
    /**
     *
     * @return
     */
    public Monster copyMonster()
    {
        Monster returnMonster = new Monster(new Coordinates(coords.getX(),coords.getY()), name, CR);
        return returnMonster;
    }
    
    /**
     *
     * @return
     */
    public Coordinates getCoords()
    {
        return coords;
    }
    
    /**
     *
     * @param x
     * @param y
     */
    public void setCoords(int x, int y)
    {
        coords = new Coordinates(x, y);
    }
    
    /**
     *
     * @return
     */
    public float getCR()
    {
        return CR;
    }
    
    /**
     *
     * @param newCR
     */
    public void setCR(float newCR)
    {
        CR = newCR;
    }
    
    /**
     *
     * @return
     */
    public String getName()
    {
        return name;
    }
    
    /**
     *
     * @return
     */
    public int getX()
    {
        return coords.getX();
    }
    
    /**
     *
     * @return
     */
    public int getY()
    {
        return coords.getY();
    }
}
