package mapgenerator;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.*; 
import javax.swing.border.Border;

/**
 *
 * @author CS 321 Team 1
 */
public class MonsterEditView
{
    JFrame frame;
    JButton generateButton;
    JButton cancelButton;
    JLabel crLabel;
    JTextField crText;
    JLabel indexList;
    int numberOfLines = 0;
    
    MonsterEditView(ArrayList<Monster> monsterList)
    {
        frame = new JFrame("Monster Edit");
        
        generateButton = new JButton("Generate New Monsters");
        cancelButton = new JButton("Cancel");
        crLabel = new JLabel("New Challenge Rating:");
        crText = new JTextField("5");
        
        numberOfLines = 2 + monsterList.size();
        String labelText = "<html> Current Monsters:<br>";
        
        if(monsterList.size() != 0)
        {
            for(int i = 0; i < monsterList.size(); ++i)
            {
                labelText += " " + String.valueOf(i + 1) + ". " + monsterList.get(i).getName() 
                        + " (" + (monsterList.get(i).getCoords().getX() + 1)
                        + ", " + (monsterList.get(i).getCoords().getY() + 1)
                        + ") <br>";
            }
        }
        
        indexList = new JLabel(labelText);
        
        Border border = BorderFactory.createLineBorder(Color.BLACK, 5);
        indexList.setBorder(border);
        indexList.setVerticalAlignment(JLabel.TOP);
        
        frame.add(generateButton);
        frame.add(cancelButton);
        frame.add(crLabel);
        frame.add(crText);
        frame.add(indexList);
        
        crLabel.setBounds(20, 20, 140, 20); 
        crText.setBounds(160, 20, 60, 20); 
        generateButton.setBounds(40, 60, 180, 40); 
        cancelButton.setBounds(40, 105, 180, 40);
        indexList.setBounds(240, 20, 200, numberOfLines * 16);
        
        frame.setSize(460, 400); 
        frame.setLayout(null);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    public JButton getGenerateButton()
    {
        return generateButton;
    }
    
    public JButton getCancelButton()
    {
        return cancelButton;
    }
    
    public int getCR()
    {
        return Integer.parseInt(crText.getText());
    }
    
    public JFrame getFrame()
    {
        return frame;
    }
}
