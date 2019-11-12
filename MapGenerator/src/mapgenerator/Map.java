package mapgenerator;

import java.util.ArrayList;

/**
 *
 * @author CS 321 Team 1
 */
public class Map 
{
    private ArrayList<MapTile> mapTileList;
    private int numCols;
    private int numRows;
    private int biomeIndex;
    
    private void generateMapTileList()
    {
        mapTileList = new ArrayList<>(numCols * numRows);
        
        for(int i = 0; i < numCols * numRows; i++)
        {
            int x = i % numRows;
            int y = i / numRows;
            int terrainIndex = 0;
            MapTile newMapTile = new MapTile(x, y, terrainIndex);
            mapTileList.add(newMapTile);
        }
    }
    
    public Map(int newNumCols, int newNumRows, int newBiomeIndex)
    {
        numCols = newNumCols;
        numRows = newNumRows;
        biomeIndex = newBiomeIndex;
        generateMapTileList();
    }
    
    public void generateNewMap(int newNumCols, int newNumRows, int newBiomeIndex)
    {
        numCols = newNumCols;
        numRows = newNumRows;
        biomeIndex = newBiomeIndex;
        mapTileList.clear();
        generateMapTileList();
    }
    
    public ArrayList<MapTile> getMapTiles()
    {
        ArrayList<MapTile> returnList = new ArrayList<>(numCols * numRows);
        
        for(int i = 0; i < numCols * numRows; i++)
        {
            MapTile newMapTile = mapTileList.get(i).copyMapTile();
            returnList.add(newMapTile);
        }
        
        return returnList;
    }
    
    public int getNumCols()
    {
        return numCols;
    }
    
    public int getNumRows()
    {
        return numRows;
    }
    
    public int getBiomeIndex()
    {
        return biomeIndex;
    }
    
    public void setNumCols(int newNumCols)
    {
        numCols = newNumCols;
    }
    
    public void setNumRows(int newNumRows)
    {
        numRows = newNumRows;
    }
    
    public void setBiomeIndex(int newBiomeIndex)
    {
        biomeIndex = newBiomeIndex;
    }
}
