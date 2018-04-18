/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import controleur.ManagerController;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modele.EButton;
import modele.EFrame;

/**
 *
 * @author Tom
 */
public class ManagerView extends EFrame {
    
    // Contrôleur de la vue
    private final ManagerController managerController;
    
    // Boutons présents dans la fenêtre
    private final EButton buttonReporting;
    private final EButton buttonSearch;
    private final EButton buttonUpdate;
    
    public ManagerView(ManagerController managerController, String title, int height, int width) {
        
        // Appel le constructeur de la classe mère
        super(title, height, width);
        
        // Lie le contrôleur de la fenêtre avec celui passé en paramètre
        this.managerController = managerController;
        
        // Ajout d'un GridLayout au panneau principal
        this.setLayout(new GridLayout(3, 1));
        
        // Bouton Reporting
        buttonReporting = new EButton("Reporting");
        buttonReporting.addActionListener(new ButtonReportingListener());
        this.getContentPane().add(buttonReporting);
        
        // Bouton Search
        buttonSearch = new EButton("Search");
        buttonSearch.addActionListener(new ButtonSearchListener());
        this.getContentPane().add(buttonSearch);
        
        // Bouton Update
        buttonUpdate = new EButton("Update");
        buttonUpdate.addActionListener(new ButtonUpdateListener());
        this.getContentPane().add(buttonUpdate);
    }
    
    /*
    * Listener du bouton Reporting
    */
    class ButtonReportingListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            managerController.ButtonReportingPressed();
        }
    }
    
    /*
    * Listener du bouton Search
    */
    class ButtonSearchListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            managerController.ButtonSearchPressed();
        }
    }
    
    /*
    * Listener du bouton Update
    */
    class ButtonUpdateListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            managerController.ButtonUpdatePressed();
        }
    }
    
}
