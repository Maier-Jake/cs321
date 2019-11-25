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
    java.util.Random rand = new java.util.Random(); // random number generator base function
    
    private double meanTile = 0.0;       // the average tile type 
    private double terrainClutter = 0.3; // the amount of clutter the terrain has
    
    private void generateMapTileList()
    {
        mapTileList = new ArrayList<>(numCols * numRows);
        int j = 0;
        for(int i = 0; i < numCols * numRows; i++)
        {
            int x = i % numRows;
            int y = i / numRows;
            int terrainIndex = 0;
            
            // use gaussian noise generator for open biomes
            double noise = rand.nextGaussian() * Math.sqrt(terrainClutter) + meanTile;
            noise = Math.abs(noise);
            if (noise < 0.5) { terrainIndex = 0; }
            if (noise > 0.5 && noise < 1.0) { terrainIndex = 1; }
            if (noise > 1.0 && noise < 1.5) {terrainIndex = 2; }
            System.out.print(terrainIndex);
            j = j + 1;
            if (j >= numCols) { 
                System.out.print('\n');
                j = 0; 
            }
            MapTile newMapTile = new MapTile(x, y, terrainIndex);
            // mapTileList.add(newMapTile);
        }
        if (biomeIndex == 2) 
        {
            double doubleCols = (double)numCols; // cast variables to doubles
            double doubleRows = (double)numRows;

            int curX = 0; // current index in the data arrays
            int curY = 0;

            int[][] starts = new int[numCols*numRows][2]; // build data arrays
            int[][] ends = new int[numCols*numRows][2];

            int startCol = (int)Math.round(rand.nextDouble()*doubleCols); // create starting point
            int startRow = (int)Math.round(rand.nextDouble()*doubleRows);

            starts[curX][0] = startCol; // fill starting point in data arrays
            starts[curX][1] = startRow;

            int col = startCol;
            int row = startRow;
            // begin loop
            while(curX < (numCols*numRows)-2){              // loop through data arrays
                curX++; // increment x value

                double dir = rand.nextDouble() * 4.0;       // choose direction
                if (dir < 1.0) {col = col + 1;}                // North
                if (dir >= 1.0 && dir < 2.0) {col = col - 1;}  // South
                if (dir >= 2.0 && dir < 3.0) {row = row + 1;}  // East
                if (dir >= 3.0) {row = row - 1;}               // West

                ends[curX][0] = col;       // fill current ends with the new coordinate
                ends[curX][1] = row;

                starts[curX+1][0] = col;   // fill next start with current coordinate
                starts[curX+1][1] = row;
            }
        }
    }
    
    public Map(int newNumCols, int newNumRows, int newBiomeIndex/*, double mean, double clutter*/)
    {
        numCols = newNumCols;
        numRows = newNumRows;
        biomeIndex = newBiomeIndex;
        mapTileList = new ArrayList<ArrayList<MapTile>>();
        for (int y = 0; y < numRows; y++)
        {
            mapTileList.add(new ArrayList<MapTile>());
            
            for (int x = 0; x < numCols; x++)
            {
                mapTileList.get(y).add(new MapTile(x,y,0));
            }
        }
        // meanTile = mean;
        // terrainClutter = clutter;
        // generateMapTileList();
    }
    
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
    
    public double getTerrainClutter() 
    {
        return terrainClutter;
    }
    
    public void setTerrainClutter(double newTerrainClutter) 
    {
        terrainClutter = newTerrainClutter;
    }
    
    public double getTerrainAverage() 
    {
        return meanTile;
    }
    
    public void setTerrainAverage(double newMeanTile) 
    {
        meanTile = newMeanTile;
    }

    // Assumes that numcols and numrows are the same for current and new maps
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
