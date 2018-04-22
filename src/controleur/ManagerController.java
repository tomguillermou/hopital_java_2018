/**
 * Contrôleur de la fenêtre de gestion des modules
 */
package controleur;

import vue.ReportingView;
import vue.SearchView;
import vue.UpdateView;

/**
 * Contrôleur de la fenêtre de gestion des modules
 *
 * @author Tom Guillermou
 */
public class ManagerController {

    // Fenêtres des modules de l'application de gestion
    private final ReportingView reportingView;
    private final SearchView searchView;
    private final UpdateView updateView;

    /**
     * Constructeur surchargé
     *
     * @param reportingView Fenêtre du module Reporting
     * @param searchView Fenetre du module Search
     * @param updateView Fenêtre du module Update
     */
    public ManagerController(ReportingView reportingView, SearchView searchView, UpdateView updateView) {
        // Modules de l'application de gestion
        this.reportingView = reportingView;
        this.searchView = searchView;
        this.updateView = updateView;
    }

    /**
     * Affiche la fenêtre du module Reporting
     */
    public void ButtonReportingPressed() {
        reportingView.showEFrame();
        searchView.hideEFrame();
        updateView.hideEFrame();
    }

    /**
     * Affiche la fenêtre du module Search
     */
    public void ButtonSearchPressed() {
        reportingView.hideEFrame();
        searchView.showEFrame();
        updateView.hideEFrame();
    }

    /**
     * Affiche la fenêtre du module Update
     */
    public void ButtonUpdatePressed() {
        reportingView.hideEFrame();
        searchView.hideEFrame();
        updateView.showEFrame();
    }

}
