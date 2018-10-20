
package javanetworking;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;


public abstract class Map{
    private static Image tile = Toolkit.getDefaultToolkit().getImage("Tile.png");
    private static int width = 32;
    private static int height = width;
    private static int rows = Window.getWidth2()/width;
    private static int cols = rows;
    private static int board[][] = new int[rows][cols];
    private static boolean first = true;
    
    
    static void Draw(Graphics2D g, JavaNetworking obj){        
        if(first){
            first = false;
            for(int r = 0;r<rows;r++){
                for(int c = 0;c<cols;c++){
                    int rot = (int) (Math.random()*4+1);
                    rot *= 90;
                    board[r][c] = rot;
                }
            }
        }
        
        for(int r = 0;r<rows;r++){
            for(int c = 0;c<cols;c++){
                draw(g, Window.getX(r*width) + width/2, Window.getY(c*height) + height/2, board[r][c], obj);
            }
        }
        
    }
    private static void draw(Graphics2D g,int xpos,int ypos,double rot,JavaNetworking obj) {
        g.translate(xpos,ypos);
        g.rotate(rot  * Math.PI/180.0);
        
        int width = tile.getWidth(obj);
        int height = tile.getHeight(obj);

        g.drawImage(tile,-width/2,-height/2,width,height,obj);

        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-xpos,-ypos);
    }
    
}
