package mapgenerator;

/**
 *
 * @author CS 321 Team 1
 */
public class Monster implements MapCoordinates
{
    private int xCoord;
    private int yCoord;
    private int monsterIndex;
    
    public Monster(int newXCoord, int newYCoord, int newMonsterIndex)
    {
        xCoord = newXCoord;
        yCoord = newYCoord;
        monsterIndex = newMonsterIndex;
    }
            
    @Override
    public int getXCoord()
    {
        return xCoord;
    }
    
    @Override
    public int getYCoord()
    {
        return yCoord;
    }
    
    public int getMonsterIndex()
    {
        return monsterIndex;
    }
    
    @Override
    public void setXCoord(int newX)
    {
        xCoord = newX;
    }
    
    @Override
    public void setYCoord(int newY)
    {
        yCoord = newY;
    }
    
    public void setMonsterIndex(int newMonsterIndex)
    {
        monsterIndex = newMonsterIndex;
    }
}
