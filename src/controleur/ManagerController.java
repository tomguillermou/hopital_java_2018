/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import vue.ReportingView;
import vue.SearchView;
import vue.UpdateView;

/**
 *
 * @author Tom
 */
public class ManagerController {
    
    private final ReportingView reportingView;
    private final SearchView searchView;
    private final UpdateView updateView;
    
    public ManagerController(ReportingView reportingView, SearchView searchView, UpdateView updateView) {
        
        this.reportingView = reportingView;
        this.searchView = searchView;
        this.updateView = updateView;
    }
    
    public void ButtonReportingPressed() {
        reportingView.showEFrame();
        searchView.hideEFrame();
        updateView.hideEFrame();
    }
    
    public void ButtonSearchPressed() {
        reportingView.hideEFrame();
        searchView.showEFrame();
        updateView.hideEFrame();
    }
    
    public void ButtonUpdatePressed() {
        reportingView.hideEFrame();
        searchView.hideEFrame();
        updateView.showEFrame();
    }
    
}
