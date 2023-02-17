//http://tutorials.jenkov.com/java-cryptography/signature.html
import java.security.*;
public class DigitalSignature {
	private static final String ALGORITHM = "DSA";
	private static final String SIGNATURE = "SHA256WithDSA";
	public static void main(String[] args) throws Exception {
		String message = "This is my Message!!!";
		Signature signature = Signature.getInstance(SIGNATURE);
		SecureRandom secureRandom = new SecureRandom();
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		
		//initialize the digital signature
		signature.initSign(keyPair.getPrivate(), secureRandom);
		byte[] data = message.getBytes("UTF-8");
		signature.update(data);
		byte[] digitalSignature = signature.sign();
		System.out.println("Create Digital Signature: " +digitalSignature.toString());
		
		Signature signature2 = Signature.getInstance(SIGNATURE);
		signature2.initVerify(keyPair.getPublic());;
		byte[] data2 = message.getBytes("UTF-8");
		signature2.update(data2);
		boolean verified = signature2.verify(digitalSignature);
		System.out.println("Signature Cerifies: "+verified);

	}

}
