
package javanetworking;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;


public abstract class Map{
    private static int rows = 30;
    private static int cols = rows;
    private static int board[][] = new int[rows][cols];
    private static boolean first = true;
    private static Image tile = Toolkit.getDefaultToolkit().getImage("Tile.png");;
    
    
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

               g.setColor(Color.red);
               g.fillRect(-50,-50,Window.getWidth2()+100,Window.getHeight2()+100);
        
        for(int r = 0;r<rows;r++){
            for(int c = 0;c<cols;c++){
                draw(g, Window.getX(r*32) + 16, Window.getY(c*32) + 16, board[r][c], obj);
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
