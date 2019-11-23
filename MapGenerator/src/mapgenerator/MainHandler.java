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
        mainView = new MainView(new Scenario(new Map(40, 30, 0), new ArrayList<Monster>(), new ArrayList<LootPile>()), false);
        initialView = new InitialView();
        
        initialView.getNewMapButton().addActionListener(new ActionListener() 
        {        
            public void actionPerformed(ActionEvent event) 
            {
                initialView.getFrame().dispose();
                mainView.setScenario(new Scenario(new Map(30, 40, 0), new ArrayList<Monster>(), new ArrayList<LootPile>()), false);
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
                mainView.setScenario(activeScenario, true);
            }
        });
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
            System.out.println("This should never run!");
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
                Monster newMonster = new Monster(coords, index, name);
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
        //ask the user for a name for the file
        File tempFile = new File("c://Users//jaked//Documents//NetBeansProjects//cs321//MapGenerator//tempfile.scenario");
        PrintWriter fileWriter = Macros.getPrintWriter(tempFile);
        //Create the file
        fileWriter.print("jake is cool");
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
