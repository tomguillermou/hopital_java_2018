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
    
    private boolean nextFrameShown = false;
    
    // Fenêtre à afficher si on se connecte
    EFrame nextFrame;
    
    /**
     * Constructeur surchargé avec la fenêtre suivante
     * @param nextFrame
     */
    public ConnectionController(EFrame nextFrame) {
      this.nextFrame = nextFrame;
    }
    
    public boolean isNextFrameShown() {
        return this.nextFrameShown;
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
            nextFrame.showEFrame();
            nextFrameShown = true;
        }
    }   
}


