package mapgenerator;

import java.awt.Color;
import java.awt.event.ActionEvent;
import static java.lang.Integer.max;
import javax.swing.*;
 
import javax.swing.BorderFactory;
import javax.swing.border.Border;

/**
 *
 * @author CS 321 Team 1
 */
public class MainView
{
    JFrame frame;
    JButton saveButton;
    JButton monsterButton;
    JButton lootButton;
    MapIcon map;
    JLabel mapLabel;
    Boolean isVisible;
    JLabel indexList;
    int numberOfLines = 0;
    
    
    MainView(Scenario newScenario, boolean mapReady)
    {
        frame = new JFrame("Main View");
        
        monsterButton = new JButton("Monsters");
        lootButton = new JButton("Loot");
        saveButton = new JButton("Save");
        map = new MapIcon(newScenario.getMap(), mapReady);
        mapLabel = new JLabel(map);
        
        numberOfLines = 4 + newScenario.getMonsterList().size() + newScenario.getLootPileList().size();
        String labelText = "<html> Monsters:<br>";
        
        if(newScenario.getMonsterList().size() != 0)
        {
            for(int i = 0; i < newScenario.getMonsterList().size(); ++i)
            {
                labelText += " " + String.valueOf(i + 1) + ". " + newScenario.getMonsterList().get(i).getName() 
                        + " (" + (newScenario.getMonsterList().get(i).getCoords().getX() + 1)
                        + ", " + (newScenario.getMonsterList().get(i).getCoords().getY() + 1)
                        + ") <br>";
            }
        }
        
        labelText += "<br> Loot Piles:<br>";
        
        if(newScenario.getLootPileList().size() != 0)
        {
            for(int i = 0; i < newScenario.getLootPileList().size(); ++i)
            {
                labelText += " " + String.valueOf(i + 1 +newScenario.getMonsterList().size() ) + ". GP: " 
                        + newScenario.getLootPileList().get(i).getGoldPieces() 
                        + ", Items: " + newScenario.getLootPileList().get(i).getItemCount()
                        + "; (" + (newScenario.getLootPileList().get(i).getCoords().getX() + 1)
                        + ", " + (newScenario.getLootPileList().get(i).getCoords().getY() + 1)
                        + ") <br>";
            }
        }
        
        indexList = new JLabel(labelText);
        
        Border border = BorderFactory.createLineBorder(Color.BLACK, 5);
        indexList.setBorder(border);
        indexList.setVerticalAlignment(JLabel.TOP);
        
        frame.add(monsterButton);
        frame.add(lootButton);
        frame.add(saveButton);
        frame.add(mapLabel);
        frame.add(indexList);
        isVisible = mapReady;
        
        monsterButton.setBounds(20, 20, 100, 20); 
        lootButton.setBounds(140, 20, 100, 20);
        saveButton.setBounds(260, 20, 100, 20);
        saveButton.addActionListener((ActionEvent event) -> {
            MainHandler.exportScenario();
        });
        lootButton.addActionListener((ActionEvent event) -> {     
            lootView.showLootView();
        });
        monsterButton.addActionListener((ActionEvent event) -> {   
            monsterView.showMonsterView();
        });
        
        mapLabel.setBounds(20, 80, (newScenario.getMap().getNumCols() * 15) + 10, (newScenario.getMap().getNumRows() * 15) + 9);
        indexList.setBounds((newScenario.getMap().getNumCols() * 15) + 50, 80, 200, numberOfLines * 16);
        
        frame.setSize((newScenario.getMap().getNumCols() * 15) + 290, max((newScenario.getMap().getNumRows() * 15) + 149, (numberOfLines * 16) + 149)); 
        frame.setLayout(null);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(isVisible);
    }
    
    public JButton getMonsterButton()
    {
        return monsterButton;
    }
    
    public JButton getLootButton()
    {
        return lootButton;
    }
    public JButton getSaveButton()
    {
        return saveButton;
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
        
        frame.setSize((newScenario.getMap().getNumCols() * 15) + 290, max((newScenario.getMap().getNumRows() * 15) + 149, (numberOfLines * 16) + 149)); 
    }
    
    public JFrame getFrame()
    {
        return frame;
    }
}
