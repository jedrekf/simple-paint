/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cg_simple_paint;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Queue;

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
    
    Queue<Point> queue = new LinkedList<Point>();
    queue.add(new Point(x, y));
    
    while(!queue.isEmpty()){
        Point p = queue.remove();

        if ((p.x >= 0) && (p.x < w && (p.y >= 0) && (p.y < h))) {
            if (!pixels[p.x][p.y].isColored() && !isBoundary(boundaryColor, pixels[p.x][p.y].getColor())) {
                pixels[p.x][p.y].setColored(true);
                pixels[p.x][p.y].setColor(newColor);

                queue.add(new Point(p.x + 1, p.y));
                queue.add(new Point(p.x - 1, p.y));
                queue.add(new Point(p.x, p.y + 1));
                queue.add(new Point(p.x, p.y - 1));
            }
        }
    
    
    }
    
    updateBufferedImage();
    return image;
    }
    
    private boolean isBoundary(Color boundaryColor, Color c){
        if(boundaryColor.getRed()==c.getRed() && 
                boundaryColor.getGreen()==c.getGreen() &&
                boundaryColor.getBlue()==c.getBlue())
            return true;
        return false;
    }
    
    private void initImage(BufferedImage img){
        w = img.getWidth();
        h = img.getHeight();
        image = img;
        pixels = new FillPixel[h][w];
        
        for(int i=0; i<h; i++){
            for(int j=0; j<w; j++){
                pixels[i][j]= new FillPixel();
                Color c = new Color(image.getRGB(i,j));
                pixels[i][j].setColor(c);
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
