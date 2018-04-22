/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import modele.Connexion;
import modele.EButton;
import modele.EFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JSeparator;

/**
 *
 * @author Tom
 */
public class UpdateView extends EFrame {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Eléments graphiques de la fenêtre de mise à jour des données
    private final EButton openTableButton = new EButton("Rafraîchir l'affichage de la table");
    private final JTable table = new JTable();
    private final JScrollPane scrollPane = new JScrollPane(table);
    private final EButton buttonInsert = new EButton("Ajouter");
    private final EButton buttonUpdate = new EButton("Mettre à jour");
    private final EButton buttonDelete = new EButton("Supprimer");
    private final JPanel buttonsPanel = new JPanel(new GridLayout(1, 3));
    // Entrées utilisateur pour mettre à jour les données d'un employé
    private final JTextField fieldNumero = new JTextField();
    private final JTextField fieldNom = new JTextField();
    private final JTextField fieldPrenom = new JTextField();
    private final JTextField fieldAdresse = new JTextField();
    private final JTextField fieldTel = new JTextField();
    private final JLabel lblNumro = new JLabel("Numéro");
    private final JLabel lblNom = new JLabel("Nom");
    private final JLabel lblPrnom = new JLabel("Prénom");
    private final JLabel lblAdresse = new JLabel("Adresse");
    private final JLabel lblTlphone = new JLabel("Téléphone");
    private final JSeparator separator = new JSeparator();
    
    /**
     * Constructeur surchargé
     * @param tittle
     * @param height
     * @param width 
     * @param actionOnClose 
     */
    public UpdateView(String tittle, int height, int width, int actionOnClose) {
        super(tittle, height, width, actionOnClose);
        
        openTableButton.addActionListener(new ButtonOpenTableListener());
        
        table.getSelectionModel().addListSelectionListener(new TableSelection());
        getContentPane().setLayout(new MigLayout("", "[436px,grow][][150.00,grow]", "[][35.00][35.00][35.00][60.00][35.00][][][115.00,grow]"));
        this.getContentPane().add(openTableButton, "cell 0 0,grow");
        this.getContentPane().add(scrollPane, "cell 0 1 1 8,grow");
        
        getContentPane().add(lblNumro, "cell 1 1,alignx trailing");
        getContentPane().add(fieldNumero, "cell 2 1,growx");
        
        getContentPane().add(lblNom, "cell 1 2,alignx trailing");
        getContentPane().add(fieldNom, "cell 2 2,growx");
        
        getContentPane().add(lblPrnom, "cell 1 3,alignx trailing");
        getContentPane().add(fieldPrenom, "cell 2 3,growx");
        
        getContentPane().add(lblAdresse, "cell 1 4,alignx trailing");
        getContentPane().add(fieldAdresse, "cell 2 4,grow");
        
        getContentPane().add(lblTlphone, "cell 1 5,alignx trailing");
        getContentPane().add(fieldTel, "cell 2 5,growx");
        
        buttonInsert.addActionListener(new ButtonInsertListener());
        buttonUpdate.addActionListener(new ButtonUpdateListener());
        buttonDelete.addActionListener(new ButtonDeleteListener());
        
        getContentPane().add(separator, "cell 1 6 2 1,growx");
        
        buttonsPanel.add(buttonInsert);
        buttonsPanel.add(buttonUpdate);
        buttonsPanel.add(buttonDelete);
        this.getContentPane().add(buttonsPanel, "cell 1 7 2 1,grow");
        
    }
    
    private void UpdateTable() {
        try {
            table.setModel(Connexion.getInstance().buildTableModel(Connexion.getInstance().searchQuery("select * from employe")));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Classe interne implémentant l'interface ActionListener
     */
    class ButtonOpenTableListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            UpdateTable();
        }
    }

    /**
     * Classe interne implémentant l'interface ListSelectionListener
     */
    class TableSelection implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent lse) {
            int selectedRowIndex = table.getSelectedRow();
            fieldNumero.setText(table.getValueAt(selectedRowIndex, 0).toString());
            fieldNom.setText(table.getValueAt(selectedRowIndex, 1).toString());
            fieldPrenom.setText(table.getValueAt(selectedRowIndex, 2).toString());
            fieldAdresse.setText(table.getValueAt(selectedRowIndex, 3).toString());
            fieldTel.setText(table.getValueAt(selectedRowIndex, 4).toString());
        }
        
    }
    
    /**
     * Classe interne implémentant l'interface ActionListener
     */
    class ButtonInsertListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            String sqlQuery = "INSERT INTO `employe`(`numero`, `nom`, `prenom`, `adresse`, `tel`) "
                    + "VALUES ("+fieldNumero.getText()+",'"+fieldNom.getText()+"','"+fieldPrenom.getText()+"','"+fieldAdresse.getText()+"','"+fieldTel.getText()+"')";
        
            Connexion.getInstance().executeSqlQuery(sqlQuery, "ajoutées");
            UpdateTable();
        }
    }
    
    /**
     * Classe interne implémentant l'interface ActionListener
     */
    class ButtonUpdateListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            String sqlQuery = "UPDATE `employe` SET `nom`='"+fieldNom.getText()+"',`prenom`='"+fieldPrenom.getText()+"',`adresse`='"+fieldAdresse.getText()+"',`tel`='"+fieldTel.getText()+"'" +
            "WHERE `numero`="+fieldNumero.getText();
            
            Connexion.getInstance().executeSqlQuery(sqlQuery, "mises à jour");
            UpdateTable();
        }
    }
    
    /**
     * Classe interne implémentant l'interface ActionListener
     */
    class ButtonDeleteListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            String sqlQuery = "DELETE FROM `employe` WHERE `numero`="+fieldNumero.getText();
                    
            Connexion.getInstance().executeSqlQuery(sqlQuery, "supprimées");
            UpdateTable();
        }
        
    }
}

