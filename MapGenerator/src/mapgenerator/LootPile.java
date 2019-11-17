package mapgenerator;

/**
 *
 * @author CS 321 Team 1
 */
public class LootPile
{
    private int goldPieces;
    private int itemCount;
    public final Coordinates coords;
    
    public LootPile(int newXCoord, int newYCoord, int newGP, int newItemCount)
    {
        coords = new Coordinates(newXCoord, newYCoord);
        goldPieces = newGP;
        itemCount = newItemCount;
    }
    public LootPile(Coordinates c, int newGP, int newItemCount)
    {
        coords = c;
        goldPieces = newGP;
        itemCount = newItemCount;
    }
    
    public LootPile copyLootPile()
    {
        LootPile returnLootPile = new LootPile(new Coordinates(coords.getX(),coords.getY()), goldPieces, itemCount);
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
