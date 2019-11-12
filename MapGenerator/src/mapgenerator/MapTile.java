package mapgenerator;

/**
 *
 * @author CS 321 Team 1
 */
public class MapTile implements MapCoordinates
{
    private int xCoord;
    private int yCoord;
    private int terrainIndex;
    
    public MapTile(int newXCoord, int newYCoord, int newTerrainIndex)
    {
        xCoord = newXCoord;
        yCoord = newYCoord;
        terrainIndex = newTerrainIndex;
    }
    
    @Override
    public MapTile clone()
    {
        MapTile returnTile = new MapTile(xCoord, yCoord, terrainIndex);
        return returnTile;
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
    
    public int getTerrainIndex()
    {
        return terrainIndex;
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
    
    public void setTerrainIndex(int newTerrainIndex)
    {
        terrainIndex = newTerrainIndex;
    }
}
