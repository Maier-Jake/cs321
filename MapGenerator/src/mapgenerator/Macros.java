package mapgenerator;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This class holds some assignments and parsing code.
 * @author CS 321 Team 1
 */
public class Macros {

    /**
     *
     */
    public static final int FORESTINDEX = 0,

    /**
     *
     */
    DUNGEONINDEX = 1,

    /**
     *
     */
    SWAMPINDEX = 2;
    
    /**
     *
     * @param s
     * @return
     */
    public static int readInt(String s)
    {
        int number = -1;
        try {
            number = Integer.parseInt(s);
        } catch (NumberFormatException ex) {
            System.out.println("I'm sorry Dave, I'm afraid I can't do that");
        }
        return number;
    }
    
    /**
     *
     * @param s
     * @return
     */
    public static String readLine(Scanner s)
    {
        String line = "nullLine";
        try {
            line = s.nextLine();
        } catch (NoSuchElementException ex)
        {
            System.out.println("I'm sorry Dave, there is no line here.");
        }
        return line;
    }
    
    /**
     *
     * @param s
     * @return
     */
    public static int getBiomeIndex(String s)
    {
        switch (s) {
            case "Forest":
                return FORESTINDEX;
            case "Dungeon":
                return DUNGEONINDEX;
            case "Swamp":
                return SWAMPINDEX;
            default:  
                System.out.println("Biome not recognized");
                return -1;
        }
    }

    /**
     *
     * @param index
     * @return
     */
    public static String convertIndexToBiome(int index)
    {
        switch (index) {
            case FORESTINDEX:
                return "Forest";
            case DUNGEONINDEX:
                return "Dungeon";
            case SWAMPINDEX:
                return "Swamp";
            default:
                return "BiomeNotRecognized";
        }
    }
    
    /**
     *
     * @param s
     * @return
     */
    public static String readChar(Scanner s)
    {
        s.useDelimiter("");
        String c = "";
        try {
            c = s.next();
        } catch (NoSuchElementException ex)
        {
        }
        s.useDelimiter(" ");
        return c;
    }

    /**
     *
     * @param f
     * @return
     */
    public static PrintWriter getPrintWriter(File f)
    {
        try { 
            //f.createNewFile();
            return new PrintWriter(f);}
        catch (IOException ex){
            return null;
        }
    }

    /**
     *
     * @param src
     * @param dest
     * @throws IOException
     */
    public static void copyFile(File src, File dest) throws IOException {
        Files.copy(src.toPath(), dest.toPath());
    }
}

