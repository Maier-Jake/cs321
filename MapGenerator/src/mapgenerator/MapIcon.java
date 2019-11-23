package mapgenerator;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
/**
 *
 * @author CS 321 Team 1
 */
public class MapIcon implements Icon 
{
    private final int pixelWidth;
    private final int pixelHeight;
    private final Map map;
    private final boolean mapReady;
    
    public MapIcon(Map newMap, boolean ready)
    {
        map = newMap;
        pixelWidth = (map.getNumCols() * 15) + 10;
        pixelHeight = (map.getNumRows() * 15) + 9;
        mapReady = ready;
    }
    
    @Override
    public int getIconWidth()
    {
        return pixelWidth;
    }
    
    @Override
    public int getIconHeight()
    {
        return pixelHeight;
    }
    
    @Override
    public void paintIcon(Component c, Graphics g, int x, int y)
    {
        Graphics2D g2 = (Graphics2D) g;
        Rectangle2D.Double background = new Rectangle2D.Double(x, y, pixelWidth, pixelHeight);
        
        g2.setColor(Color.BLACK);
        g2.fill(background);
        
        if(mapReady)
        {
            for(int i = 0; i < map.getNumRows(); ++i)
            {
                for(int j = 0; j < map.getNumCols(); ++j)
                {
                    Rectangle2D.Double currentSquare = new Rectangle2D.Double((x + 7 + (j * 15)), (y + 6 + (i * 15)), 12, 12);
                    switch(map.getMapTiles().get(i).get(j).getTerrainIndex())
                    {
                        case 0:
                            g2.setColor(Color.RED);
                            g2.fill(currentSquare);
                            break;
                        case 1:
                            g2.setColor(Color.BLUE);
                            g2.fill(currentSquare);
                            break;
                        case 2:
                            g2.setColor(Color.GREEN);
                            g2.fill(currentSquare);
                            break;
                    }
                }
            }
        }
    }
}
