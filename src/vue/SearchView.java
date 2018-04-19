package vue;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import modele.Connexion;
import modele.EFrame;

/**
 *
 * @author Tom
 */
public class SearchView extends EFrame {
    
    // Container de la fenêtre principale
    private final Container container;
    
    private final JPanel panelTable = new JPanel(new GridLayout(2, 1));
    private final JPanel panelColumn = new JPanel(new GridLayout(2, 1));
    private final JPanel panelValue = new JPanel(new GridLayout(2, 1));
    
    private final JLabel searchTableLabel = new JLabel("Je recherche dans la table :");
    private final JLabel searchColumnLabel = new JLabel("Je recherche dans la colonne :");
    private final JLabel searchValueLabel = new JLabel("Avec la valeur :");
    
    private final JComboBox boxTable;
    private final JComboBox boxColumn;
    
    private final DefaultComboBoxModel chambreModel = new DefaultComboBoxModel(new String[]{"tout", "code_service", "no_chambre", "surveillant", "nb_lits"});
    private final DefaultComboBoxModel docteurModel = new DefaultComboBoxModel(new String[]{"tout", "numero", "specialite"});
    private final DefaultComboBoxModel employeModel = new DefaultComboBoxModel(new String[]{"tout", "numero", "nom", "prenom", "adresse", "tel"});
    private final DefaultComboBoxModel hospitalisationModel = new DefaultComboBoxModel(new String[]{"tout", "no_malade", "code_service", "no_chambre", "lit"});
    private final DefaultComboBoxModel infirmierModel = new DefaultComboBoxModel(new String[]{"tout", "numero", "code_service", "rotation", "salaire"});
    private final DefaultComboBoxModel maladeModel = new DefaultComboBoxModel(new String[]{"tout", "numero", "nom", "prenom", "adresse", "tel", "mutuelle"});
    private final DefaultComboBoxModel serviceModel = new DefaultComboBoxModel(new String[]{"tout", "code", "nom", "batiment", "directeur"});
    private final DefaultComboBoxModel soigneModel = new DefaultComboBoxModel(new String[]{"tout", "no_docteur", "no_malade"});
    
    private final JTextField fieldValue;
    
    private final JButton buttonSearch = new JButton("Search");
    
    public SearchView(String tittle, int height, int width) {
        
        // Appel le constructeur de la classe mère
        super(tittle, height, width);
        
        // Initialise le container & lui ajoute un FlowLayout
        container = this.getContentPane();
        container.setLayout(new GridLayout(4, 1));

        // ComboBox contenant les choix de recherche que l'ont peut effectuer
        boxTable = new JComboBox(Connexion.getInstance().getTablesNames());
        
        // Ajoute le ActionListener adapté à la ComboBox
        boxTable.addActionListener(new BoxTableListener());
        
        boxColumn = new JComboBox();
        fieldValue = new JTextField();
        
        buttonSearch.addActionListener(new ButtonSearchListener());
        
        panelTable.add(searchTableLabel);
        panelTable.add(boxTable);
        
        panelColumn.add(searchColumnLabel);
        panelColumn.add(boxColumn);
        
        panelValue.add(searchValueLabel);
        panelValue.add(fieldValue);
        
        container.add(panelTable);
        container.add(panelColumn);
        container.add(panelValue);
        container.add(buttonSearch);
    }
    
    //Classe interne implémentant l'interface ItemListener
    class BoxTableListener implements ActionListener{           

        @Override
        public void actionPerformed(ActionEvent ae) {  
            if("chambre".equals(boxTable.getSelectedItem())) {
                boxColumn.setModel(chambreModel);
            }
            else if("docteur".equals(boxTable.getSelectedItem())) {
                boxColumn.setModel(docteurModel);
            }
            else if("employe".equals(boxTable.getSelectedItem())) {
                boxColumn.setModel(employeModel);
            }
            else if("hospitalisation".equals(boxTable.getSelectedItem())) {
                boxColumn.setModel(hospitalisationModel);
            }
            else if("infirmier".equals(boxTable.getSelectedItem())) {
                boxColumn.setModel(infirmierModel);
            }
            else if("malade".equals(boxTable.getSelectedItem())) {
                boxColumn.setModel(maladeModel);
            }
            else if("service".equals(boxTable.getSelectedItem())) {
                boxColumn.setModel(serviceModel);
            }
            else if("soigne".equals(boxTable.getSelectedItem())) {
                boxColumn.setModel(soigneModel);
            }
        }
    }

    class ButtonSearchListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            String tableName = boxTable.getSelectedItem().toString();
            String columnName = boxColumn.getSelectedItem().toString();
            String value = fieldValue.getText();
            
//            System.out.println(tableName + " " + columnName + " " + value);
            
            try {
                Connexion.getInstance().searchTable(tableName, columnName, value);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        
    }

}

