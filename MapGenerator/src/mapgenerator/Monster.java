package mapgenerator;

/**
 *
 * @author CS 321 Team 1
 */
public class Monster extends MapCoordinates
{
    private int monsterIndex;
    
    public Monster(int newXCoord, int newYCoord, int newMonsterIndex)
    {
        xCoord = newXCoord;
        yCoord = newYCoord;
        monsterIndex = newMonsterIndex;
    }
    
    public Monster copyMonster()
    {
        Monster returnMonster = new Monster(xCoord, yCoord, monsterIndex);
        return returnMonster;
    }
    
    public int getMonsterIndex()
    {
        return monsterIndex;
    }
    
    public void setMonsterIndex(int newMonsterIndex)
    {
        monsterIndex = newMonsterIndex;
    }
}
