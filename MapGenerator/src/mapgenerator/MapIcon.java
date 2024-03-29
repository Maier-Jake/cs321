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
    
    /**
     *
     * @param newMap
     * @param ready
     */
    public MapIcon(Map newMap, boolean ready)
    {
        map = newMap;
        pixelWidth = (map.getNumCols() * 15) + 10;
        pixelHeight = (map.getNumRows() * 15) + 9;
        mapReady = ready;
    }
    
    /**
     *
     * @return
     */
    @Override
    public int getIconWidth()
    {
        return pixelWidth;
    }
    
    /**
     *
     * @return
     */
    @Override
    public int getIconHeight()
    {
        return pixelHeight;
    }
    
    /**
     *
     * @param c
     * @param g
     * @param x
     * @param y
     */
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
                    
                    switch (map.getBiomeIndex()) {
                        case 0:
                            switch(map.getMapTiles().get(i).get(j).getTerrainIndex())
                            {
                                case 0:
                                    g2.setColor(new Color(109, 46, 4));
                                    g2.fill(currentSquare);
                                    break;
                                case 1:
                                    g2.setColor(new Color(47, 199, 12));
                                    g2.fill(currentSquare);
                                    break;
                                case 2:
                                    g2.setColor(new Color(9, 96, 3));
                                    g2.fill(currentSquare);
                                    break;
                            }   break;
                        case 1:
                            switch(map.getMapTiles().get(i).get(j).getTerrainIndex())
                            {
                                case 0:
                                    g2.setColor(new Color(78, 78, 78));
                                    g2.fill(currentSquare);
                                    break;
                                case 1:
                                    g2.setColor(new Color(175, 175, 175));
                                    g2.fill(currentSquare);
                                    break;
                                case 2:
                                    g2.setColor(new Color(126, 126, 126));
                                    g2.fill(currentSquare);
                                    break;
                            }   break;
                        case 2:
                            switch(map.getMapTiles().get(i).get(j).getTerrainIndex())
                            {
                                case 0:
                                    g2.setColor(new Color(3, 22, 179));
                                    g2.fill(currentSquare);
                                    break;
                                case 1:
                                    g2.setColor(new Color(3, 179, 98));
                                    g2.fill(currentSquare);
                                    break;
                                case 2:
                                    g2.setColor(new Color(3, 179, 179));
                                    g2.fill(currentSquare);
                                    break;
                            }   break;
                        default:
                            break;
                    }
                }
            }
        }
    }
}
