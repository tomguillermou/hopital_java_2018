/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import modele.*;

/**
 *
 * @author Tom
 */
public class ConnectionController {
    
    private final EButton buttonConnection;
    private final JPanel inputsPanel;
    private final JPanel buttonConnectionPanel;
    private final JTextField fieldDatabase;
    private final JTextField fieldUser;
    private final JTextField fieldPassword;
    
    /**
     * Constructeur
     */
    public ConnectionController() {
        
        // Panneau contenant les entrées utilisateur
        this.inputsPanel = new JPanel();
        this.inputsPanel.setLayout(new GridLayout(3, 1));
        
        // Panneau contenant le bouton de connexion
        this.buttonConnectionPanel = new JPanel();
        
        // Entrées
        this.fieldDatabase = new JTextField();
        this.fieldUser = new JTextField();
        this.fieldPassword = new JTextField();
        
        // Ajout des entrées au panneau correspondant
        this.inputsPanel.add(this.fieldDatabase);
        this.inputsPanel.add(this.fieldUser);
        this.inputsPanel.add(this.fieldPassword);
        
        // Initialise le bouton de connexion
        this.buttonConnection = new EButton("Connexion");
        
        // Ajoute le bouton au panneau contenant ce bouton
        this.buttonConnectionPanel.add(this.buttonConnection);
        
        // Ajoute un ActionListener au bouton de connexion
        this.buttonConnection.addActionListener(new ButtonConnectionListener());
    }

    /**
     *
     * @return Panneau contenant les entrées utilisateur
     */
    public JPanel getInputsPanel() {
        return this.inputsPanel;
    }
    
    /**
     *
     * @return Panneau contenant le bouton de connection
     */
    public JPanel getButtonConnectionPanel() {
        return this.buttonConnectionPanel;
    }
    
    class ButtonConnectionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            
            String database = fieldDatabase.getText();
            String user = fieldUser.getText();
            String password = fieldPassword.getText();
            Connexion connexion = Connexion.getInstance();
            
            // Connexion à la base de données
            try {
                // On tente de se connecter
                connexion.connectLocal(database, user, password);
                connexion.statementQuery("select * from books");
                
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
                
            } catch (SQLException ex) {
                // Affiche une boîte de dialogue sur l'erreur de connexion
                JOptionPane.showMessageDialog(null, "Wrong database name/login/password", "Erreur de connexion", JOptionPane.ERROR_MESSAGE);
            }
            
            //TODO Afficher la fen^tre suivante
        }
    }

}


