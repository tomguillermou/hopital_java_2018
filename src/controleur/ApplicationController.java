/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import controleur.ConnectionController;
import modele.Connexion;
import vue.ConnectionView;

/**
 * 
 * @author Tom
 */
public class ApplicationController {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        boolean run = true;
        
        // Contrôleur de la fenêtre de connexion
        ConnectionController conController = new ConnectionController();
        // Vue de la fenêtre de connexion
        ConnectionView conView = new ConnectionView(conController, "Connexion", 200, 500);
        
        // Affiche la fenêtre de connexion
        conView.showEFrame();
        
    } 
}
