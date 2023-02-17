package ClientServerEncryptionPair;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

public class ServerWithEncryption {
	private int serverPort = 8181;
	private DataOutput toClient;
	private DataInput fromClient;
	private KeyPairGenerator kpg;
	private KeyPair kp;
	Cipher cipher;
	Key pubKey;
	Key privKey;
	
	
	public static void main(String[] args) {
		new ServerWithEncryption();
	}
	
	ServerWithEncryption(){
		startServer();	
		Security.addProvider(new com.sun.crypto.provider.SunJCE());
	}
	
	ServerWithEncryption(int serverPort){
		this.serverPort = serverPort;
		startServer();
	}
	
	private void startServer(){
		makePublicPrivateKeysRSA();
		try {
			//Creating the server socket
			ServerSocket serverSocket = new ServerSocket(serverPort);
			System.out.println("Server Started at port No: "+ serverPort);
			while(true){
				//Listen for new connection request
				Socket client = serverSocket.accept();
				toClient = new DataOutputStream(client.getOutputStream());
				toClient.writeUTF("You connected to server at port No: " + serverPort);
			}
			
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
	private void makePublicPrivateKeysRSA(){
		try {
			kpg = KeyPairGenerator.getInstance("RSA");
		    kpg.initialize(2048);
		    kp = kpg.genKeyPair();
		    pubKey = kp.getPublic();
		    privKey = kp.getPrivate();
		    
		    System.out.println(pubKey + "\n" +privKey);
		    
		    cipher = Cipher.getInstance("RSA");
	//	    String string = new String("Hello");
		    
		    cipher.init(Cipher.ENCRYPT_MODE, pubKey);
		    
		    
		//    byte[] encryptedString = cipher.doFinal(string.getBytes());
		  //  System.out.println("Encrypted: "+ new String(encryptedString));
		    
		    cipher.init(Cipher.DECRYPT_MODE, privKey);
		    //System.out.println("Decrypted: "+ new String(cipher.doFinal(encryptedString)));
		    
		    
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException  e) {
			e.printStackTrace();
		}
	}
}
