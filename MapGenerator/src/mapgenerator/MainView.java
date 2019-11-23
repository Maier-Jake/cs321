package mapgenerator;

import javax.swing.*; 

/**
 *
 * @author CS 321 Team 1
 */
public class MainView
{
    JFrame frame;
    JButton monsterButton;
    JButton lootButton;
    MapIcon map;
    JLabel mapLabel;
    
    
    MainView(Scenario newScenario, boolean mapReady)
    {
        frame = new JFrame("Main View");
        
        monsterButton = new JButton("Monsters");
        lootButton = new JButton("Loot");
        map = new MapIcon(newScenario.getMap(), mapReady);
        mapLabel = new JLabel(map);
        
        frame.add(monsterButton);
        frame.add(lootButton);
        frame.add(mapLabel);
        
        monsterButton.setBounds(20, 20, 100, 20); 
        lootButton.setBounds(140, 20, 100, 20);
        mapLabel.setBounds(20, 80, (newScenario.getMap().getNumCols() * 15) + 10, (newScenario.getMap().getNumRows() * 15) + 9);
        
        frame.setSize((newScenario.getMap().getNumCols() * 15) + 370, (newScenario.getMap().getNumRows() * 15) + 149); 
        frame.setLayout(null);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    public JButton getMonsterButton()
    {
        return monsterButton;
    }
    
    public JButton getLootButton()
    {
        return lootButton;
    }
    
    public MapIcon getMapIcon()
    {
        return map;
    }
    
    public void setScenario(Scenario newScenario, boolean mapReady)
    {
        map = new MapIcon(newScenario.getMap(), mapReady);
        mapLabel.setIcon(map);
        frame.add(mapLabel);
        mapLabel.setBounds(20, 80, (newScenario.getMap().getNumCols() * 15) + 10, (newScenario.getMap().getNumRows() * 15) + 9);
        
        frame.setSize((newScenario.getMap().getNumCols() * 15) + 370, (newScenario.getMap().getNumRows() * 15) + 149); 
    }
    
    public JFrame getFrame()
    {
        return frame;
    }
}
