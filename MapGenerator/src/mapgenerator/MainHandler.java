package mapgenerator;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.io.File;
import java.awt.event.*;
import static java.lang.Math.floor;

/**
 *
 * @author CS 321 Team 1
 */
public class MainHandler 
{
    
    private static Scenario activeScenario;
    private static InitialView initialView;
    private static MainView mainView;
    private static ArrayList<Monster> availableMonsters;
    
    public static void main(String[] args) throws InterruptedException 
    {
        availableMonsters = new ArrayList<>();
        availableMonsters.add(new Monster(0, 0, "Orc", 1));
        availableMonsters.add(new Monster(0, 0, "Skeleton", (float) 0.5));
        availableMonsters.add(new Monster(0, 0, "Thug", (float) 0.25));
        availableMonsters.add(new Monster(0, 0, "Bear", 2));
        availableMonsters.add(new Monster(0, 0, "Dragon", 8));
        
        initialView = new InitialView();
        
        initialView.getNewMapButton().addActionListener(new ActionListener() 
        {        
            public void actionPerformed(ActionEvent event) 
            {
                initialView.getFrame().dispose();
                Map newMap = new Map(30, 20, Macros.FORESTINDEX);
                ArrayList<Monster> newMonsters = generateMonsterList(newMap, null, 15);
                ArrayList<LootPile> newLoots = generateLootPileList(newMap, newMonsters, 20, 5);
                activeScenario = new Scenario(newMap, newMonsters, newLoots);
                // activeScenario.setMonsterList(generateMonsterList(15));///////////////////////////////
                // activeScenario.setLootPileList(generateLootPileList(20,5));
                mainView = new MainView(activeScenario, true);
            }
        });
        
        initialView.getLoadMapButton().addActionListener(new ActionListener() 
        {        
            public void actionPerformed(ActionEvent event) 
            {
                initialView.getFrame().dispose();
                activeScenario = loadScenario();
                if(activeScenario == null)
                {
                    return;
                }
                System.out.println("Scenario Loaded In From File");
                mainView = new MainView(activeScenario, true);

            }
        });
    }
    /*
    private static void initializeMainView(Scenario scenario)
    {
        
        mainView.getLootButton().addActionListener(new ActionListener() 
        {        
            public void actionPerformed(ActionEvent event) 
            {
                lootView.showLootView();
            }
        });
        mainView.getMonsterButton().addActionListener(new ActionListener() 
        {        
            public void actionPerformed(ActionEvent event) 
            {
                monsterView.showMonsterView();
            }
        });
        mainView.getSaveButton().addActionListener((ActionEvent event) -> {
            exportScenario();
        });
        
    }
    */
    public static ArrayList<Monster> generateMonsterList(Map newMap, ArrayList<LootPile> lootList, int CR)
    {
        ArrayList<Monster> monsterList = new ArrayList<>();
        ArrayList<Monster> correctCRList = new ArrayList<>();
        ArrayList<MapTile> availableTiles = new ArrayList<>();
        
        //create a list of all available tiles
        for(int y = 0; y < newMap.getMapTiles().size(); ++y)
        {
            for(int x = 0; x < newMap.getMapTiles().get(y).size(); ++x)
            {
                if(newMap.getMapTiles().get(y).get(x).getTerrainIndex() == 1)
                {
                    if(lootList != null && !lootList.isEmpty())
                    {
                        for(int i = 0; i < lootList.size(); ++i)
                        {
                            if(!(lootList.get(i).getCoords().getX() == x 
                                    && lootList.get(i).getCoords().getX() == y) )
                            {
                                availableTiles.add(newMap.getMapTiles().get(y).get(x).copyMapTile());
                            }
                        }
                    }
                    else
                    {
                        availableTiles.add(newMap.getMapTiles().get(y).get(x).copyMapTile());
                    }
                }
            }
        }
        
        boolean keepRunning = true;
        float workingCR = CR;
        while(keepRunning)
        {
            for(int i = 0; i < availableMonsters.size(); ++i)
            {
                if(availableMonsters.get(i).getCR() < workingCR)
                {
                    correctCRList.add(availableMonsters.get(i));
                }
            }
            if(correctCRList.size() != 0 && availableTiles.size() > 1)
            {
                int monsterIndex = (int) floor(Math.random() * correctCRList.size());
                Monster newMonster = correctCRList.get(monsterIndex).copyMonster();
                int tileIndex = (int)floor(Math.random() * (availableTiles.size() - 1));
                newMonster.setCoords(availableTiles.get(tileIndex).getCoords().getX(), 
                        availableTiles.get(tileIndex).getCoords().getY());
                availableTiles.remove(tileIndex);
                monsterList.add(newMonster);
                workingCR = workingCR - newMonster.getCR();
                correctCRList.clear();
            }
            else
            {
                keepRunning = false;
            }
        }
        
        
        return monsterList;
    }
    
