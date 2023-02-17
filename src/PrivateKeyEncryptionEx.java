import java.security.*;
import javax.crypto.*;
// //http://jexp.ru/index.php/Java_Tutorial/Security/Key_Generator
public class PrivateKeyEncryptionEx {
	static String algorithm = "AES";
	static Key key;
	static Cipher cipher;
	public static void main(String[] args) throws Exception{
		key = KeyGenerator.getInstance(algorithm).generateKey();
		cipher = Cipher.getInstance(algorithm);
		String message = "This is plain text message!!!!";
		byte[] encryptedBytes = encrypt(message);
		System.out.println("Orinignal message: " + message);
		System.out.println("Encription algorithm: " + algorithm);
		System.out.println("Key: " + key);
		System.out.println("Encrypted Text: " + encrypt(message));
		System.out.println("Decrypted Text: " + decrypt(encryptedBytes));
	}
	
	public static byte[] encrypt(String input) throws Exception {
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] inputBytes = input.getBytes();
		return cipher.doFinal(inputBytes);
	}
	public static String decrypt(byte[] encryptedBytes) throws Exception{
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] recoveredBytes = cipher.doFinal(encryptedBytes);
		String decryptedText = new String(recoveredBytes);
		return decryptedText;
	}
}
