package mapgenerator;

import java.util.ArrayList;

/**
 * This class holds all of the values used for the program.
 * In the MVC architecture this is classified as the model.
 * @author CS 321 Team 1
 */
public class Scenario
{
    private static Map currentMap;
    private static ArrayList<Monster> monsterList;
    private static ArrayList<LootPile> lootPileList;
    
    Scenario(Map newMap, ArrayList<Monster> newMonsterList, ArrayList<LootPile> newLootPileList)
    {
        currentMap = newMap;
        monsterList = newMonsterList;
        lootPileList = newLootPileList;
    }
    
    /**
     *
     * @param newMonster
     */
    public void addMonster(Monster newMonster)
    {
        monsterList.add(newMonster.copyMonster());
    }
    
    /**
     *
     * @param newLootPile
     */
    public void addLootPile(LootPile newLootPile)
    {
        lootPileList.add(newLootPile.copyLootPile());
    }
    
    /**
     *
     * @return
     */
    public static Map getMap()
    {
        return currentMap;
    }
    
    /**
     *
     * @return
     */
    public static ArrayList<Monster> getMonsterList()
    {
        /*ArrayList<Monster> returnList = new ArrayList<>(monsterList.size());
        for(int i = 0; i < monsterList.size(); i++)
        {
            returnList.set(i, monsterList.get(i).copyMonster());
        }*/
        return monsterList;
    }
    
    /**
     *
     * @return
     */
    public static ArrayList<LootPile> getLootPileList()
    {
        /*ArrayList<LootPile> returnList = new ArrayList<>(lootPileList.size());
        for(int i = 0; i < lootPileList.size(); i++)
        {
            returnList.set(i, lootPileList.get(i).copyLootPile());
        }*/
        return lootPileList;
    }
    
    /**
     *
     * @param newMap
     */
    public void setMap(Map newMap)
    {
        currentMap = newMap;
    }
    
    /**
     *
     * @param newMonsterList
     */
    public void setMonsterList(ArrayList<Monster> newMonsterList)
    {
        monsterList.clear();
        for(Monster newMonster : newMonsterList) 
        {
            monsterList.add(newMonster.copyMonster());
        }
    }
    
    /**
     *
     * @param newLootPileList
     */
    public void setLootPileList(ArrayList<LootPile> newLootPileList)
    {
        lootPileList.clear();
        for(LootPile newLootPile : newLootPileList)
        {
            lootPileList.add(newLootPile.copyLootPile());
        }
    }
}
