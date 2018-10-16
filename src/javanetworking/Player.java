
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
    
    Player(int i){
        speed = 5;
        if(i == 0){
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
            players[i] = new Player(i);
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
    
    static Player GetPlayer(){
        if(JavaNetworking.isClient){
            return players[0];
        }
        else
            return players[1];
    }
    
    void draw(Graphics2D g,JavaNetworking obj){
        g.setColor(Color.red);
        g.drawRect(xpos - 5, ypos - 5, 10, 10);
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
