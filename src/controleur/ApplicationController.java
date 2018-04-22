/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import javax.swing.JFrame;
import vue.ConnectionView;
import vue.ManagerView;
import vue.ReportingView;
import vue.SearchView;
import vue.UpdateView;

/**
 * 
 * @author Tom
 */
public class ApplicationController {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Vue de la fenêtre de reporting
        ReportingView reportingView = new ReportingView("Reporting", 70, 500, JFrame.HIDE_ON_CLOSE);
        
        // Vue de la fenêtre de recherche
        SearchView searchView = new SearchView("Search", 180, 500, JFrame.HIDE_ON_CLOSE);
        searchView.setResizable(false);
        
        // Vue de la fenêtre d'update
        UpdateView updateView = new UpdateView("Mise à jour", 500, 800, JFrame.HIDE_ON_CLOSE);
        
        // Contrôleur de la fenêtre de gestion
        ManagerController manangerController = new ManagerController(reportingView, searchView, updateView);
        // Vue de la fenêtre de gestion
        ManagerView managerView = new ManagerView(manangerController, "Gestion du centre hospitalier", 300, 375, JFrame.EXIT_ON_CLOSE);
        managerView.setResizable(false);
        
        // Contrôleur de la fenêtre de connexion
        ConnectionController connectionController = new ConnectionController(managerView);
        // Vue de la fenêtre de connexion
        ConnectionView connectionView = new ConnectionView(connectionController, "Connexion", 200, 300, JFrame.EXIT_ON_CLOSE);
        connectionView.setResizable(false);
        
        // Affiche la fenêtre de connexion
        connectionView.showEFrame();
        
    } 
}
