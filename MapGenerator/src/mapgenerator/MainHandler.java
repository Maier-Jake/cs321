package mapgenerator;

import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.awt.event.*;
import static java.lang.Math.floor;

/**
 * This class serves as the main class for the program.
 * In the MVC architecture, this class in the controller.
 * @author CS 321 Team 1
 */
public class MainHandler 
{
    private static Scenario activeScenario;
    private static InitialView initialView;
    private static MainView mainView;
    private static NewMapView newMapView;
    private static ArrayList<Monster> availableMonsters;
    private static ArrayList<Monster> availableMonstersInForest;
    private static ArrayList<Monster> availableMonstersInDungeon;
    private static ArrayList<Monster> availableMonstersInSwamp;
    
    /**
     * Main serves to start the program and initialize the monster databases 
     * and InitialView.
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException 
    {
        setUpDatabase();
        
        initialView = new InitialView();
        
        initialView.getNewMapButton().addActionListener(new ActionListener() 
        {        
            public void actionPerformed(ActionEvent event) 
            {
                initialView.getFrame().dispose();
                newMapView = new NewMapView();
        
                newMapView.getGenerateButton().addActionListener(new ActionListener() 
                {        
                    public void actionPerformed(ActionEvent event) 
                    {
                        Map newMap = new Map(newMapView.getMapWidth(), newMapView.getMapHeight(), newMapView.getBiome());
                        ArrayList<Monster> newMonsters = generateMonsterList(newMap, null, newMapView.getCR());
                        ArrayList<LootPile> newLoots = generateLootPileList(newMap, newMonsters, newMapView.getGP(), newMapView.getItems());
                        activeScenario = new Scenario(newMap, newMonsters, newLoots);
                        // activeScenario.setMonsterList(generateMonsterList(15));///////////////////////////////
                        // activeScenario.setLootPileList(generateLootPileList(20,5));
                        newMapView.getFrame().dispose();
                        // mainView = new MainView(activeScenario, true);
                        initializeMainView(activeScenario);
                    }
                });
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
                initializeMainView(activeScenario);
                // mainView = new MainView(activeScenario, true);
            }
        });
    }
    
    private static void initializeMainView(Scenario scenario)
    {
        mainView = new MainView(scenario, true);
        mainView.getLootButton().addActionListener(new ActionListener() 
        {        
            public void actionPerformed(ActionEvent event) 
            {
                LootEditView lootEdit = new LootEditView(scenario.getLootPileList());
                lootEdit.getGenerateButton().addActionListener(new ActionListener() 
                {        
                    public void actionPerformed(ActionEvent event) 
                    {
                        ArrayList<LootPile> newLoots = generateLootPileList(scenario.getMap(), 
                                scenario.getMonsterList(), lootEdit.getGP(), lootEdit.getItems());
                        activeScenario.setLootPileList(newLoots);
                        lootEdit.getFrame().dispose();
                        mainView.getFrame().dispose();
                        initializeMainView(activeScenario);
                    }
                });
                lootEdit.getCancelButton().addActionListener(new ActionListener() 
                {        
                    public void actionPerformed(ActionEvent event) 
                    {
                        lootEdit.getFrame().dispose();
                    }
                });
            }
        });
        mainView.getMonsterButton().addActionListener(new ActionListener() 
        {        
            public void actionPerformed(ActionEvent event) 
            {
                MonsterEditView monsterEdit = new MonsterEditView(scenario.getMonsterList());
                monsterEdit.getGenerateButton().addActionListener(new ActionListener() 
                {        
                    public void actionPerformed(ActionEvent event) 
                    {
                        ArrayList<Monster> newMonsters = generateMonsterList(scenario.getMap(), 
                                                                        scenario.getLootPileList(), monsterEdit.getCR());
                        activeScenario.setMonsterList(newMonsters);
                        monsterEdit.getFrame().dispose();
                        mainView.getFrame().dispose();
                        initializeMainView(activeScenario);
                    }
                });
                monsterEdit.getCancelButton().addActionListener(new ActionListener() 
                {        
                    public void actionPerformed(ActionEvent event) 
                    {
                        monsterEdit.getFrame().dispose();
                    }
                });
            }
        });
        mainView.getSaveButton().addActionListener((ActionEvent event) -> {
            exportScenario();
        });
    }
    
    /**
     *
     * @param newMap The map the monsters will be positioned onto.
     * @param lootList The loot list currently on the above map.
     * @param CR The new Challenge Rating for the monsters.
     * @return An ArrayList of the new monsters.
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
        if(newMap.getBiomeIndex() == 1)
        {
            while(keepRunning)
            {
                for(int i = 0; i < availableMonstersInDungeon.size(); ++i)
                {
                    if(availableMonstersInDungeon.get(i).getCR() < workingCR)
                    {
                        correctCRList.add(availableMonstersInDungeon.get(i));
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
        }
        else if(newMap.getBiomeIndex() == 2)
        {
            while(keepRunning)
            {
                for(int i = 0; i < availableMonstersInSwamp.size(); ++i)
                {
                    if(availableMonstersInSwamp.get(i).getCR() < workingCR)
                    {
                        correctCRList.add(availableMonstersInSwamp.get(i));
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
        }
        else
        {
            while(keepRunning)
            {
                for(int i = 0; i < availableMonstersInForest.size(); ++i)
                {
                    if(availableMonstersInForest.get(i).getCR() < workingCR)
                    {
                        correctCRList.add(availableMonstersInForest.get(i));
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
        }
            
        
        
        return monsterList;
    }
    
    /**
     *
     * @param newMap The map the loot piles will be positioned on.
     * @param monsterList The list of monsters already on the map.
     * @param GP The amount of gold pieces in the new loot piles.
     * @param items The amount of items in the new loot piles.
     * @return An ArrayList of the new loot piles.
     */
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
    
