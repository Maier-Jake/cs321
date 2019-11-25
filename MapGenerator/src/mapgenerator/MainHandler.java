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

/**
 *
 * @author CS 321 Team 1
 */
public class MainHandler 
{
    
    private static Scenario activeScenario;
    private static InitialView initialView;
    private static MainView mainView;
    
    public static void main(String[] args) throws InterruptedException 
    {
        
        initialView = new InitialView();
        
        initialView.getNewMapButton().addActionListener(new ActionListener() 
        {        
            public void actionPerformed(ActionEvent event) 
            {
                initialView.getFrame().dispose();
                activeScenario = new Scenario(new Map(30, 40, Macros.FORESTINDEX), new ArrayList<Monster>(), new ArrayList<LootPile>());
                mainView = new MainView(activeScenario, true);
                System.out.println("Exiting program");
                //exportScenario();
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
                mainView = new MainView(activeScenario, true);
                //mainView.setScenario(activeScenario, true);
                //exportScenario();
            }
        });
        
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
        
        //exportScenario();
        //mainView = new MainView(new Scenario(new Map(40, 30, 0), new ArrayList<Monster>(), new ArrayList<LootPile>()), false);
        //mainView.setVisible(false);
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
        File workingDirectory = new File(System.getProperty("user.dir"));
        fileChooser.setCurrentDirectory(workingDirectory);
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
        //System.out.println("Width is: " + width);
        //read second line and get length of map.
        int length;
        line = Macros.readLine(infile);
        if(line.equals("nullLine")) return null;
        startI = line.indexOf(": ")+2;
        token = line.substring(startI);
        length = Macros.readInt(token);
        //System.out.println("Length is: " + length);
        
        //read third line and get biome of the map
        String biome;
        int biomeIndex;
        line = Macros.readLine(infile);
        if(line.equals("nullLine")) return null;
        startI = line.indexOf(": ")+2;
        token = line.substring(startI);
        biome = token;
        //System.out.println("Biome is: " + biome);
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
            //System.out.println(line);
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
                Monster newMonster = new Monster(coords, index, name);
                monsterList.add(newMonster);
            }
            else if(line.contains("LootPile:"))
            {
                line = line.substring(line.indexOf("\"")+1);
                int numItems = Integer.parseInt(line.substring(0,line.indexOf(",")));
                line = line.substring(line.indexOf(",")+1);
                int numGP = Integer.parseInt(line.substring(0,line.indexOf("\"")));
                LootPile newLoot = new LootPile(coords, numGP, numItems, index);
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
            //System.out.println("filename: " + fileName); 
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
                //write index
                writer.write(monster.getMonsterIndex()+ ": ");
                //write coordinates
                writer.write("("+monster.getX()+","+monster.getY()+") ");
                //write "monster"
                writer.write("Monster: \"" + monster.getName() +"\"");
                //endline
                writer.write("\n");
            }
            for(LootPile loot : activeScenario.getLootPileList()) 
            {
                //write index
                writer.write(loot.getIndex()+ ": ");
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
