package mapgenerator;

import javax.swing.*; 

/**
 *
 * @author CS 321 Team 1
 */
public class InitialView
{
    JFrame frame;
    JButton newMapButton;
    JButton loadMapButton;
    
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
    
    public JButton getNewMapButton()
    {
        return newMapButton;
    }
    
    public JButton getLoadMapButton()
    {
        return loadMapButton;
    }
    
    public JFrame getFrame()
    {
        return frame;
    }
}
