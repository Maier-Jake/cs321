package mapgenerator;

import java.awt.FlowLayout;
import javax.swing.*; 
import java.awt.event.*;

/**
 *
 * @author CS 321 Team 1
 */
public class InitialView
{
    // JFrame frame;
    private int buttonState;
    JFrame frame;
    public void InitialView()
    {
        buttonState = 0;
    }
    
    public void displayView()
    {
        frame = new JFrame("Initial VIew");
        
        JButton newMapButton = new JButton("New Map");
        JButton loadMapButton = new JButton("Load Map");
        
        // JPanel p = new JPanel(); 
  
        // p.setLayout(new FlowLayout());
        
        frame.add(newMapButton);
        frame.add(loadMapButton);
        
        newMapButton.setBounds(100, 40, 100, 40); 
        loadMapButton.setBounds(100, 100, 100, 40);
        
        newMapButton.addActionListener(new ActionListener() 
        {        
            public void actionPerformed(ActionEvent event) 
            {          
                buttonState = 1;
                frame.dispose();
            }
        });
        
        loadMapButton.addActionListener(new ActionListener() 
        {        
            public void actionPerformed(ActionEvent event) 
            {          
                buttonState = 2;
                frame.dispose();
            }      
        });
        
        // frame.add(p);
        
        frame.setSize(300, 220); 
        frame.setLayout(null);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        /*while(getButtonState() == 0)
        {
            int x = 0;
        }*/
        
        // return buttonState;
    }
    
    public int getButtonState()
    {
        return buttonState;
    }
}
