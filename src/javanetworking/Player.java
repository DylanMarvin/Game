
package javanetworking;

import java.awt.Color;
import java.awt.Graphics2D;



public class Player {
    private static Player players[] = new Player[2];
    private int xpos;
    private int ypos;
    private int mouseX;
    private int mouseY;
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
            playerID=i;
        }
        else{
            xpos=500;
            ypos=400;
            playerID=i;
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
            if(players[i]==null)
                break;
            players[i].tick();
        }
    }
    
    static Player GetPlayer(){
        if(JavaNetworking.isClient)
            return players[0];
        else
            return players[1];
    }
    static void setOpponentPosition(int xpos,int ypos,int mouseX,int mouseY){
        if(JavaNetworking.isClient){
            players[1].xpos = xpos;
            players[1].ypos = ypos;
            players[1].mouseX = mouseX;
            players[1].mouseY = mouseY;
        }
        else if(JavaNetworking.isClient==false){
            players[0].xpos = xpos;
            players[0].ypos = ypos;
            players[0].mouseX = mouseX;
            players[0].mouseY = mouseY;
            
        }
        else
            System.out.println("Error setting opponent position");
    }
    void draw(Graphics2D g,JavaNetworking obj){
        if(playerID==0)
            g.setColor(Color.red);
        else if(playerID==1)
            g.setColor(Color.blue);
        
        g.setColor(Color.green);
        g.drawLine(xpos, ypos, mouseX, mouseY);
        
        g.drawRect(xpos - 5, ypos - 5, 10, 10);
    }
       
    void tick(){
        xpos+=xVel;
        ypos+=yVel;
        if(playerID==0){
            ClientHandler.sendInfo();
        }
        else
        {
            ServerHandler.sendInfo();
        }
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
    int getX(){
        return xpos;
    }
    int getY(){
        return ypos;
    }
    int getMouseX(){
        return mouseX;
    }
    int getMouseY(){
        return mouseY;
    }
    void setMousePos(int _mouseX,int _mouseY){
        mouseX = _mouseX;
        mouseY = _mouseY;
    }
    
    
}
