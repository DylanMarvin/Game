
package javanetworking;


import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;


public abstract class Map{
    private static int rows = Window.getWidth2()/32;
    private static int cols = rows;
    private static int board[][] = new int[rows][cols];
    

    private static Image tile = Toolkit.getDefaultToolkit().getImage("tile.png");;
    
    
    static void Draw(Graphics2D g, JavaNetworking obj){
        if(Window.animateFirstTime){
            for(int r = 0;r<rows;r++){
                for(int c = 0;c<cols;c++){
                //int rot = (int) (Math.random()*4+1);
                //rot*=90;
                //board[r][c] = rot;
                board[r][c] = 0;
            }
        }
        }

        
        for(int r = 0;r<rows;r++){
            for(int c = 0;c<cols;c++){
                g.translate((r*32-16),(c*32-16));
                //g.rotate(board[r][c]  * Math.PI/180.0);

                
                g.drawImage(tile, Window.getX(r*32+16), Window.getY(c*32+16), obj);
                

               // g.rotate(-board[r][c]  * Math.PI/180.0);
                g.translate(-(r*32-16),-(c*32-16));
            }
        }
        
    }
    
}
