/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

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
        ReportingView reportingView = new ReportingView("Reporting", 500, 500);
        
        // Vue de la fenêtre de recherche
        SearchView searchView = new SearchView("Search", 500, 500);
        
        // Vue de la fenêtre d'update
        UpdateView updateView = new UpdateView("Update", 500, 500);
        
        // Contrôleur de la fenêtre de gestion
        ManagerController manangerController = new ManagerController(reportingView, searchView, updateView);
        // Vue de la fenêtre de gestion
        ManagerView managerView = new ManagerView(manangerController, "Gestion du centre hospitalier", 500, 500);
        
        // Contrôleur de la fenêtre de connexion
        ConnectionController connectionController = new ConnectionController(managerView);
        // Vue de la fenêtre de connexion
        ConnectionView connectionView = new ConnectionView(connectionController, "Connexion", 200, 500);
        
        // Affiche la fenêtre de connexion
        connectionView.showEFrame();
        
    } 
}
