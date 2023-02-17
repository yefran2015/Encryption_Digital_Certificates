import java.security.MessageDigest;

public class MessageDigestEx {
	private static final String ALGORITHM = "SHA-512"; 
	public static void main(String[] args) throws Exception{
		String plainText = "This is Plain Text Message!!!";
		MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM);
		messageDigest.update(plainText.getBytes());
		String encyptedText = new String(messageDigest.digest());
		System.out.println("Plain Text: "+plainText);
		System.out.println("Message Digest: " + encyptedText);
	}

}
