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
    
    /**
     *
     * @param newXCoord
     * @param newYCoord
     * @param newGP
     * @param newItemCount
     */
    public LootPile(int newXCoord, int newYCoord, int newGP, int newItemCount)
    {
        coords = new Coordinates(newXCoord, newYCoord);
        goldPieces = newGP;
        itemCount = newItemCount;
    }

    /**
     *
     * @param c
     * @param newGP
     * @param newItemCount
     */
    public LootPile(Coordinates c, int newGP, int newItemCount)
    {
        coords = c;
        goldPieces = newGP;
        itemCount = newItemCount;
    }
    
    /**
     *
     * @return
     */
    public LootPile copyLootPile()
    {
        LootPile returnLootPile = new LootPile(new Coordinates(coords.getX(),coords.getY()), goldPieces, itemCount);
        return returnLootPile;
    }
    
    /**
     *
     * @return
     */
    public Coordinates getCoords()
    {
        return coords;
    }
    
    /**
     *
     * @return
     */
    public int getGoldPieces()
    {
        return goldPieces;
    }
    
    /**
     *
     * @return
     */
    public int getItemCount()
    {
        return itemCount;
    }
    
    /**
     *
     * @param newGoldPieces
     */
    public void setGoldPieces(int newGoldPieces)
    {
        goldPieces = newGoldPieces;
    }
    
    /**
     *
     * @param newItemCount
     */
    public void setItemCount(int newItemCount)
    {
        itemCount = newItemCount;
    }

    /**
     *
     * @return
     */
    public int getX()
    {
        return coords.getX();
    }

    /**
     *
     * @return
     */
    public int getY()
    {
        return coords.getY();
    }
}
