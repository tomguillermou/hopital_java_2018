/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import javax.swing.JButton;

/**
 *
 * @author Tom
 */
public class EButton extends JButton {
    
    private String name;
    
    public EButton(String name) {
        super(name);
        this.name = name;
    }
    
}
