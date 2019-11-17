package mapgenerator;

/**
 *
 * @author CS 321 Team 1
 */
public class MapTile
{
    private int terrainIndex;
    public final Coordinates coords;
    
    public MapTile(int newXCoord, int newYCoord, int newTerrainIndex)
    {
        coords = new Coordinates (newXCoord, newYCoord);
        terrainIndex = newTerrainIndex;
    }
    public MapTile(Coordinates c, int terrainType)
    {
        coords = c;
        terrainIndex = terrainType;
    }
    
    public MapTile copyMapTile()
    {
        MapTile returnTile = new MapTile(new Coordinates(coords.getX(),coords.getY()), terrainIndex);
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
