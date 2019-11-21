package mapgenerator;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author CS 321 Team 1
 */
public class Scenario implements Serializable 
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
        monsterList.add(newMonster.copyMonster());
    }
    
    public void addLootPile(LootPile newLootPile)
    {
        lootPileList.add(newLootPile.copyLootPile());
    }
    
    public Map getMap()
    {
        return currentMap;
    }
    
    public ArrayList<Monster> getMonsterList()
    {
        ArrayList<Monster> returnList = new ArrayList<>(monsterList.size());
        for(int i = 0; i < monsterList.size(); i++)
        {
            returnList.set(i, monsterList.get(i).copyMonster());
        }
        return returnList;
    }
    
    public ArrayList<LootPile> getLootPileList()
    {
        ArrayList<LootPile> returnList = new ArrayList<>(lootPileList.size());
        for(int i = 0; i < lootPileList.size(); i++)
        {
            returnList.set(i, lootPileList.get(i).copyLootPile());
        }
        return returnList;
    }
    
    public void setMap(Map newMap)
    {
        currentMap = newMap;
    }
    
    public void setMonsterList(ArrayList<Monster> newMonsterList)
    {
        monsterList.clear();
        for(Monster newMonster : newMonsterList) 
        {
            monsterList.add(newMonster.copyMonster());
        }
    }
    
    public void setLootPileList(ArrayList<LootPile> newLootPileList)
    {
        lootPileList.clear();
        for(LootPile newLootPile : newLootPileList)
        {
            lootPileList.add(newLootPile.copyLootPile());
        }
    }
}
