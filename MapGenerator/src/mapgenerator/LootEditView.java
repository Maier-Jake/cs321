package mapgenerator;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.*; 
import javax.swing.border.Border;

/**
 * This class represents the pop-up gui that enables the user to reroll the lootpiles.
 * @author CS 321 Team 1
 */
public class LootEditView
{
    JFrame frame;
    JButton generateButton;
    JButton cancelButton;
    JLabel gpLabel;
    JTextField gpText;
    JLabel itemLabel;
    JTextField itemText;
    JLabel indexList;
    int numberOfLines = 0;
    
    /**
    * This 
    */
    LootEditView(ArrayList<LootPile> lootList)
    {
        frame = new JFrame("Monster Edit");
        
        generateButton = new JButton("Generate New Loot Piles");
        cancelButton = new JButton("Cancel");
        gpLabel = new JLabel("New # of Gold Pieces:");
        gpText = new JTextField("25");
        itemLabel = new JLabel("New # of Items:");
        itemText = new JTextField("3");
        
        numberOfLines = 2 + lootList.size();
        String labelText = "<html> Current Loot Piles:<br>";
        
        if(lootList.size() != 0)
        {
            for(int i = 0; i < lootList.size(); ++i)
            {
                labelText += " " + String.valueOf(i + 1) + ". GP: " 
                        + lootList.get(i).getGoldPieces() 
                        + ", Items: " + lootList.get(i).getItemCount()
                        + "; (" + (lootList.get(i).getCoords().getX() + 1)
                        + ", " + (lootList.get(i).getCoords().getY() + 1)
                        + ") <br>";
            }
        }
        
        indexList = new JLabel(labelText);
        
        Border border = BorderFactory.createLineBorder(Color.BLACK, 5);
        indexList.setBorder(border);
        indexList.setVerticalAlignment(JLabel.TOP);
        
        frame.add(generateButton);
        frame.add(cancelButton);
        frame.add(gpLabel);
        frame.add(gpText);
        frame.add(itemLabel);
        frame.add(itemText);
        frame.add(indexList);
        
        gpLabel.setBounds(20, 20, 140, 20); 
        gpText.setBounds(160, 20, 60, 20); 
        itemLabel.setBounds(20, 45, 140, 20); 
        itemText.setBounds(160, 45, 60, 20); 
        generateButton.setBounds(40, 85, 180, 40); 
        cancelButton.setBounds(40, 130, 180, 40);
        indexList.setBounds(240, 20, 200, numberOfLines * 16);
        
        frame.setSize(460, 400); 
        frame.setLayout(null);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    /**
     *
     * @return
     */
    public JButton getGenerateButton()
    {
        return generateButton;
    }
    
    /**
     *
     * @return
     */
    public JButton getCancelButton()
    {
        return cancelButton;
    }
    
    /**
     *
     * @return
     */
    public int getGP()
    {
        return Integer.parseInt(gpText.getText());
    }
    
    /**
     *
     * @return
     */
    public int getItems()
    {
        return Integer.parseInt(itemText.getText());
    }
    
    /**
     *
     * @return
     */
    public JFrame getFrame()
    {
        return frame;
    }
}
