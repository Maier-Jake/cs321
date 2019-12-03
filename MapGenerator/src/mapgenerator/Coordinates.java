/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapgenerator;

/** Coordinate objects are created to store x and y positions for our tiles,
 *      monsters, and lootpiles.
 * @author Team 1
 */
public class Coordinates {
    private int xCoord;
    private int yCoord;
    
    /** This constructor creates a new coordinates object with a passed in
     *       value for the position.
     * @param x is the passed in x-position (column)
     * @param y is the passed in y-position (row)
     */
    public Coordinates(int x, int y)
    {
        xCoord = x;
        yCoord = y;
    }
    
    /**
     * This function returns the index of the column the object is in.
     * @return the x-position (column)
     */
    public int getX()
    {
        return xCoord;
    }
    
    /**
     * This function returns the index of the row the object is in.
     * @return the y-position (row)
     */
    public int getY()
    {
        return yCoord;
    }
    
    /** 
     * This function changes the column of the object.
     * @param newX is the index of the column we are putting the object.
     */
    public void setX(int newX)
    {
        xCoord = newX;
    }
    
    /** 
     * This function changes the row of the object.
     * @param newY is the index of the row we are putting the object.
     */
    public void setY(int newY)
    {
        yCoord = newY;
    }
}
