package mapgenerator;

/**
 *
 * @author CS 321 Team 1
 */
public class LootPile
{
    private int goldPieces;
    private int itemCount;
    private final Coordinates coords;
    private int index;
    
    public LootPile(int newXCoord, int newYCoord, int newGP, int newItemCount, int index)
    {
        coords = new Coordinates(newXCoord, newYCoord);
        goldPieces = newGP;
        itemCount = newItemCount;
        this.index = index;
    }
    public LootPile(Coordinates c, int newGP, int newItemCount, int index)
    {
        coords = c;
        goldPieces = newGP;
        itemCount = newItemCount;
        this.index = index;
    }
    
    
    public LootPile copyLootPile()
    {
        LootPile returnLootPile = new LootPile(new Coordinates(coords.getX(),coords.getY()), goldPieces, itemCount, index);
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
    public int getIndex()
    {
        return index;
    }
    
    public void setIndex(int newIndex)
    {
        index = newIndex;
    }
    public int getX()
    {
        return coords.getX();
    }
    public int getY()
    {
        return coords.getY();
    }
}
