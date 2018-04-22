/**
 * Contrôleur de la fenêtre de connexion
 */
package controleur;

/**
 * Librairies importées
 */
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modele.*;

/**
 *
 * @author Tom Guillermou
 */
public class ConnectionController {

    // Fenêtre de gestion (s'affiche lorsque la connexion avec la BBD est établie)
    EFrame managerView;
    // Booléen indiquant si la fenêtre de gestion est visible ou non
    private boolean managerViewVisible = false;

    /**
     * Constructeur surchargé
     *
     * @param managerView Fenêtre de gestion de l'application
     */
    public ConnectionController(EFrame managerView) {
        this.managerView = managerView;
    }

    /**
     * Indique si la fenêtre de gestion est affichée ou non
     *
     * @return boolean
     */
    public boolean isManagerViewVisible() {
        return managerViewVisible;
    }

    /**
     * Fonction de contrôle du bouton de connexion de la fenêtre de connexion
     *
     * @param database Nom de la BDD
     * @param user Nom de l'utilisateur
     * @param password Mot de passe de l'utilisateur
     */
    public void ButtonConnectionPressed(String database, String user, String password) {

        try {
            // Connexion en local
            Connexion.getInstance().connectLocal(database, user, password);
        } catch (ClassNotFoundException ex) {
            // Affiche l'erreur dans une boîte de dialogue
            JOptionPane.showMessageDialog(null, "Class not found", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            // Affiche l'erreur dans une boîte de dialogue
            JOptionPane.showMessageDialog(null, "Wrong identifiers", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Si la connexion est établie
        if (Connexion.getInstance().isConnected()) {
            // Affiche la fenêtre de gestion
            managerView.showEFrame();
            // Met à jour le booléen indiquant la visibilité de la fenêtre de gestion
            managerViewVisible = true;
        }
    }
}
