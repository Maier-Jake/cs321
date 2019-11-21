package mapgenerator;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *
 * @author CS 321 Team 1
 */
public class Macros {
    public static final int FORESTINDEX = 0, DUNGEONINDEX = 1, SWAMPINDEX = 2;
    
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
    public static PrintWriter getPrintWriter(File f)
    {
        try { 
            f.createNewFile();
            return new PrintWriter(f);}
        catch (IOException ex){
            return null;
        }
    }
    public static void copyFile(File src, File dest) throws IOException {
        Files.copy(src.toPath(), dest.toPath());
    }
}

