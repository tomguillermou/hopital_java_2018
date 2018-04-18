/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import modele.*;

/**
 *
 * @author Tom
 */
public class ConnectionController {
    
    // Fenêtre à afficher si on se connecte
    EFrame frameToShow;
    // Booléen qui indique si la fenêtre suivante est affichée
    private boolean frameShown = false;
    
    /**
     * Constructeur surchargé avec la fenêtre suivante
     * @param frameToShow
     */
    public ConnectionController(EFrame frameToShow) {
      this.frameToShow = frameToShow;
    }
    
    public boolean isNextFrameShown() {
        return this.frameShown;
    }
    
    public void ButtonConnectionPressed(String database, String user, String password) {
        
        try {
            // Connexion en local
            Connexion.getInstance().connectLocal(database, user, password);
            
        } catch (ClassNotFoundException ex) {

            // Affiche l'erreur dans la console 
            ex.printStackTrace();

            // Affiche une boîte de dialogue erreur
            JOptionPane.showMessageDialog(null, "Class not found", "Error", JOptionPane.ERROR_MESSAGE);

        } catch (SQLException ex) {

            // Affiche l'erreur dans la console
            ex.printStackTrace();

            // Affiche une boîte de dialogue erreur
            JOptionPane.showMessageDialog(null, "Wrong identifiers", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        // Si la connexion est établie
        if(Connexion.getInstance().isConnected()) {
            
            // Affiche la fenêtre suivante
            frameToShow.showEFrame();
            // La fenêtre suivante est affichée
            frameShown = true;
        }
    }   
}


