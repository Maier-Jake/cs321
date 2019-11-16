package mapgenerator;

import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.io.*;
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
    private static Scenario processScenario(Scanner s) {
        Scenario newScenario;
        String line, token;
        int startI;
        
        //read first line and get width of map.
        int width;
        line = Macros.readLine(s);
        if(line.equals("nullLine")) return null;
        startI = line.indexOf(": ")+2;
        token = line.substring(startI);
        width = Macros.readInt(token);
        
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
        
        //create new Map using this knowledge.
        Map newMap = Map(width, length, biomeIndex);
        
        
        //Line1: Width:
        //Line2: Height:
        // Map(int newNumCols, int newNumRows, int newBiomeIndex)
        // Scenario(Map newMap, ArrayList<Monster> newMonsterList, ArrayList<LootPile> newLootPileList)
        
        return null;
    }
    
    public static void main(String[] args) 
    {
        JFrame frame = new JFrame();
        Scanner infile;
        
        final JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
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
        
        activeScenario = processScenario(infile);
        
            
        
        frame.setLayout(new FlowLayout());

        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //JFileChooser
        
    }
    
}
