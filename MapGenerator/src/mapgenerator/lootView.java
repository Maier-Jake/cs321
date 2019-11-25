package mapgenerator;

import javax.swing.*; 
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

/**
 *
 * @author CS 321 Team 1
 */

// the view that lets user input loot data
public class lootView {
    JFrame frame = new JFrame("Edit Loot"); // the master frame
    
    JButton addLoot; // button to add loot
    JButton removeLoot; // button to remove loot
    JButton renameLoot; // button to rename loot
    JList lootList = new JList();
    DefaultListModel lootModel = new DefaultListModel();
    JScrollPane lootBox; // box to display loot table
    JTextField goldField; // field to get amount of gold
    JTextField xField; // field to get x coordinate
    JTextField yField; // field to get y coordinate
    JTextField nameField; // field to get loot pile name
    
    JLabel lootLabel;
    JLabel goldLabel;
    JLabel xLabel;
    JLabel yLabel;
    JLabel nameLabel;
    
    JPanel topPanel; // top level panel
    JPanel editPanel; // panel to hold edit tools
    JPanel displayPanel;// panel to hold display tools
    JPanel buttonPanel; // panel to hold edit buttons
    JPanel goldPanel;
    JPanel xPanel;
    JPanel yPanel;
    JPanel namePanel;
    
    lootView()
    {
        // do backend stuff
        updateList();
        
        // do graphical stuff
        frame = new JFrame("Edit Loot"); // the master frame
        
       
        addLoot = new JButton("Add Loot"); // button to add loot
        removeLoot = new JButton("Remove Selected"); // button to remove loot
        renameLoot = new JButton("Rename Selected"); // button to rename loot
        lootBox = new JScrollPane(); // box to display loot table
        goldField = new JTextField("100gp"); // field to get amount of gold
        goldField.setPreferredSize(new Dimension(200, 25));
        xField = new JTextField("0"); // field to get x coordinate
        xField.setPreferredSize(new Dimension(200, 25));
        yField = new JTextField("0"); // field to get y coordinate
        yField.setPreferredSize(new Dimension(200, 25));
        nameField = new JTextField("New Loot Pile"); // field to get loot pile name
        nameField.setPreferredSize(new Dimension(200, 25));
    
        lootLabel = new JLabel("Loot List:");
        goldLabel = new JLabel("Gold:");
        xLabel = new JLabel("     X:");
        yLabel = new JLabel("     Y:");
        nameLabel = new JLabel("Name:");
    
        topPanel = new JPanel(); // top level panel
        editPanel = new JPanel(); // panel to hold edit tools
        displayPanel = new JPanel(); // panel to hold display tools
        buttonPanel = new JPanel(); // panel to hold edit buttons
        goldPanel = new JPanel(new FlowLayout());
        xPanel = new JPanel(new FlowLayout());
        yPanel = new JPanel(new FlowLayout());
        namePanel = new JPanel(new FlowLayout());
       
        
        // define GUI layout
        BoxLayout topLayout = new BoxLayout(topPanel, BoxLayout.X_AXIS);
        topPanel.setLayout(topLayout);
        editPanel.setLayout(new BoxLayout(editPanel, BoxLayout.Y_AXIS));
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        
        // add components to GUI
        buttonPanel.add(addLoot);
        buttonPanel.add(removeLoot);
        buttonPanel.add(renameLoot);
        editPanel.add(buttonPanel);
        
        goldPanel.add(goldLabel);
        goldPanel.add(goldField);
        editPanel.add(goldPanel);
        
        xPanel.add(xLabel);
        xPanel.add(xField);
        editPanel.add(xPanel);
        
        yPanel.add(yLabel);
        yPanel.add(yField);
        editPanel.add(yPanel);
        
        namePanel.add(nameLabel);
        namePanel.add(nameField);
        editPanel.add(namePanel);
        editPanel.add(Box.createRigidArea(new Dimension(0, 700)));
        
      
        topPanel.add(editPanel);
        displayPanel.setPreferredSize(new Dimension(700, 600));
        displayPanel.add(lootLabel);
        displayPanel.add(lootBox);
        topPanel.add(displayPanel);
        
        frame.add(topPanel);
        
        frame.pack();
        frame.setSize(900, 600);
        frame.setVisible(false);
    }
    
    public void showLootView() { frame.setVisible(true);}
    public void hideLootView() { frame.setVisible(false);}
    public void updateList()
    {
        ArrayList<LootPile> loots = new ArrayList<>();
        
    }
}
