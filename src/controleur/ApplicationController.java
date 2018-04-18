/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import vue.ConnectionView;
import vue.ManagerView;

/**
 * 
 * @author Tom
 */
public class ApplicationController {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Vue de la fenêtre de gestion principale
        ManagerView nFrame = new ManagerView("Next Frame", 300, 300);
        
        // Contrôleur de la fenêtre de connexion
        ConnectionController conController = new ConnectionController(nFrame);
        // Vue de la fenêtre de connexion
        ConnectionView conView = new ConnectionView(conController, "Connexion", 200, 500);
        
        // Affiche la fenêtre de connexion
        conView.showEFrame();
        
    } 
}
