/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapgenerator;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *
 * @author jaked
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
}

