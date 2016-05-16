/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cg_simple_paint;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author Jedrek
 */
public class RegionFiller {
    
    private static Color white = new Color(255, 255, 255), black = new Color(0,0,0); 
    private BufferedImage image;
    private int w,h;
    private FillPixel[][] pixels;
    
    
    public BufferedImage flood(int x, int y, Color boundaryColor, Color newColor, BufferedImage img){
    initImage(img);
    
    boundaryFill4(x, y, boundaryColor, newColor);
    
    updateBufferedImage();
    return image;
    }
    
    
    private void boundaryFill4(int x, int y, Color boundaryColor, Color newColor){
    // x, y - starting point coordinates
    // boundary - color of the border (stop)
    // new - must be different than boundary
    // and any color already present in the region
        Color c = pixels[x][y].getColor();
        if (c != boundaryColor && c != newColor) {
            //mark pixel as painted
            pixels[x][y].setColored(true);
            pixels[x][y].setColor(newColor);
            if(x+1 < 500 && !pixels[x+1][y].isColored())
                boundaryFill4(x + 1, y, boundaryColor, newColor);
            if(x-1 > 0 && !pixels[x-1][y].isColored())
                boundaryFill4(x - 1, y, boundaryColor, newColor);
            if(y+1 < 400 && !pixels[x][y+1].isColored())
                boundaryFill4(x, y + 1, boundaryColor, newColor);
            if(y-1 > 0 && !pixels[x][y-1].isColored())
                boundaryFill4(x, y - 1, boundaryColor, newColor);
        }
    }
    
    
    private void initImage(BufferedImage img){
        w = img.getWidth();
        h = img.getHeight();
        image = img;
        pixels = new FillPixel[h][w];

        for(int i=0; i<h; i++){
            for(int j=0; j<w; j++){
                pixels[i][j].setColor(new Color(image.getRGB(i,j)));
                pixels[i][j].setColored(false);
            }
        }
    }
    
    private void updateBufferedImage(){
        for(int i=0; i< h; i++){
            for(int j=0; j<w; j++){
                image.setRGB(i, j, pixels[i][j].getColor().getRGB());
            }
        }
    }
}
