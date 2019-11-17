/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapgenerator;

/**
 *
 * @author jaked
 */
public class Coordinates {
    private int xCoord;
    private int yCoord;
    
    public Coordinates(int x, int y)
    {
        xCoord = x;
        yCoord = y;
    }
    
    public int getX()
    {
        return xCoord;
    }
    
    public int getY()
    {
        return yCoord;
    }
    
    public void setX(int newX)
    {
        xCoord = newX;
    }
    
    public void setY(int newY)
    {
        yCoord = newY;
    }
}
