package mapgenerator;

import javax.swing.*; 

/**
 * This class is the first GUI that the user will see. It has buttons to
 *    create a new scenario or load in a previously saved one.
 * @author CS 321 Team 1
 */
public class InitialView
{
    JFrame frame;
    JButton newMapButton;
    JButton loadMapButton;
    
    /**
    * This constructor builds the initialview.
    */
    InitialView()
    {
        frame = new JFrame("Initial View");
        
        newMapButton = new JButton("New Map");
        loadMapButton = new JButton("Load Map");
        
        frame.add(newMapButton);
        frame.add(loadMapButton);
        
        newMapButton.setBounds(100, 40, 100, 40); 
        loadMapButton.setBounds(100, 100, 100, 40);
        
        frame.setSize(300, 220); 
        frame.setLayout(null);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    /**
     * This method is used by the mainhandler to gain access to the "New Map" button on the InitialView.
     * @return the reference to the button in initialView
     */
    public JButton getNewMapButton()
    {
        return newMapButton;
    }
    
    /**
     * This method is used by the mainhandler to gain access to the "Load Map" button on the InitialView.
     * @return the reference to the button in initialView
     */
    public JButton getLoadMapButton()
    {
        return loadMapButton;
    }
    
    /**
     * This method is used by the mainhandler to gain access to the guiFrame of the InitialView.
     * This is used to show and hide the GUI.
     * @return the reference to the initialview frame.
     */
    public JFrame getFrame()
    {
        return frame;
    }
}
