package medios.cmmn.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

public class MediosCryptoUtil {

	private static final String defaultKey = "tkeksqjqdlswjsrnrwlqkddmlfydnjsdusgkqghlzldlqslek";
	
	public static String encodeHexString(String base64EncodedKey) {
		return encodeHexString(Base64.decodeBase64(base64EncodedKey.getBytes()));
	}
	
	public static String encodeHexString(byte keyBytes[]) {
	    return new String(Hex.encodeHex(keyBytes));
	}
	
	public static String decodeHexString(String hexStringKey) {
		byte hexBytes[] = new byte[hexStringKey.length() / 2];
		int j = 0;
		for(int i = 0; i < hexStringKey.length(); i += 2)
			hexBytes[j++] = (byte)(Integer.parseInt(hexStringKey.substring(i, i + 2), 16) & 0xff);
		
		return decodeHexString(hexBytes);
	}
	
	public static String decodeHexString(byte hexBytes[]) {
		return new String(Base64.encodeBase64(hexBytes));
	}
	
	public static String generateKey()
			throws NoSuchAlgorithmException {
		KeyGenerator generator = KeyGenerator.getInstance("AES");
		generator.init(128);
		Key createkey = generator.generateKey();
		byte keyBytes[] = createkey.getEncoded();
		String base64EncodedKey = new String(Base64.encodeBase64(keyBytes));
		return base64EncodedKey;
	}
	
	public static String generateHexStringKey()
			throws NoSuchAlgorithmException {
		return encodeHexString(generateKey());
	}
	
	public static String cryptoEncrypt(String base64EncodedKey, String message)
			throws NoSuchAlgorithmException, NoSuchPaddingException
				, InvalidKeyException, InvalidAlgorithmParameterException
				, IllegalStateException, IllegalBlockSizeException
				, BadPaddingException, UnsupportedEncodingException {
		javax.crypto.SecretKey key = new SecretKeySpec(Base64.decodeBase64(base64EncodedKey.getBytes()), "AES");
		java.security.spec.AlgorithmParameterSpec iv = new IvParameterSpec(Base64.decodeBase64(base64EncodedKey.getBytes()));
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(1, key, iv);
		byte keyBytes[] = cipher.doFinal(message.getBytes("UTF-8"));
		String encrypted = new String(Base64.encodeBase64(keyBytes));
		return encrypted;
	}
	
	public static String cryptoHexKeyEncrypt(String hexStringKey, String message)
			throws NoSuchAlgorithmException, NoSuchPaddingException
				, InvalidKeyException, InvalidAlgorithmParameterException
				, IllegalStateException, IllegalBlockSizeException
				, BadPaddingException, UnsupportedEncodingException {
		return encodeHexString(cryptoEncrypt(decodeHexString(hexStringKey), message));
	}
	
	public static String cryptoEmbeddedKeyEncrypt(String key, String val)
			throws NoSuchAlgorithmException, InvalidKeyException
				, NoSuchPaddingException, InvalidAlgorithmParameterException
				, IllegalStateException, IllegalBlockSizeException
				, BadPaddingException, UnsupportedEncodingException {
		StringBuffer sbResult = new StringBuffer();
		String hexStringKey = generateHexStringKey();
		String hexStringEncrypted = cryptoHexKeyEncrypt(hexStringKey, val);
		sbResult.append(hexStringKey);
		int offset = 0;
		for(int i = 0; i < hexStringEncrypted.length(); i++) {
			int idx = i % key.length();
			idx = Integer.parseInt(Byte.toString(key.substring(idx, idx + 1).getBytes()[0]));
			offset += idx;
			sbResult.insert(offset % sbResult.length(), hexStringEncrypted.substring(i, i + 1));
		}
		
		return sbResult.toString();
	}
	
	public static String cryptoEmbeddedKeyEncrypt(String strValue)
			throws NoSuchAlgorithmException, InvalidKeyException
				, NoSuchPaddingException, InvalidAlgorithmParameterException
				, IllegalStateException, IllegalBlockSizeException
				, BadPaddingException, UnsupportedEncodingException {
		return cryptoEmbeddedKeyEncrypt(defaultKey, strValue);
	}
	
	public static String cryptoDecrypt(String base64EncodedKey, String encrypted)
			throws NoSuchAlgorithmException, NoSuchPaddingException
				, InvalidKeyException, InvalidAlgorithmParameterException
				, IllegalStateException, IllegalBlockSizeException
				, BadPaddingException, UnsupportedEncodingException {
		javax.crypto.SecretKey key = new SecretKeySpec(Base64.decodeBase64(base64EncodedKey.getBytes()), "AES");
		java.security.spec.AlgorithmParameterSpec iv = new IvParameterSpec(Base64.decodeBase64(base64EncodedKey.getBytes()));
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(2, key, iv);
		String decrypted = new String(cipher.doFinal(Base64.decodeBase64(encrypted.getBytes())), "UTF-8");
		return decrypted;
	}
	
	public static String cryptoHexKeyDecrypt(String hexStringKey, String encryptedHexString)
			throws NoSuchAlgorithmException, NoSuchPaddingException
				, InvalidKeyException, InvalidAlgorithmParameterException
				, IllegalStateException, IllegalBlockSizeException
				, BadPaddingException, UnsupportedEncodingException {
		return cryptoDecrypt(decodeHexString(hexStringKey), decodeHexString(encryptedHexString));
	}
	
	public static String cryptoEmbeddedKeyDecrypt(String key, String embeddedKeyEncrypt)
			throws NoSuchAlgorithmException, InvalidKeyException
				, NoSuchPaddingException, InvalidAlgorithmParameterException
				, IllegalStateException, IllegalBlockSizeException
				, BadPaddingException, UnsupportedEncodingException{
		StringBuffer sbResult = new StringBuffer();
		StringBuffer sbEmbeddedKey = new StringBuffer(embeddedKeyEncrypt);
		int offset = 0;
		for(int i = embeddedKeyEncrypt.length() - 33; i >= 0; i--) {
			int idx = i % key.length();
			idx = Integer.parseInt(Byte.toString(key.substring(idx, idx + 1).getBytes()[0]));
			offset += idx;
		}
		
		for(int i = embeddedKeyEncrypt.length() - 33; i >= 0; i--) {
			sbResult.insert(0, sbEmbeddedKey.substring(offset % (sbEmbeddedKey.length() - 1), offset % (sbEmbeddedKey.length() - 1) + 1));
			sbEmbeddedKey.delete(offset % (sbEmbeddedKey.length() - 1), offset % (sbEmbeddedKey.length() - 1) + 1);
			int idx = i % key.length();
			idx = Integer.parseInt(Byte.toString(key.substring(idx, idx + 1).getBytes()[0]));
			offset -= idx;
		}
		
		return cryptoHexKeyDecrypt(sbEmbeddedKey.toString(), sbResult.toString());
	}
	
	public static String cryptoEmbeddedKeyDecrypt(String embeddedKeyEncrypt)
			throws NoSuchAlgorithmException, InvalidKeyException
				, NoSuchPaddingException, InvalidAlgorithmParameterException
				, IllegalStateException, IllegalBlockSizeException
				, BadPaddingException, UnsupportedEncodingException {
		return cryptoEmbeddedKeyDecrypt(defaultKey, embeddedKeyEncrypt);
	}
}
