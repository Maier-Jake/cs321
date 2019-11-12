package mapgenerator;

/**
 *
 * @author CS 321 Team 1
 */
public class LootPile extends MapCoordinates
{
    private int goldPieces;
    private int itemCount;
    
    public LootPile(int newXCoord, int newYCoord, int newGP, int newItemCount)
    {
        xCoord = newXCoord;
        yCoord = newYCoord;
        goldPieces = newGP;
        itemCount = newItemCount;
    }
    
    public LootPile copyLootPile()
    {
        LootPile returnLootPile = new LootPile(xCoord, yCoord, goldPieces, itemCount);
        return returnLootPile;
    }
    
    public int getGoldPieces()
    {
        return goldPieces;
    }
    
    public int getItemCount()
    {
        return itemCount;
    }
    
    public void setGoldPieces(int newGoldPieces)
    {
        goldPieces = newGoldPieces;
    }
    
    public void setItemCount(int newItemCount)
    {
        itemCount = newItemCount;
    }
}
