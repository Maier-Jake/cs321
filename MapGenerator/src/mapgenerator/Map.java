package mapgenerator;

import java.util.ArrayList;

/**
 *
 * @author CS 321 Team 1
 */
public class Map 
{
    private ArrayList<ArrayList<MapTile>> mapTileList;
    
    private int numCols;
    private int numRows;
    private int biomeIndex;
    
    private void generateMapTileList()
    {
        // List<List<String>> listOfLists = new ArrayList<List<String>>(size); 
        mapTileList = new ArrayList<ArrayList<MapTile>>(); 
        for(int i = 0; i < numCols; i++)  {
            mapTileList.add(new ArrayList<MapTile>());
        }
        for (int x = 0; x < numCols; x++)
        { for (int y = 0; y < numRows; y++)
            {
                int terrainIndex = 0;
                MapTile newMapTile = new MapTile(x, y, terrainIndex);
                mapTileList.get(x).add(newMapTile);
            }
        }
    }
    
    public Map(int newNumCols, int newNumRows, int newBiomeIndex)
    {
        numCols = newNumCols;
        numRows = newNumRows;
        biomeIndex = newBiomeIndex;
        generateMapTileList();
    }
    
    //I dont understand this usecase scenario -- Jake
    public void generateNewMap(int newNumCols, int newNumRows, int newBiomeIndex)
    {
        numCols = newNumCols;
        numRows = newNumRows;
        biomeIndex = newBiomeIndex;
        mapTileList.clear();
        generateMapTileList();
    }
    
    public ArrayList<ArrayList<MapTile>> getMapTiles()
    {
        ArrayList<ArrayList<MapTile>> returnList = new ArrayList<ArrayList<MapTile>>(); 
        
        for (int x = 0; x < numCols; x++)
        { for (int y = 0; y < numRows; y++)
            {
                MapTile newMapTile = mapTileList.get(x).get(y);
                returnList.get(x).add(newMapTile);
            }
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
    public void setMap(ArrayList<ArrayList<MapTile>> newMap)
    {
        mapTileList = newMap;
    }
    public void printMap()
    {
        System.out.println();
        for (int x = 0; x < numCols; x++)
        { for (int y = 0; y < numRows; y++)
            {
                System.out.print(mapTileList.get(x).get(y).getTerrainIndex());
            }
        System.out.println();
        }
    }
}
