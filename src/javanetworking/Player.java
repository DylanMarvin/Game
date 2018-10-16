
package javanetworking;

import java.awt.Color;
import java.awt.Graphics2D;



public class Player {
    private static Player players[] = new Player[2];
    private int xpos;
    private int ypos;
    private int speed;
    private int health;
    private double angle;
    private int xVel;
    private int yVel;
    private int playerID;
    private boolean keyDown[] = new boolean[4];
    
    Player(){
        speed=5;
        if(JavaNetworking.isClient){
            xpos=200;
            ypos=400;
            playerID=0;
        }
        else{
            xpos=500;
            ypos=400;
            playerID=1;
        }
        keyDown[0] = false;
        keyDown[1] = false;
        keyDown[2] = false;
        keyDown[3] = false;
    }
    
   
    static void CreatePlayers(){
        for(int i = 0;i<players.length;i++){
            players[i] = new Player();
        }
    }
    
    static void Draw(Graphics2D g,JavaNetworking obj){
        for(int i = 0;i<players.length;i++){
            players[i].draw(g,obj);
        }
    }
    
    static void Tick(){
        for(int i = 0;i<players.length;i++){
            players[i].tick();
        }
    }
    
    
    void draw(Graphics2D g,JavaNetworking obj){
        g.setColor(Color.red);
        g.drawRect(xpos, ypos, 10, 10);
    }
    
    
    void tick(){
        xpos+=xVel;
        ypos+=yVel;
    }
    void setVel(String dir){
        
        if(dir.equals("UP")){
            yVel = -speed;
            keyDown[0] = true;
        }
        if(dir.equals("DOWN")){
            yVel = speed;
            keyDown[1] = true;
        }
        if(dir.equals("LEFT")){
            xVel = -speed;
            keyDown[2] = true;
        }
        if(dir.equals("RIGHT")){
            xVel = speed;
            keyDown[3] = true;
        }
        
    }
    void resetVel(String dir){
        
        if(dir.equals("UP")){
            keyDown[0] = false;
        }
        if(dir.equals("DOWN")){
            keyDown[1] = false;
        }
        if(dir.equals("LEFT")){
            keyDown[2] = false;
        }
        if(dir.equals("RIGHT")){
            keyDown[3] = false;
        }
        
        if(keyDown[0] == false){
            yVel = 0;
        }
        if(keyDown[1] == false){
            yVel = 0;
        }       
        if(keyDown[2] == false){
            xVel = 0;
        }
        if(keyDown[3] == false){
            xVel = 0;
        }
        if(keyDown[0] == true && keyDown[1] == false){
            yVel = -speed;
        }
        if(keyDown[0] == false && keyDown[1] == true){
            yVel = speed;
        }
        if(keyDown[2] == true && keyDown[3] == false){
            xVel = -speed;
        }
        if(keyDown[2] == false && keyDown[3] == true){
            xVel = speed;
        }
        
    }
}
