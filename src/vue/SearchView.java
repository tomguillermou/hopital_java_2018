package vue;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import modele.Connexion;
import modele.EFrame;

/**
 *
 * @author Tom
 */
public class SearchView extends EFrame {
    
    // Eléments graphiques pour la fenêtre de recherche
    private final Container container;
    private final JPanel panelTable = new JPanel(new GridLayout(2, 1));
    private final JPanel panelColumn = new JPanel(new GridLayout(2, 1));
    private final JPanel panelValue = new JPanel(new GridLayout(2, 1));
    private final JLabel searchTableLabel = new JLabel("Je recherche dans la table :");
    private final JLabel searchColumnLabel = new JLabel("Je recherche dans la colonne :");
    private final JLabel searchValueLabel = new JLabel("Avec la valeur :");
    private final JComboBox comboBoxTable;
    private final JComboBox comboBoxColumn = new JComboBox();
    private final JTextField fieldValue = new JTextField();
    private final JButton buttonSearch = new JButton("Search");
    private final JTable table = new JTable();
    private final JScrollPane scrollPane = new JScrollPane(table);
    
    // Modèle de ComboBox pour sélectionner les colonnes des différentes tables de la BDD
    private final DefaultComboBoxModel chambreModel = new DefaultComboBoxModel(new String[]{"tout", "code_service", "no_chambre", "surveillant", "nb_lits"});
    private final DefaultComboBoxModel docteurModel = new DefaultComboBoxModel(new String[]{"tout", "numero", "specialite"});
    private final DefaultComboBoxModel employeModel = new DefaultComboBoxModel(new String[]{"tout", "numero", "nom", "prenom", "adresse", "tel"});
    private final DefaultComboBoxModel hospitalisationModel = new DefaultComboBoxModel(new String[]{"tout", "no_malade", "code_service", "no_chambre", "lit"});
    private final DefaultComboBoxModel infirmierModel = new DefaultComboBoxModel(new String[]{"tout", "numero", "code_service", "rotation", "salaire"});
    private final DefaultComboBoxModel maladeModel = new DefaultComboBoxModel(new String[]{"tout", "numero", "nom", "prenom", "adresse", "tel", "mutuelle"});
    private final DefaultComboBoxModel serviceModel = new DefaultComboBoxModel(new String[]{"tout", "code", "nom", "batiment", "directeur"});
    private final DefaultComboBoxModel soigneModel = new DefaultComboBoxModel(new String[]{"tout", "no_docteur", "no_malade"});
    
    public SearchView(String tittle, int height, int width) {
        
        // Appel le constructeur de la classe mère
        super(tittle, height, width);
        
        // Initialise le container & lui ajoute un FlowLayout
        container = this.getContentPane();
        container.setLayout(new GridLayout(5, 1));

        // ComboBox contenant les choix de recherche que l'ont peut effectuer
        comboBoxTable = new JComboBox(Connexion.getInstance().getTablesNames());
        
        // Ajoute le ActionListener adapté à la ComboBox
        comboBoxTable.addActionListener(new BoxTableListener());
        
        buttonSearch.addActionListener(new ButtonSearchListener());
        
        panelTable.add(searchTableLabel);
        panelTable.add(comboBoxTable);
        
        panelColumn.add(searchColumnLabel);
        comboBoxColumn.setModel(chambreModel); // Choix par défaut, empêche NullPointerException
        panelColumn.add(comboBoxColumn);
        
        panelValue.add(searchValueLabel);
        panelValue.add(fieldValue);
        
        container.add(panelTable);
        container.add(panelColumn);
        container.add(panelValue);
        container.add(buttonSearch);
        container.add(scrollPane);
    }
    
    /**
     * Classe interne implémentant l'interface ItemListener
     */
    class BoxTableListener implements ActionListener{           
        
        @Override
        public void actionPerformed(ActionEvent ae) { 
            
            // Affecte le modèle de la seconde ComboBox en fonction du choix fait dans la première ComboBox
            if("chambre".equals(comboBoxTable.getSelectedItem())) {
                comboBoxColumn.setModel(chambreModel);
            }
            else if("docteur".equals(comboBoxTable.getSelectedItem())) {
                comboBoxColumn.setModel(docteurModel);
            }
            else if("employe".equals(comboBoxTable.getSelectedItem())) {
                comboBoxColumn.setModel(employeModel);
            }
            else if("hospitalisation".equals(comboBoxTable.getSelectedItem())) {
                comboBoxColumn.setModel(hospitalisationModel);
            }
            else if("infirmier".equals(comboBoxTable.getSelectedItem())) {
                comboBoxColumn.setModel(infirmierModel);
            }
            else if("malade".equals(comboBoxTable.getSelectedItem())) {
                comboBoxColumn.setModel(maladeModel);
            }
            else if("service".equals(comboBoxTable.getSelectedItem())) {
                comboBoxColumn.setModel(serviceModel);
            }
            else if("soigne".equals(comboBoxTable.getSelectedItem())) {
                comboBoxColumn.setModel(soigneModel);
            }
        }
    }
    
    /**
     * Classe interne implémentant l'interface ActionListener
     */
    class ButtonSearchListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            
            // Entrées utilisateur
            String tableName = comboBoxTable.getSelectedItem().toString();
            String columnName = comboBoxColumn.getSelectedItem().toString();
            String value = fieldValue.getText();
            
            try {
                table.setModel(Connexion.getInstance().buildTableModel(Connexion.getInstance().searchTable(tableName, columnName, value)));
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        
    }

}

