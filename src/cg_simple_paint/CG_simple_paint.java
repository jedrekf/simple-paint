/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cg_simple_paint;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jedrek
 */
public class CG_simple_paint {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
            try {
                new PaintWorkshop().setVisible(true);
            } catch (Exception ex) {
                Logger.getLogger(CG_simple_paint.class.getName()).log(Level.SEVERE, null, ex);
            }   
        
    }
    
}
