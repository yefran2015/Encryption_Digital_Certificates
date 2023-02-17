package ClientServerEncryptionPair;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientWithEncryption implements Runnable{
	//Indicate server address(IP or name) and port
	private String serverAddress = "localhost";
	private int serverPort = 8181;
	private DataInputStream fromServer;
	private DataOutputStream toServer;
	
	public static void main(String[] args) {
		new ClientWithEncryption();
	}

	ClientWithEncryption(){
		connectToServer();
	}
	ClientWithEncryption(String serverAddress, int serverPort){
		this.serverAddress = serverAddress;
		this.serverPort = serverPort;
		connectToServer();
	}
	public void connectToServer(){
		//Creating the socket to connect to the server
		try {
			Socket socket;
			socket = new Socket(serverAddress, serverPort);
			
			//Creating input stream from server
			fromServer = new DataInputStream(socket.getInputStream());
			
			//Creating an output stream to server
			toServer = new DataOutputStream(socket.getOutputStream());
			
		} catch (UnknownHostException e) { // also can be used just single: (Exception e)
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		}
		
		//Control each  thread separately
		Thread thread = new Thread(this);
		thread.start();
	}

	public void run() {
		try {
			System.out.println("From Server: "+fromServer.readUTF());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
