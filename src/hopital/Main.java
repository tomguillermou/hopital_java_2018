/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hopital;

import controleur.ConnectionController; 
import java.awt.BorderLayout;
import vue.ConnectionView;

/**
 * 
 * @author Tom
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Fenêtre de connexion & son contrôleur
        ConnectionView conView = new ConnectionView("Connexion", 250, 500);
        ConnectionController conController = new ConnectionController();
        
        // Ajout du panneau des entrées au panneau des composants
        conView.getBoxComponents().add(conController.getInputsPanel());
        conView.getContentPane().add(conView.getBoxComponents(), BorderLayout.CENTER);
        
        // Ajout du bouton de connexion au panneau principal
        conView.getContentPane().add(conController.getButtonConnectionPanel(), BorderLayout.SOUTH);
        
        // Affiche la fenêtre de connexion
        conView.showEFrame();

    }
    
}
