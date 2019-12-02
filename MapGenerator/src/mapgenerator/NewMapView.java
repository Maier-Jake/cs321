package mapgenerator;

import javax.swing.*; 

/**
 *
 * @author CS 321 Team 1
 */
public class NewMapView
{
    JFrame frame;
    JButton generateButton;
    JTextField mapWidth;
    JTextField mapHeight;
    JTextField monsterCR;
    JTextField gpNumber;
    JTextField itemNumber;
    JComboBox mapBiome;
    JLabel widthLabel;
    JLabel heightLabel;
    JLabel CRLabel;
    JLabel gpLabel;
    JLabel itemLabel;
    JLabel biomeLabel;
    
    
    NewMapView()
    {
        frame = new JFrame("New Scenario");
        
        generateButton = new JButton("Generate Scenario");
        mapWidth = new JTextField("10");
        mapHeight = new JTextField("10");
        monsterCR = new JTextField("3");
        gpNumber = new JTextField("25");
        itemNumber = new JTextField("3");
        String s1[] = {"Forest", "Dungeon", "Swamp"};
        mapBiome = new JComboBox(s1);
        widthLabel = new JLabel("Map Width:");
        heightLabel = new JLabel("Map Height:");
        CRLabel = new JLabel("Challenge Rating:");
        gpLabel = new JLabel("# of Gold Pieces:");
        itemLabel = new JLabel("# of Items:");
        biomeLabel = new JLabel("Map Biome:");
        
        frame.add(generateButton);
        frame.add(mapWidth);
        frame.add(mapHeight);
        frame.add(monsterCR);
        frame.add(gpNumber);
        frame.add(itemNumber);
        frame.add(mapBiome);
        frame.add(widthLabel);
        frame.add(heightLabel);
        frame.add(CRLabel);
        frame.add(gpLabel);
        frame.add(itemLabel);
        frame.add(biomeLabel);
        
        generateButton.setBounds(80, 180, 160, 40); 
        mapWidth.setBounds(140, 20, 100, 20); 
        mapHeight.setBounds(140, 45, 100, 20);
        mapBiome.setBounds(140, 70, 100, 20);  
        monsterCR.setBounds(140, 95, 100, 20); 
        gpNumber.setBounds(140, 120, 100, 20); 
        itemNumber.setBounds(140, 145, 100, 20); 
        widthLabel.setBounds(20, 20, 100, 20); 
        heightLabel.setBounds(20, 45, 100, 20); 
        biomeLabel.setBounds(20, 70, 100, 20); 
        CRLabel.setBounds(20, 95, 120, 20); 
        gpLabel.setBounds(20, 120, 100, 20); 
        itemLabel.setBounds(20, 145, 100, 20); 
        
        frame.setSize(270, 270); 
        frame.setLayout(null);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    public JButton getGenerateButton()
    {
        return generateButton;
    }
    
    public int getMapWidth()
    {
        return Integer.parseInt(mapWidth.getText());
    }
    
    public int getMapHeight()
    {
        return Integer.parseInt(mapHeight.getText());
    }
    
    public int getGP()
    {
        return Integer.parseInt(gpNumber.getText());
    }
    
    public int getItems()
    {
        return Integer.parseInt(itemNumber.getText());
    }
    
    public int getCR()
    {
        return Integer.parseInt(monsterCR.getText());
    }
    
    public int getBiome()
    {
        switch((String) mapBiome.getSelectedItem())
        {
            case "Forest":
                return 0;
                
            case "Dungeon":
                return 1;
                
            case "Swamp":
                return 2;
                
            default:
                return 0;
        }
    }
    
    public JFrame getFrame()
    {
        return frame;
    }
}