    public static ArrayList<LootPile> generateLootPileList(Map newMap, ArrayList<Monster> monsterList, int GP, int items)
    {
        ArrayList<LootPile> lootPileList = new ArrayList<>();
        ArrayList<MapTile> availableTiles = new ArrayList<>();
        
        for(int y = 0; y < newMap.getMapTiles().size(); ++y)
        {
            for(int x = 0; x < newMap.getMapTiles().get(y).size(); ++x)
            {
                if(newMap.getMapTiles().get(y).get(x).getTerrainIndex() == 1)
                {
                    if(monsterList != null && monsterList.size() != 0)
                    {
                        for(int i = 0; i < monsterList.size(); ++i)
                        {
                            if(!(monsterList.get(i).getCoords().getX() == x 
                                    && monsterList.get(i).getCoords().getX() == y) )
                            {
                                availableTiles.add(newMap.getMapTiles().get(y).get(x).copyMapTile());
                            }
                        }
                    }
                    else
                    {
                        availableTiles.add(newMap.getMapTiles().get(y).get(x).copyMapTile());
                    }
                }
            }
        }
        
        boolean keepRunning = true;
        int workingGP = GP;
        int workingItems = items;
        while(keepRunning)
        {
            if(availableTiles.size() > 1 && (workingGP > 0 || workingItems > 0))
            {
                int newGP = 0;
                if(workingGP > 5)
                {
                    newGP = (int)(Math.random() * (workingGP - 5)) + 5;
                }
                else
                {
                    newGP = workingGP;
                }
                
                workingGP = workingGP - newGP;
                
                int newItems = 0;
                if(workingItems > 2)
                {
                    newItems = (int)(Math.random() * (workingItems - 2)) + 2;
                }
                else
                {
                    newItems = workingItems;
                }
                
                workingItems = workingItems - newItems;
                int tileIndex = (int)floor(Math.random() * (availableTiles.size() - 1));
                availableTiles.remove(tileIndex);
                
                LootPile newLootPile = new LootPile(availableTiles.get(tileIndex).getCoords().getX(), 
                        availableTiles.get(tileIndex).getCoords().getY(), newGP, newItems);
                lootPileList.add(newLootPile);
            }
            else
            {
                keepRunning = false;
            }
        }
        
        return lootPileList;
    }
    
