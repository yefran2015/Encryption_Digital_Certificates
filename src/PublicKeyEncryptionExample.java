import java.security.*;
import javax.crypto.*;
import java.util.*;
//http://www.javacirecep.com/java-security/java-rsa-encryption-decryption-example/
public class PublicKeyEncryptionExample {
	private static KeyPair keyPair;
	private static String algorithm = "RSA";
	private static int alg_length = 1024;
	
	public static void main(String[] args) throws Exception{
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
		keyPairGenerator.initialize(alg_length);
		keyPair = keyPairGenerator.generateKeyPair();
		final Cipher cipher = Cipher.getInstance(algorithm);
		final String plainText = "This is not encrypted Text!!!";
		
		System.out.println("Original Text: " + plainText);
		System.out.println("Public Key: "+ keyPair.getPublic());
		System.out.println("Private Key: "+ keyPair.getPrivate());
		
		// ENCRYPT using PUBLIC Key:
		cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPublic());
		byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
		String cipherText = new String(Base64.getEncoder().encode(encryptedBytes));
		System.out.println("Encrypted (cipherText): " + cipherText);
		
		// DECRYPT using the PRIVATE Key
		cipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());
		byte[] cipherTextBytes = Base64.getDecoder().decode(cipherText.getBytes());
		byte[] decryptedBytes = cipher.doFinal(cipherTextBytes);
		String decryptedText = new String(decryptedBytes);
		System.out.println("Decrypted (plainText): " + decryptedText);
	}

}
