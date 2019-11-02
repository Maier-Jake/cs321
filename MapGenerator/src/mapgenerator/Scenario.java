package mapgenerator;

import java.util.ArrayList;

/**
 *
 * @author CS 321 Team 1
 */
public class Scenario 
{
    private Map currentMap;
    private ArrayList<Monster> monsterList;
    private ArrayList<LootPile> lootPileList;
    
    Scenario(Map newMap, ArrayList<Monster> newMonsterList, ArrayList<LootPile> newLootPileList)
    {
        currentMap = newMap;            // TODO change to deep copy
        monsterList = newMonsterList;   // TODO change to deep copy
        lootPileList = newLootPileList; // TODO change to deep copy
    }
    
    public void addMonster(Monster newMonster)
    {
        monsterList.add(newMonster);  // Change to deep copy
    }
    
    public void addLootPile(LootPile newLootPile)
    {
        lootPileList.add(newLootPile);  // Change to deep copy
    }
    
    public Map getMap()
    {
        return currentMap;  // Change to deep copy
    }
    
    public ArrayList<Monster> getMonsterList()
    {
        return monsterList;  // Change to deep copy
    }
    
    public ArrayList<LootPile> getLootPileList()
    {
        return lootPileList;  // Change to deep copy
    }
    
    public void setMap(Map newMap)
    {
        currentMap = newMap;  // Change to deep copy
    }
    
    public void setMonsterList(ArrayList<Monster> newMonsterList)
    {
        monsterList = newMonsterList;  // Change to deep copy
    }
    
    public void setLootPileList(ArrayList<LootPile> newLootPileList)
    {
        lootPileList = newLootPileList;  // Change to deep copy
    }
}