    /**
     * loadScenario is used to read in a .scenario file
     * line by line to generate a new Scenario file to
     * be used by the program
     */
    private static Scenario loadScenario(/*Scanner s*/) 
    {
        JFrame frame = new JFrame();
        Scanner infile = null;
        
        Scenario newScenario = null;
        
        final JFileChooser fileChooser = new JFileChooser();
        File workingDirectory = new File(System.getProperty("user.dir"));
        fileChooser.setCurrentDirectory(workingDirectory);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("ScenarioFiles", "Scenario");
        fileChooser.setFileFilter(filter);
        
        int returnVal = fileChooser.showOpenDialog(frame);
        if(returnVal == JFileChooser.APPROVE_OPTION) 
        {
            //System.out.println("You chose to open this file: " + fileChooser.getSelectedFile().getName());
            try {
                infile = new Scanner(fileChooser.getSelectedFile());
            } catch (FileNotFoundException ex) {
                System.err.println("The file could not be opened.");
                System.out.println("Exiting program");
                return newScenario;
            }   
        } 
        else 
        {
            System.out.println("Failed to load in file, closed before .scenario file selected!");
            infile = new Scanner(System.in);
        }
        
        String line, token;
        int startI, endI;
        
        //read first line and get width of map.
        int width;
        line = Macros.readLine(infile);
        if(line.equals("nullLine")) return null;
        startI = line.indexOf(": ")+2;
        token = line.substring(startI);
        width = Macros.readInt(token);
        //read second line and get length of map.
        int length;
        line = Macros.readLine(infile);
        if(line.equals("nullLine")) return null;
        startI = line.indexOf(": ")+2;
        token = line.substring(startI);
        length = Macros.readInt(token);
        
        //read third line and get biome of the map
        String biome;
        int biomeIndex;
        line = Macros.readLine(infile);
        if(line.equals("nullLine")) return null;
        startI = line.indexOf(": ")+2;
        token = line.substring(startI);
        biome = token;
        biomeIndex = Macros.getBiomeIndex(biome);
        if(biomeIndex == -1) return null;
        
        //create new 2D array of tiles
        ArrayList<ArrayList<MapTile>> aMap = new ArrayList<ArrayList<MapTile>>();
        
        for(int i = 0; i < length; i++)  
        {
            aMap.add(new ArrayList<MapTile>());
        }
        //read the .txt file and load into 2D array
        for (int y = 0; y < length; y++)
        { 
            for (int x = 0; x < width; x++)
            {
                int terrainIndex = Integer.parseInt(Macros.readChar(infile));
                MapTile newMapTile = new MapTile(x, y, terrainIndex);
                aMap.get(y).add(newMapTile);
            }
            infile.nextLine();
        }
        
        //create new Map using this knowledge.
        Map newMap = new Map(width, length, biomeIndex);
        newMap.setMap(aMap);
        //newMap.printMap();

        //skip Entities: line
        infile.nextLine();
        
        //Create arraylists of monsters and lootpiles
        ArrayList<Monster> monsterList = new ArrayList<Monster>();
        ArrayList<LootPile> lootList = new ArrayList<LootPile>();
        
        while(infile.hasNextLine())
        {
            line = Macros.readLine(infile);
            //get coordinates
            line = line.substring(line.indexOf("("));
            startI = line.indexOf("(")+1;
            endI = line.indexOf(",");
            int x = Integer.parseInt(line.substring(startI,endI));
            startI = line.indexOf(",")+1;
            endI = line.indexOf(")");
            int y = Integer.parseInt(line.substring(startI,endI));
            Coordinates coords = new Coordinates(x, y);
            
            //get name & or info
            line = line.substring(endI);
            if (line.contains("Monster:"))
            {
                line = line.substring(line.indexOf("\""));
                startI = line.indexOf("\"")+1;
                endI = line.indexOf(",");
                String name = line.substring(startI,endI);
                line = line.substring(endI);
                startI = line.indexOf(",")+1;
                endI = line.indexOf("\"");
                float challengeRating = Float.parseFloat(line.substring(startI,endI));
                
                Monster newMonster = new Monster(coords, name, challengeRating);
                monsterList.add(newMonster);
            }
            else if(line.contains("LootPile:"))
            {
                line = line.substring(line.indexOf("\"")+1);
                int numItems = Integer.parseInt(line.substring(0,line.indexOf(",")));
                line = line.substring(line.indexOf(",")+1);
                int numGP = Integer.parseInt(line.substring(0,line.indexOf("\"")));
                LootPile newLoot = new LootPile(coords, numGP, numItems);
                lootList.add(newLoot);
            }
        }
        return new Scenario(newMap, monsterList, lootList);
    }
    
    
    public static void exportScenario()
    {
        String fileName = "";
        JFrame frame = new JFrame();
        
        final JFileChooser fileChooser = new JFileChooser();
        File workingDirectory = new File(System.getProperty("user.dir"));
        fileChooser.setCurrentDirectory(workingDirectory);
        fileChooser.setDialogTitle("Specify a file to save");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("ScenarioFiles", "Scenario");
        fileChooser.setFileFilter(filter);
        
        if(fileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) 
        {
            fileName = fileChooser.getSelectedFile().getPath();
        } 
        else 
        {
            System.out.println("Failed to load in file, closed before .scenario file selected!");
        }
        
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            //Width: width
            //Length: length
            writer.write("Width: " + activeScenario.getMap().getNumCols() + "\n");
            writer.write("Length: " + activeScenario.getMap().getNumRows() + "\n");
            //Biome: biomeName
            int biomeIndex = activeScenario.getMap().getBiomeIndex();
            String biomeName = Macros.convertIndexToBiome(biomeIndex);
            writer.write("Biome: " + biomeName + "\n");
            //MapPrintout
            for (int y = 0; y < activeScenario.getMap().getNumRows(); y++)
            { 
                for (int x = 0; x < activeScenario.getMap().getNumCols(); x++)
                {
                    int number = activeScenario.getMap().getMapTiles().get(y).get(x).getTerrainIndex();
                    String n = Integer.toString(number);
                    writer.write(n);
                }
                writer.write("\n");
            }
            //Entities line:
            writer.write("Entities:\n");
            //List of entities
            for(Monster monster : activeScenario.getMonsterList()) 
            {
                //write coordinates
                writer.write("("+monster.getX()+","+monster.getY()+") ");
                //write "monster"
                writer.write("Monster: \"" + monster.getName() +","+ monster.getCR() + "\"");
                //endline
                writer.write("\n");
            }
            for(LootPile loot : activeScenario.getLootPileList()) 
            {
                //write coordinates
                writer.write("("+loot.getX()+","+loot.getY()+") ");
                //write "loot"
                writer.write("LootPile: \"" + loot.getItemCount() + "," + loot.getGoldPieces() + "\"");
                //endline
                writer.write("\n");
            }
            
            
            writer.close();
            
        } catch(IOException ex)
        {
            System.err.format("IOException: %s%n", ex);
        }
    }
}
