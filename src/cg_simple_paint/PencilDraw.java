/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cg_simple_paint;

import java.awt.Color;
import java.awt.image.BufferedImage;
import static java.awt.image.BufferedImage.TYPE_INT_RGB;

/**
 *
 * @author Jedrek
 */
public class PencilDraw {
    private static Color white = new Color(255, 255, 255), black = new Color(0,0,0); 
    private BufferedImage image;
    private int w,h;
    private Color[][] pixels;
    
    public BufferedImage getImage(){
        return this.image;
    }
    /*takes the input image sets it up locally with width height and takes its pix array*/
    private void initImage(BufferedImage img){
        w = img.getWidth();
        h = img.getHeight();
        image = img;
        pixels = new Color[h][w];
        takePixelArrayFromBuffered();
    }
    
    public BufferedImage clear(BufferedImage img){
        initImage(img);
        for(int i=0;i<h;i++){
            for(int j=0;j<w;j++){
                pixels[i][j] = white;
            }
        }
        updateBufferedImage();
        return image;
    }
    
    public BufferedImage addLine(int x1, int y1, int x2, int y2, BufferedImage img){
        initImage(img);
        float m;
        int tempx, tempy;
        int dx = Math.abs(x1-x2);
        int dy = Math.abs(y1-y2);
        if(dx==0 && dy == 0){
            pixels[x1][y1] = black;
            updateBufferedImage();
            return image;
        }
        if(dx>=dy){
            if(x1>x2){
                tempx = x1; 
                x1 = x2;
                x2 = tempx;
                tempy = y1;
                y1 = y2;
                y2 = tempy;
            }
            
            m = (float)(y2-y1)/(float)(x2-x1);
            
            float y = y1;
            for (int x = x1; x <= x2; ++x)
            {
                //putPixel(x, round(y)); different round for m>0 m<0?
                pixels[x][Math.round(y)] = black; 
               y += m;
            }
        }else{
            if(y1>y2){
               tempy = y1;
               y1=y2;
               y2=tempy;
               tempx=x1;
               x1=x2;
               x2=tempx;
            }
            
            m = dx==0 ? 0 : (float)(x2-x1)/(float)(y2-y1);
            
            float x =x1;
            for(int y= y1 ; y< y2;y++){
                pixels[Math.round(x)][y] = black;
                x += m;
            }
        }      
        
        updateBufferedImage();
       return image;
    }
    
    private void drawVerticalLine(int x, int y1, int y2) {
        int bigger = y1>y2 ? y1:y2;
        int smaller = y1>y2 ? y2:y1;
        for(int i = smaller; i<bigger; i++){
            pixels[x][i] = black;
        }
    }
    public BufferedImage addCircle(int cx, int cy, int r, BufferedImage img){
        initImage(img);
            
        int d = (5 - r * 4)/4;
        int x = 0;
        int y = r;
        Color circleColor = Color.white;

        do {
            pixels[cx+x][cy+y] = black;
            pixels[cx+x][cy-y] = black;
            pixels[cx-x][cy+y] = black;
            pixels[cx-x][cy-y] = black;
            pixels[cx+y][cy+x] = black;
            pixels[cx+y][cy-x] = black;
            pixels[cx-y][cy+x] = black;
            pixels[cx-y][cy-x] = black;
            if (d < 0) {
                    d += 2 * x + 1;
            } else {
                    d += 2 * (x - y) + 1;
                    y--;
            }
            x++;
        } while (x <= y);
        
        updateBufferedImage();
       return image;
    }
    
    /*int IntensifyPixel(int x, int y, float thickness, float distance)
    {
        float cov = coverage(thickness, distance);
        if ( cov > 0 )
            putPixel(x, y, lerp(BKG_COLOR, LINE_COLOR, cov));
        return cov;
    }
    int ThickAntialiasedLine(int x1, int y1, int x2, int y2, float thickness)
    {
        //initial values in Bresenham;s algorithm
        int dx = x2 - x1, dy = y2 - y1;
        int dE = 2*dy, dNE = 2*(dy - dx);
        int d = 2*dy - dx;
        int two_v_dx = 0; //numerator, v=0 for the first pixel
        float invDenom = 1/(2*sqrt(dx*dx + dy*dy)); //inverted denominator
        float two_dx_invDenom = 2*dx*invDenom; //precomputed constant
        int x = x1, y = y1;
        int i;
        IntensifyPixel(x, y, thickness, 0);
        for (i=1; IntensifyPixel(x, y+i, thickness, i*two_dx_invDenom); ++i);
        for (i=1; IntensifyPixel(x, y-i, thickness, i*two_dx_invDenom); ++i);
        while ( x < x2 )
        {
            ++x;
            if ( d < 0 ) // move to E
            {
                two_v_dx = d + dx;
                d += dE;
            }
            else // move to NE
            {
                two_v_dx = d-dx;
                d += dNE;
                ++y;
            }
            // Now set the chosen pixel and its neighbors
            IntensifyPixel(x, y, thickness, two_v_dx*invDenom);
            for (i=1; IntensifyPixel(x, y+i, thickness, i*two_dx_invDenom - two_v_dx*invDenom); ++i);
            for (i=1; IntensifyPixel(x, y-i, thickness, i*two_dx_invDenom + two_v_dx*invDenom); ++i);
        }

    }*/
    
    private void takePixelArrayFromBuffered(){
        for(int i=0; i<h; i++){
            for(int j=0; j<w; j++){
                pixels[i][j] = new Color(image.getRGB(i,j));
            }
        }
    }
    
    private void updateBufferedImage(){
        for(int i=0; i< h; i++){
            for(int j=0; j<w; j++){
                image.setRGB(i, j, pixels[i][j].getRGB());
            }
        }
    }


}
