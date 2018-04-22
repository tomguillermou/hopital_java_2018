/**
 * Contrôleur principal de l'application de gestion d'un hôpital
 */
package controleur;

/**
 * Librairies importées
 */
import javax.swing.JFrame;
import vue.*;

/**
 * Classe ApplicationController qui gère l'application de gestion d'un hôpital
 *
 * @author Tom Guillermou, Maxime Michel
 */
public class ApplicationController {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // Vue de la fenêtre de reporting (devient invisible quand on la ferme)
        ReportingView reportingView = new ReportingView("Reporting", 70, 500, JFrame.HIDE_ON_CLOSE);

        // Vue de la fenêtre de recherche (devient invisible quand on la ferme)
        SearchView searchView = new SearchView("Search", 180, 500, JFrame.HIDE_ON_CLOSE);
        searchView.setResizable(false); // taille non modifiable

        // Vue de la fenêtre d'update (devient invisible quand on la ferme)
        UpdateView updateView = new UpdateView("Mise à jour", 500, 800, JFrame.HIDE_ON_CLOSE);

        // Contrôleur de la fenêtre de gestion
        ManagerController managerController = new ManagerController(reportingView, searchView, updateView);

        // Vue de la fenêtre de gestion (ferme l'application quand on la ferme)
        ManagerView managerView = new ManagerView(managerController, "Gestion du centre hospitalier", 300, 375, JFrame.EXIT_ON_CLOSE);
        managerView.setResizable(false); // taille non modifiable

        // Contrôleur de la fenêtre de connexion 
        ConnectionController connectionController = new ConnectionController(managerView);

        // Vue de la fenêtre de connexion (ferme l'application quand on la ferme)
        ConnectionView connectionView = new ConnectionView(connectionController, "Connexion", 200, 300, JFrame.EXIT_ON_CLOSE);
        connectionView.setResizable(false);  // taille non modifiable

        // Affiche la fenêtre de connexion
        connectionView.showEFrame();

    }
}
