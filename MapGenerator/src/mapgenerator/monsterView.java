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

public class monsterView {
    JFrame frame = new JFrame("Edit Loot"); // the master frame
    
    JButton addMonster; // button to add loot
    JButton removeMonster; // button to remove loot
    JButton renameMonster; // button to rename loot
    JList monsterList = new JList();
    DefaultListModel monsterModel = new DefaultListModel();
    JScrollPane monsterBox; // box to display loot table
    JTextField xpField; // field to get amount of gold
    JTextField xField; // field to get x coordinate
    JTextField yField; // field to get y coordinate
    JTextField nameField; // field to get loot pile name
    
    JLabel monsterLabel;
    JLabel xpLabel;
    JLabel xLabel;
    JLabel yLabel;
    JLabel nameLabel;
    
    JPanel topPanel; // top level panel
    JPanel editPanel; // panel to hold edit tools
    JPanel displayPanel;// panel to hold display tools
    JPanel buttonPanel; // panel to hold edit buttons
    JPanel xpPanel;
    JPanel xPanel;
    JPanel yPanel;
    JPanel namePanel;
    
    monsterView()
    {
        // do backend stuff
        updateList();
        
        // do graphical stuff
        frame = new JFrame("Edit Monsters"); // the master frame
        
       
        addMonster = new JButton("Add Monsters"); // button to add loot
        removeMonster = new JButton("Remove Selected"); // button to remove loot
        renameMonster = new JButton("Rename Selected"); // button to rename loot
        monsterBox = new JScrollPane(); // box to display loot table
        xpField = new JTextField("100xp"); // field to get amount of gold
        xpField.setPreferredSize(new Dimension(200, 25));
        xField = new JTextField("0"); // field to get x coordinate
        xField.setPreferredSize(new Dimension(200, 25));
        yField = new JTextField("0"); // field to get y coordinate
        yField.setPreferredSize(new Dimension(200, 25));
        nameField = new JTextField("New Monster"); // field to get loot pile name
        nameField.setPreferredSize(new Dimension(200, 25));
    
        monsterLabel = new JLabel("Loot List:");
        monsterLabel = new JLabel("Experience:");
        xLabel = new JLabel("     X:");
        yLabel = new JLabel("     Y:");
        nameLabel = new JLabel("Name:");
    
        topPanel = new JPanel(); // top level panel
        editPanel = new JPanel(); // panel to hold edit tools
        displayPanel = new JPanel(); // panel to hold display tools
        buttonPanel = new JPanel(); // panel to hold edit buttons
        xpPanel = new JPanel(new FlowLayout());
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
        buttonPanel.add(addMonster);
        buttonPanel.add(removeMonster);
        buttonPanel.add(renameMonster);
        editPanel.add(buttonPanel);
        
        xpPanel.add(xpLabel);
        xpPanel.add(xpField);
        editPanel.add(xpPanel);
        
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
        displayPanel.add(monsterLabel);
        displayPanel.add(monsterBox);
        topPanel.add(displayPanel);
        
        frame.add(topPanel);
        
        frame.pack();
        frame.setSize(900, 600);
        frame.setVisible(false);
    }
    
    public void showMonsterView() { frame.setVisible(true);}
    public void hideMonsterView() { frame.setVisible(false);}
    public void updateList()
    {
        ArrayList<LootPile> loots = new ArrayList<>();
        
    }
}
