package mapgenerator;

/**
 *
 * @author CS 321 Team 1
 */
public class MapTile extends MapCoordinates
{
    private int terrainIndex;
    
    public MapTile(int newXCoord, int newYCoord, int newTerrainIndex)
    {
        xCoord = newXCoord;
        yCoord = newYCoord;
        terrainIndex = newTerrainIndex;
    }
    
    public MapTile copyMapTile()
    {
        MapTile returnTile = new MapTile(xCoord, yCoord, terrainIndex);
        return returnTile;
    }
    
    public int getTerrainIndex()
    {
        return terrainIndex;
    }
    
    public void setTerrainIndex(int newTerrainIndex)
    {
        terrainIndex = newTerrainIndex;
    }
}
