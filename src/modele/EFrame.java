/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import javax.swing.JFrame;

/**
 *
 * @author Tom
 */
public abstract class EFrame extends JFrame {
    
    private final String title;
    private final int height;
    private final int width;
    
    public EFrame(String title, int height, int width) {
        
        this.title = title;
        this.height = height;
        this.width = width;
        
        this.setTitle(this.title);
        this.setSize(this.width, this.height);
        //this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void showEFrame() {
        this.setVisible(true);
    }
    
    public void hideEFrame() {
        this.setVisible(false);
    }
}
