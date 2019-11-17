package mapgenerator;

import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.NoSuchElementException;

/**
 *
 * @author CS 321 Team 1
 */
public class MainHandler 
{
    
    private static Scenario activeScenario;
    
    /**
     * processScenario is used to read in a .txt file
     * line by line to generate a new Scenario file to
     * be used by the program
     */
    private static Scenario loadScenario(Scanner s) {
        Scenario newScenario;
        String line, token;
        int startI, endI;
        
        //read first line and get width of map.
        int width;
        line = Macros.readLine(s);
        if(line.equals("nullLine")) return null;
        startI = line.indexOf(": ")+2;
        token = line.substring(startI);
        width = Macros.readInt(token);
        System.out.println("Width is: " + width);
        //read second line and get length of map.
        int length;
        line = Macros.readLine(s);
        if(line.equals("nullLine")) return null;
        startI = line.indexOf(": ")+2;
        token = line.substring(startI);
        length = Macros.readInt(token);
        System.out.println("Length is: " + length);
        
        //read third line and get biome of the map
        String biome;
        int biomeIndex;
        line = Macros.readLine(s);
        if(line.equals("nullLine")) return null;
        startI = line.indexOf(": ")+2;
        token = line.substring(startI);
        biome = token;
        System.out.println("Biome is: " + biome);
        biomeIndex = Macros.getBiomeIndex(biome);
        if(biomeIndex == -1) return null;
        
        //create new 2D array of tiles
        ArrayList<ArrayList<MapTile>> aMap = new ArrayList<ArrayList<MapTile>>();
        for(int i = 0; i < width; i++)  {
            aMap.add(new ArrayList<MapTile>());
        }
        //read the .txt file and load into 2D array
        for (int x = 0; x < width; x++)
        { for (int y = 0; y < length; y++)
            {
                int terrainIndex = Integer.parseInt(Macros.readChar(s));
                MapTile newMapTile = new MapTile(x, y, terrainIndex);
                aMap.get(x).add(newMapTile);
            }
        s.nextLine();
        }
        
        //create new Map using this knowledge.
        Map newMap = new Map(width, length, biomeIndex);
        newMap.setMap(aMap);
        newMap.printMap();

        //skip Entities: line
        s.nextLine();
        
        //Create arraylists of monsters and lootpiles
        ArrayList<Monster> monsterList = new ArrayList<Monster>();
        ArrayList<LootPile> lootList = new ArrayList<LootPile>();
        
        while(s.hasNextLine())
        {
            //get index
            line = Macros.readLine(s);
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
    
    public static void main(String[] args) 
    {
        JFrame frame = new JFrame();
        Scanner infile;
        
        final JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("ScenarioFiles", "Scenario");
        fileChooser.setFileFilter(filter);
        int returnVal = fileChooser.showOpenDialog(frame);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("You chose to open this file: " + fileChooser.getSelectedFile().getName());
            try {
                infile = new Scanner(fileChooser.getSelectedFile());
            } catch (FileNotFoundException ex) {
                System.err.println("The file could not be opened.");
                System.out.println("Exiting program");
                return;
            }   
        } else {
            System.out.println("This should never run!");
            infile = new Scanner(System.in);
        }
        
        activeScenario = loadScenario(infile);
        
            
        
        frame.setLayout(new FlowLayout());

        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //JFileChooser
        
    }
    
}
