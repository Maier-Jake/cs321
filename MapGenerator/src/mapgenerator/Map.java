package mapgenerator;

import static java.lang.Math.floor;
import java.util.ArrayList;

/**
 * This class holds the map and falls into the model.
 * 
 * @author CS 321 Team 1
 */
public class Map 
{
    private ArrayList<ArrayList<MapTile>> mapTileList;
    
    private int numCols;
    private int numRows;
    private int biomeIndex;
    java.util.Random rand = new java.util.Random(); // random number generator base function
    
    private double meanTile = 1.4;       // the average tile type 
    private double terrainClutter = 0.3; // the amount of clutter the terrain has
    
    /**
     * This is where the map is generated. This should perhaps be in the controller 
     * instead of here, but functionally this made it much easier.
     */
    private void generateMapTileList()
    {
        if (biomeIndex != 1) {
            for(int y = 0; y < numRows; y++)
            {
                for(int x = 0; x < numCols; x++)
                {
                    int terrainIndex = 0;
                    // use gaussian noise generator for open biomes
                    double noise = rand.nextGaussian() * Math.sqrt(terrainClutter) + meanTile;
                    noise = Math.abs(noise);
                    if (noise < 1.0) { terrainIndex = 0; }
                    if (noise > 1.0 && noise < 2.0) { terrainIndex = 1; }
                    if (noise > 2.0 && noise < 3.0) {terrainIndex = 2; }

                    //MapTile newMapTile = new MapTile(x, y, terrainIndex);
                    mapTileList.get(y).get(x).setTerrainIndex(terrainIndex);
                }
            }
        }
        if(biomeIndex == 1)
        {
            for(int y = 0; y < numRows; y++)
            {
                for(int x = 0; x < numCols; x++)
                {
                    mapTileList.get(y).get(x).setTerrainIndex(0);
                }
            }
            
            int yLower = (int) (2 + floor(Math.random() * numRows / 5));
            int yUpper = (int) (numRows - 2 - floor(Math.random() * numRows / 5));
            int xLower = (int) (2 + floor(Math.random() * numCols / 5));
            int xUpper = (int) (numCols - 2 - floor(Math.random() * numCols / 5));
            
            for(int y = yLower; y < yUpper; y++)
            {
                for(int x = xLower; x < xUpper; x++)
                {
                    mapTileList.get(y).get(x).setTerrainIndex(1);
                }
            }
            
            for(int y = 0; y < yLower; y++)
            {
                mapTileList.get(y).get(xLower).setTerrainIndex(1);
            }
            
            for(int x = xUpper; x < numCols; x++)
            {
                mapTileList.get(yUpper - 1).get(x).setTerrainIndex(1);
            }
        }
    }
    
    /**
     *
     * @param newNumCols
     * @param newNumRows
     * @param newBiomeIndex
     */
    public Map(int newNumCols, int newNumRows, int newBiomeIndex/*, double mean, double clutter*/)
    {
        numCols = newNumCols;
        numRows = newNumRows;
        biomeIndex = newBiomeIndex;
        mapTileList = new ArrayList<ArrayList<MapTile>>();
        for (int y = 0; y < newNumRows; y++)
        {
            mapTileList.add(new ArrayList<MapTile>());
            
            for (int x = 0; x < newNumCols; x++)
            {
                mapTileList.get(y).add(new MapTile(x,y,1));
            }
        }
        generateMapTileList();
    }
    
    /**
     *
     * @param newNumCols
     * @param newNumRows
     * @param newBiomeIndex
     * @param mean
     * @param clutter
     */
    public void generateNewMap(int newNumCols, int newNumRows, int newBiomeIndex, double mean, double clutter)
    {
        numCols = newNumCols;
        numRows = newNumRows;
        biomeIndex = newBiomeIndex;
        mapTileList.clear();
        meanTile = mean;
        terrainClutter = clutter;
        generateMapTileList();
    }
    
    /**
     *
     * @return
     */
    public ArrayList<ArrayList<MapTile>> getMapTiles()
    {
        ArrayList<ArrayList<MapTile>> returnList = new ArrayList<ArrayList<MapTile>>();
        
        for (int y = 0; y < numRows; y++)
        {
            returnList.add(new ArrayList<MapTile>());
            
            for (int x = 0; x < numCols; x++)
            {
                MapTile newMapTile = mapTileList.get(y).get(x);
                returnList.get(y).add(newMapTile);
            }
        }
        
        return returnList;
    }
    
    /**
     *
     * @return
     */
    public int getNumCols()
    {
        return numCols;
    }
    
    /**
     *
     * @return
     */
    public int getNumRows()
    {
        return numRows;
    }
    
    /**
     *
     * @return
     */
    public int getBiomeIndex()
    {
        return biomeIndex;
    }
    
    /**
     *
     * @param newNumCols
     */
    public void setNumCols(int newNumCols)
    {
        numCols = newNumCols;
    }
    
    /**
     *
     * @param newNumRows
     */
    public void setNumRows(int newNumRows)
    {
        numRows = newNumRows;
    }
    
    /**
     *
     * @param newBiomeIndex
     */
    public void setBiomeIndex(int newBiomeIndex)
    {
        biomeIndex = newBiomeIndex;
    }
    
    /**
     *
     * @return
     */
    public double getTerrainClutter() 
    {
        return terrainClutter;
    }
    
    /**
     *
     * @param newTerrainClutter
     */
    public void setTerrainClutter(double newTerrainClutter) 
    {
        terrainClutter = newTerrainClutter;
    }
    
    /**
     *
     * @return
     */
    public double getTerrainAverage() 
    {
        return meanTile;
    }
    
    /**
     *
     * @param newMeanTile
     */
    public void setTerrainAverage(double newMeanTile) 
    {
        meanTile = newMeanTile;
    }

    // Assumes that numcols and numrows are the same for current and new maps

    /**
     *
     * @param newMap
     */
    public void setMap(ArrayList<ArrayList<MapTile>> newMap)
    {
        mapTileList.clear();
        
        for (int y = 0; y < numRows; y++)
        {
            mapTileList.add(new ArrayList<>());
            
            for (int x = 0; x < numCols; x++)
            {
                MapTile newMapTile = newMap.get(y).get(x);
                mapTileList.get(y).add(newMapTile);
            }
        }
    }
    
    /**
     *
     */
    public void printMap()
    {
        System.out.println();
        for (int y = 0; y < numRows; y++)
        { 
            for (int x = 0; x < numCols; x++)
            {
                System.out.print(mapTileList.get(y).get(x).getTerrainIndex());
            }
            System.out.println();
        }
    }
}
