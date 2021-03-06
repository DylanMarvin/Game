/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javanetworking;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientHandler
{
    public static boolean connected = false;
    private static         Socket MyClient;
    private static         BufferedReader br;
    private static         PrintWriter out;
    
	private static String hostIP = null;
	private static int hostPort = -1;
        
//	private static Socket server = null;
//	private static PrintWriter serverOut = null;
//	private static BufferedReader serverIn = null;

	public static void connect(String ip, int port) throws UnknownHostException, IOException
	{
		hostIP = ip;
		hostPort = port;
            MyClient = new Socket(ip, port);
            br = new BufferedReader(new InputStreamReader(MyClient.getInputStream()));
            out = new PrintWriter(MyClient.getOutputStream(), true);
                
                
//		server = new Socket();
//		server.connect(new InetSocketAddress(ip, port), 6000);
//		serverOut = new PrintWriter(server.getOutputStream(), true);
//		serverIn = new BufferedReader(new InputStreamReader(server.getInputStream()));
		connected = true;
		recieveInfo();
	}

	public static void disconnect()
	{
		try
		{
			if (MyClient != null)
				MyClient.close();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		hostIP = null;
		hostPort = -1;
		MyClient = null;
		out = null;
		br = null;
		connected = false;
		JavaNetworking.gameStarted = false;
		JavaNetworking.reset();
	}
    public static void sendInfo()
    {
		if (connected)
		{
			out.println(Player.GetPlayer().getX() + ":" + Player.GetPlayer().getY() + ":" + Player.GetPlayer().getMouseX() + ":" + Player.GetPlayer().getMouseY());
                        out.flush(); 
		}        
    }

	public static void sendDisconnect()
	{
		if (connected)
		{
			out.println("esc");
		}
	}


	private static void recieveInfo()
	{
		new Thread(new Runnable() {

			@Override
			public void run()
			{
				String inputLine;

				try
				{
					while ((inputLine = br.readLine()) != null)
					{
						try
						{
							if (inputLine.equals("esc"))
							{
								disconnect();
								return;
							}
                                                        
							int serverXpos = Integer.parseInt(inputLine.split(":")[0]);
							int serverYpos = Integer.parseInt(inputLine.split(":")[1]);
                                                        int serverMouseX = Integer.parseInt(inputLine.split(":")[2]);
                                                        int serverMouseY = Integer.parseInt(inputLine.split(":")[3]);
                                                        Player.setOpponentPosition(serverXpos, serverYpos,serverMouseX,serverMouseY);
						}
						catch (NumberFormatException e)
						{
							e.printStackTrace();
						}
						catch (NullPointerException e)
						{
							disconnect();
						}
					}
				}
				catch (IOException e)
				{
					disconnect();
				}

			}
		}).start();
	}

	public static boolean isConnected()
	{
		return connected;
	}
}
