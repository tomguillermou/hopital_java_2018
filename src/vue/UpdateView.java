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
    private final JPanel inputsPanel = new JPanel(new GridLayout(1, 5));
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
        
        inputsPanel.add(fieldNumero);
        inputsPanel.add(fieldNom);
        inputsPanel.add(fieldPrenom);
        inputsPanel.add(fieldAdresse);
        inputsPanel.add(fieldTel);
        
        buttonInsert.addActionListener(new ButtonInsertListener());
        buttonUpdate.addActionListener(new ButtonUpdateListener());
        buttonDelete.addActionListener(new ButtonDeleteListener());
        
        buttonsPanel.add(buttonInsert);
        buttonsPanel.add(buttonUpdate);
        buttonsPanel.add(buttonDelete);
        getContentPane().setLayout(new MigLayout("", "[436px]", "[][150.00px,grow,fill][66px][bottom]"));
        this.getContentPane().add(openTableButton, "cell 0 0,grow");
        this.getContentPane().add(scrollPane, "cell 0 1,grow");
        this.getContentPane().add(inputsPanel, "cell 0 2,grow");
        this.getContentPane().add(buttonsPanel, "cell 0 3,grow");
        
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

