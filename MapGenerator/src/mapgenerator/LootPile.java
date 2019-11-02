package mapgenerator;

/**
 *
 * @author CS 321 Team 1
 */
public class LootPile implements MapCoordinates
{
    private int xCoord;
    private int yCoord;
    private int goldPieces;
    private int itemCount;
    
    public LootPile(int newXCoord, int newYCoord, int newGP, int newItemCount)
    {
        xCoord = newXCoord;
        yCoord = newYCoord;
        goldPieces = newGP;
        itemCount = newItemCount;
    }
            
    @Override
    public int getXCoord()
    {
        return xCoord;
    }
    
    @Override
    public int getYCoord()
    {
        return yCoord;
    }
    
    public int getGoldPieces()
    {
        return goldPieces;
    }
    
    public int getItemCount()
    {
        return itemCount;
    }
    
    @Override
    public void setXCoord(int newX)
    {
        xCoord = newX;
    }
    
    @Override
    public void setYCoord(int newY)
    {
        yCoord = newY;
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
