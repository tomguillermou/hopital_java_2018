/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import modele.EFrame;

/**
 *
 * @author Tom
 */
public class ConnectionView extends EFrame {
    
    private final JPanel boxComponents;
    private final JLabel labelDatabase;
    private final JLabel labelUser;
    private final JLabel labelPassword;
    
    public ConnectionView(String title, int height, int width) {
        
        // Appel du constructeur de la classe mère
        super(title, height, width);
        
        this.setLayout(new BorderLayout());
        
        this.boxComponents = new JPanel();
        this.boxComponents.setLayout(new BoxLayout(this.boxComponents, BoxLayout.LINE_AXIS));
        
        // Panneau contenant les labels
        JPanel labelsPanel = new JPanel();
        labelsPanel.setLayout(new GridLayout(3, 1));
        
        // Initialise les labels
        this.labelDatabase = new JLabel("Database :");
        this.labelUser = new JLabel("User :");
        this.labelPassword = new JLabel("Password :");
        
        // Ajout des labels au panneau labels
        labelsPanel.add(this.labelDatabase);
        labelsPanel.add(this.labelUser);
        labelsPanel.add(this.labelPassword);
        
        // Ajout du panneau labels au panneau des composants
        this.boxComponents.add(labelsPanel);
    }
    
    /**
     * Accesseur au panneau des composants
     * @return Panneau des composants 
     */
    public JPanel getBoxComponents() {
        return this.boxComponents;
    }
    
    
}
