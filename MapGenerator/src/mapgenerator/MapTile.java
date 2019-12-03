package mapgenerator;

/**
 * This class represents a single tile in the map and is used as a part
 * of the model.
 * @author CS 321 Team 1
 */
public class MapTile
{
    private int terrainIndex;

    /**
     *
     */
    public final Coordinates coords;
    
    /**
     *
     * @param newXCoord
     * @param newYCoord
     * @param newTerrainIndex
     */
    public MapTile(int newXCoord, int newYCoord, int newTerrainIndex)
    {
        coords = new Coordinates (newXCoord, newYCoord);
        terrainIndex = newTerrainIndex;
    }

    /**
     *
     * @param c
     * @param terrainType
     */
    public MapTile(Coordinates c, int terrainType)
    {
        coords = c;
        terrainIndex = terrainType;
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
     * @return
     */
    public MapTile copyMapTile()
    {
        MapTile returnTile = new MapTile(new Coordinates(coords.getX(),coords.getY()), terrainIndex);
        return returnTile;
    }
    
    /**
     *
     * @return
     */
    public int getTerrainIndex()
    {
        return terrainIndex;
    }
    
    /**
     *
     * @param newTerrainIndex
     */
    public void setTerrainIndex(int newTerrainIndex)
    {
        terrainIndex = newTerrainIndex;
    }
}
