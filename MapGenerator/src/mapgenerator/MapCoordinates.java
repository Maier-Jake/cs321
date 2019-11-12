package mapgenerator;

/**
 *
 * @author CS 321 Team 1
 */
public abstract class MapCoordinates 
{
    protected int xCoord;
    protected int yCoord;
    
    public int getXCoord()
    {
        return xCoord;
    }
    
    public int getYCoord()
    {
        return yCoord;
    }
    
    public void setXCoord(int newX)
    {
        xCoord = newX;
    }
    
    public void setYCoord(int newY)
    {
        yCoord = newY;
    }
}