    private static void setUpDatabase()
    {
        availableMonsters = new ArrayList<>();
        availableMonsters.add(new Monster(0, 0, "Orc", 1));
        availableMonsters.add(new Monster(0, 0, "Skeleton", (float) 0.5));
        availableMonsters.add(new Monster(0, 0, "Thug", (float) 0.25));
        availableMonsters.add(new Monster(0, 0, "Bear", 2));
        availableMonsters.add(new Monster(0, 0, "Dragon", 8));
        
        availableMonstersInForest = new ArrayList<>();
        availableMonstersInForest.add(new Monster(0, 0, "Panther", (float) 0.25));
        availableMonstersInForest.add(new Monster(0, 0, "Polar Bear", (float) 2));
        availableMonstersInForest.add(new Monster(0, 0, "Giant Boar", (float) 2));
        availableMonstersInForest.add(new Monster(0, 0, "Warg", (float) 0.5));
        availableMonstersInForest.add(new Monster(0, 0, "Wolf", (float) 0.25));
        availableMonstersInForest.add(new Monster(0, 0, "Elk", (float) 0.25));
        availableMonstersInForest.add(new Monster(0, 0, "Assassin", (float) 8));
        availableMonstersInForest.add(new Monster(0, 0, "Giant Rat", (float) 0.125));
        
        availableMonstersInDungeon = new ArrayList<>();
        availableMonstersInDungeon.add(new Monster(0, 0, "Assassin", (float) 8));
        availableMonstersInDungeon.add(new Monster(0, 0, "Wight", (float) 3));
        availableMonstersInDungeon.add(new Monster(0, 0, "Zombie", (float) 0.25));
        availableMonstersInDungeon.add(new Monster(0, 0, "Skeleton", (float) 0.25));
        availableMonstersInDungeon.add(new Monster(0, 0, "Ogre Zombie", (float) 2));
        availableMonstersInDungeon.add(new Monster(0, 0, "Giant Rat", (float) 0.125));
        
        availableMonstersInSwamp = new ArrayList<>();
        availableMonstersInSwamp.add(new Monster(0, 0, "Warg", (float) 0.5));
        availableMonstersInSwamp.add(new Monster(0, 0, "Wolf", (float) 0.25));
        availableMonstersInSwamp.add(new Monster(0, 0, "Giant Centipede", (float) 0.25));
        availableMonstersInSwamp.add(new Monster(0, 0, "Giant Frog", (float) 0.25));
        availableMonstersInSwamp.add(new Monster(0, 0, "Giant Poisonous Snake", (float) 0.25));
        availableMonstersInSwamp.add(new Monster(0, 0, "Flying Snake", (float) 0.125));
        
    }
    
    /**
     * loadScenario is used to read in a .scenario file
     * line by line to generate a new Scenario file to
     * be used by the program
     */
    private static Scenario loadScenario() 
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
    
    /**
     * Export scenario is used to save the scenario to a file.
     */
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
