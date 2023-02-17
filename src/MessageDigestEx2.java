import java.security.MessageDigest;
public class MessageDigestEx2 {
	private static final String ALGORITHM = "SHA-512"; 
	public static void main(String[] args) throws Exception{
		String plainText = "This is Plain text Message!!!";
		MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM);
		byte[] encodedHash = messageDigest.digest(plainText.getBytes());
		String encryptedString = bytesToHex(encodedHash);
		System.out.println("Original Text: " + plainText);
		System.out.println("Message Digest: " + encryptedString);

	}
	private static String bytesToHex(byte[] hash) {
		StringBuffer hexString = new StringBuffer();
		for(int i=0; i<hash.length; i++) {
			String hex = Integer.toHexString(0xff & hash[i]);
			if(hex.length() == 1)
				hexString.append('0');
			hexString.append(hex);
		}
		return hexString.toString();
	}

}
