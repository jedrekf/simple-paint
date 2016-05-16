/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cg_simple_paint;

import java.awt.Color;

/**
 *
 * @author Jedrek
 */
public class FillPixel {
   private Color color;
   private boolean colored;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isColored() {
        return colored;
    }

    public void setColored(boolean colored) {
        this.colored = colored;
    }
   
   
}
