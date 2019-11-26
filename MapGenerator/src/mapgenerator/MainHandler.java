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
        availableMonsters.add(new Monster(0, 0, 0, "Orc", 1));
        availableMonsters.add(new Monster(0, 0, 0, "Skeleton", (float) 0.5));
        availableMonsters.add(new Monster(0, 0, 0, "Thug", (float) 0.25));
        availableMonsters.add(new Monster(0, 0, 0, "Bear", 2));
        
        initialView = new InitialView();
        
        initialView.getNewMapButton().addActionListener(new ActionListener() 
        {        
            public void actionPerformed(ActionEvent event) 
            {
                initialView.getFrame().dispose();
                activeScenario = new Scenario(new Map(30, 40, Macros.FORESTINDEX), 
                        new ArrayList<Monster>(), new ArrayList<LootPile>());
                mainView = new MainView(activeScenario, true);
                System.out.println("Exiting program");
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
                System.out.println("Map Loaded");
                // activeScenario.setMonsterList(generateMonsterList(15));///////////////////////////////
                // activeScenario.setLootPileList(generateLootPileList(20,5));
                mainView = new MainView(activeScenario, true);
                //mainView.setScenario(activeScenario, true);
                exportScenario();
            }
        });
        
        //exportScenario();
        //mainView = new MainView(new Scenario(new Map(40, 30, 0), new ArrayList<Monster>(), new ArrayList<LootPile>()), false);
        //mainView.setVisible(false);
    }
    
    public static ArrayList<Monster> generateMonsterList(int CR)
    {
        ArrayList<Monster> monsterList = new ArrayList<>();
        ArrayList<Monster> correctCRList = new ArrayList<>();
        ArrayList<MapTile> availableTiles = new ArrayList<>();
        
        for(int y = 0; y < Scenario.getMap().getMapTiles().size(); ++y)
        {
            for(int x = 0; x < Scenario.getMap().getMapTiles().get(y).size(); ++x)
            {
                if(Scenario.getMap().getMapTiles().get(y).get(x).getTerrainIndex() == 1)
                {
                    for(int i = 0; i < Scenario.getLootPileList().size(); ++i)
                    {
                        if(!(Scenario.getLootPileList().get(i).getCoords().getX() == x 
                                && Scenario.getLootPileList().get(i).getCoords().getX() == y) )
                        {
                            availableTiles.add(Scenario.getMap().getMapTiles().get(y).get(x).copyMapTile());
                        }
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
    
    public static ArrayList<LootPile> generateLootPileList(int GP, int items)
    {
        ArrayList<LootPile> lootPileList = new ArrayList<>();
        ArrayList<MapTile> availableTiles = new ArrayList<>();
        
        for(int y = 0; y < Scenario.getMap().getMapTiles().size(); ++y)
        {
            for(int x = 0; x < Scenario.getMap().getMapTiles().get(y).size(); ++x)
            {
                if(Scenario.getMap().getMapTiles().get(y).get(x).getTerrainIndex() == 1)
                {
                    for(int i = 0; i < Scenario.getMonsterList().size(); ++i)
                    {
                        if(!(Scenario.getMonsterList().get(i).getCoords().getX() == x 
                                && Scenario.getMonsterList().get(i).getCoords().getX() == y) )
                        {
                            availableTiles.add(Scenario.getMap().getMapTiles().get(y).get(x).copyMapTile());
                        }
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
     * processScenario is used to read in a .txt file
     * line by line to generate a new Scenario file to
     * be used by the program
     */
    private static Scenario loadScenario(/*Scanner s*/) 
    {
        JFrame frame = new JFrame();
        Scanner infile = null;
        
        Scenario newScenario = null;
        
        final JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("ScenarioFiles", "Scenario");
        fileChooser.setFileFilter(filter);
        int returnVal = fileChooser.showOpenDialog(frame);
        if(returnVal == JFileChooser.APPROVE_OPTION) 
        {
            System.out.println("You chose to open this file: " + fileChooser.getSelectedFile().getName());
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
        System.out.println("Width is: " + width);
        //read second line and get length of map.
        int length;
        line = Macros.readLine(infile);
        if(line.equals("nullLine")) return null;
        startI = line.indexOf(": ")+2;
        token = line.substring(startI);
        length = Macros.readInt(token);
        System.out.println("Length is: " + length);
        
        //read third line and get biome of the map
        String biome;
        int biomeIndex;
        line = Macros.readLine(infile);
        if(line.equals("nullLine")) return null;
        startI = line.indexOf(": ")+2;
        token = line.substring(startI);
        biome = token;
        System.out.println("Biome is: " + biome);
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
        newMap.printMap();

        //skip Entities: line
        infile.nextLine();
        
        //Create arraylists of monsters and lootpiles
        ArrayList<Monster> monsterList = new ArrayList<Monster>();
        ArrayList<LootPile> lootList = new ArrayList<LootPile>();
        
        while(infile.hasNextLine())
        {
            //get index
            line = Macros.readLine(infile);
            System.out.println(line);
            int index = Integer.parseInt(line.substring(0,1));
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
                line = line.substring(line.indexOf("\"")+1);
                
                String name = line.substring(0,line.indexOf("\""));
                Monster newMonster = new Monster(coords, index, name, 0);
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
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write("jake is coolll");
            System.out.println("jake is coool was printed to file");
        } catch(IOException ex)
        {
            System.out.println("error opening file");
        }
        /*
        // parent component of the dialog
        JFrame parentFrame = new JFrame();

        JFileChooser fileChooser = new JFileChooser();
        
        fileChooser.setDialogTitle("Specify a file to save");    

        int userSelection = fileChooser.showSaveDialog(parentFrame);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            System.out.println("Save as file: " + fileToSave.getAbsolutePath());
        }
        */
    }
}
