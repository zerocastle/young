package medios.cmmn.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@Slf4j
public class CryptoUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(CryptoUtil.class);
	
	public static String encodeHexString(String base64EncodedKey) {
		return encodeHexString(Base64.decodeBase64(base64EncodedKey.getBytes()));
	}
	public static String encodeHexString(byte[] keyBytes) {
		return new String( Hex.encodeHex(keyBytes) );
	}
	
	public static String decodeHexString(String hexStringKey) {
		byte [] hexBytes = new byte [hexStringKey.length() / 2];
		int j = 0;
		
		for (int i = 0; i < hexStringKey.length(); i += 2) {
			hexBytes[j++] = (byte) (Integer.parseInt(hexStringKey.substring(i, i + 2), 16) & 0xFF);
		}
		
		return decodeHexString(hexBytes);
	}
	
	public static String convertReturnCode(String str) {
		return str.replaceAll("\r", "\\\\r").replaceAll("\n", "\\\\n");
	}
	public static String decodeHexString(byte[] hexBytes) {
		//return Base64.encodeBase64String(hexBytes);
		return new String( Base64.encodeBase64(hexBytes) );
	}
	
	public static String generateHexStringKey() throws NoSuchAlgorithmException {
		return encodeHexString(generateKey());
	}
	
	public static String generateKey() throws NoSuchAlgorithmException {
		KeyGenerator generator = KeyGenerator.getInstance("AES");
		generator.init(128);
		Key createkey = generator.generateKey();
		byte[] keyBytes = createkey.getEncoded();
//		String base64EncodedKey = Base64.encodeBase64URLSafeString(keyBytes);
//		String base64EncodedKey = Base64.encodeBase64String(keyBytes);
		String base64EncodedKey = new String( Base64.encodeBase64(keyBytes) );

//		System.out.println("base64EncodedKey : " + base64EncodedKey.trim());
//		System.out.println("================> hexString1   :" + encodeHexString(keyBytes));
//		System.out.println("================> hexString2   :" + encodeHexString(base64EncodedKey));
//		System.out.println("================> base64EncodedKey   :" + decodeHexString(encodeHexString(base64EncodedKey)));

		return base64EncodedKey;
	}
	public static String cryptoHexKeyEncrypt(String hexStringKey, String message) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalStateException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		String base64EncodedKey = decodeHexString(hexStringKey);
		//System.out.println("base64EncodedKey : " + base64EncodedKey);
		return encodeHexString(cryptoEncrypt(base64EncodedKey, message));
	}
	
	public static String cryptoEncrypt(String base64EncodedKey, String message) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalStateException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		SecretKey				key	= new SecretKeySpec		(Base64.decodeBase64(base64EncodedKey.getBytes()), "AES");
		AlgorithmParameterSpec	iv	= new IvParameterSpec	(Base64.decodeBase64(base64EncodedKey.getBytes()));
		
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key, iv);
		byte[] keyBytes = cipher.doFinal(message.getBytes("UTF-8"));
		String encrypted = new String( Base64.encodeBase64(keyBytes) );
		//System.out.println("encrypted : " + encrypted);
		return encrypted;
	}
	
	public static String cryptoHexKeyDecrypt(String hexStringKey, String encryptedHexString) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalStateException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		String base64EncodedKey = decodeHexString(hexStringKey);
		return cryptoDecrypt(base64EncodedKey, decodeHexString(encryptedHexString));
	}
	
	public static String cryptoDecrypt(String base64EncodedKey, String encrypted) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalStateException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		SecretKey				key	= new SecretKeySpec		(Base64.decodeBase64(base64EncodedKey.getBytes()), "AES");
		AlgorithmParameterSpec	iv	= new IvParameterSpec	(Base64.decodeBase64(base64EncodedKey.getBytes()));
		
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, key, iv);
		
		String decrypted = new String(cipher.doFinal(Base64.decodeBase64(encrypted.getBytes())), "UTF-8");
		//System.out.println("decrypted : " + decrypted);
		return decrypted;
	}
	
	public static String cryptoEmbeddedKeyEncrypt(String key, String val) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalStateException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		StringBuffer sbResult = new StringBuffer();
		
		String hexStringKey			= generateHexStringKey();
		String hexStringEncrypted	= cryptoHexKeyEncrypt(hexStringKey, val);
		
		sbResult.append(hexStringKey);
		
		int offset = 0;
		for(int i=0; i<hexStringEncrypted.length(); i++) {
			int idx = (i % key.length());
			idx = Integer.parseInt(Byte.toString(key.substring(idx, idx+1).getBytes()[0]));
			offset += idx;
			
			sbResult.insert((offset % sbResult.length()), hexStringEncrypted.substring(i, i+1));
		}
		
		return sbResult.toString();
	}
	public static String cryptoEmbeddedKeyDecrypt(String key, String embeddedKeyEncrypt) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalStateException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		StringBuffer sbResult = new StringBuffer();
		StringBuffer sbEmbeddedKey = new StringBuffer(embeddedKeyEncrypt);
		
		int offset = 0;
		for(int i=embeddedKeyEncrypt.length() - 33; i>=0; i--) {
			int idx = (i % key.length());
			idx = Integer.parseInt(Byte.toString(key.substring(idx, idx+1).getBytes()[0]));
			offset += idx;
		}
		
		for(int i=embeddedKeyEncrypt.length() - 33; i>=0; i--) {
			sbResult.insert(0, sbEmbeddedKey.substring(offset % (sbEmbeddedKey.length() - 1), offset % (sbEmbeddedKey.length() - 1) + 1));
			sbEmbeddedKey.delete(offset % (sbEmbeddedKey.length() - 1), offset % (sbEmbeddedKey.length() - 1) + 1);
			
			int idx = (i % key.length());
			idx = Integer.parseInt(Byte.toString(key.substring(idx, idx+1).getBytes()[0]));
			offset -= idx;
		}
		
		return cryptoHexKeyDecrypt(sbEmbeddedKey.toString(), sbResult.toString());
	}
	
	public static String[] expressions = {
			// 특수문자 포함 필수
			   "([a-zA-Z0-9ㄱ-ㅎㅏ-ㅣ가-힣].*[`,~,!,@,#,$,%,^,&,*,(,),-,=,_,+,\\[,\\],{,},;,:,,,.,/,<,>,?])"
			+ "|([`,~,!,@,#,$,%,^,&,*,(,),-,=,_,+,\\[,\\],{,},;,:,,,.,/,<,>,?].*[a-zA-Z0-9ㄱ-ㅎㅏ-ㅣ가-힣])"
			// 특수문자, 숫자 포함 필수
			,  "([a-zA-Zㄱ-ㅎㅏ-ㅣ가-힣].*[0-9].*[`,~,!,@,#,$,%,^,&,*,(,),-,=,_,+,\\[,\\],{,},;,:,,,.,/,<,>,?])"
			+ "|([a-zA-Zㄱ-ㅎㅏ-ㅣ가-힣].*[`,~,!,@,#,$,%,^,&,*,(,),-,=,_,+,\\[,\\],{,},;,:,,,.,/,<,>,?].*[0-9])"
			+ "|([0-9].*[`,~,!,@,#,$,%,^,&,*,(,),-,=,_,+,\\[,\\],{,},;,:,,,.,/,<,>,?].*[a-zA-Zㄱ-ㅎㅏ-ㅣ가-힣])"
			+ "|([0-9].*[a-zA-Zㄱ-ㅎㅏ-ㅣ가-힣].*[`,~,!,@,#,$,%,^,&,*,(,),-,=,_,+,\\[,\\],{,},;,:,,,.,/,<,>,?])"
			+ "|([`,~,!,@,#,$,%,^,&,*,(,),-,=,_,+,\\[,\\],{,},;,:,,,.,/,<,>,?].*[0-9].*[a-zA-Zㄱ-ㅎㅏ-ㅣ가-힣])"
			+ "|([`,~,!,@,#,$,%,^,&,*,(,),-,=,_,+,\\[,\\],{,},;,:,,,.,/,<,>,?].*[a-zA-Zㄱ-ㅎㅏ-ㅣ가-힣].*[0-9])"	
			// 특수문자, 숫자 포함 필수 대/소문자 구분 필수
			,  "([a-zㄱ-ㅎㅏ-ㅣ가-힣].*[A-Z].*[0-9].*[`,~,!,@,#,$,%,^,&,*,(,),-,=,_,+,\\[,\\],{,},;,:,,,.,/,<,>,?])"
			+ "|([a-zㄱ-ㅎㅏ-ㅣ가-힣].*[A-Z].*[`,~,!,@,#,$,%,^,&,*,(,),-,=,_,+,\\[,\\],{,},;,:,,,.,/,<,>,?].*[0-9])"
			+ "|([a-zㄱ-ㅎㅏ-ㅣ가-힣].*[0-9].*[A-Z].*[`,~,!,@,#,$,%,^,&,*,(,),-,=,_,+,\\[,\\],{,},;,:,,,.,/,<,>,?])"
			+ "|([a-zㄱ-ㅎㅏ-ㅣ가-힣].*[0-9].*[`,~,!,@,#,$,%,^,&,*,(,),-,=,_,+,\\[,\\],{,},;,:,,,.,/,<,>,?].*[A-Z])"
			+ "|([a-zㄱ-ㅎㅏ-ㅣ가-힣].*[`,~,!,@,#,$,%,^,&,*,(,),-,=,_,+,\\[,\\],{,},;,:,,,.,/,<,>,?].*[A-Z].*[0-9])"
			+ "|([a-zㄱ-ㅎㅏ-ㅣ가-힣].*[`,~,!,@,#,$,%,^,&,*,(,),-,=,_,+,\\[,\\],{,},;,:,,,.,/,<,>,?].*[0-9].*[A-Z])"
			
			+ "|([A-Z].*[a-zㄱ-ㅎㅏ-ㅣ가-힣].*[0-9].*[`,~,!,@,#,$,%,^,&,*,(,),-,=,_,+,\\[,\\],{,},;,:,,,.,/,<,>,?])"
			+ "|([A-Z].*[a-zㄱ-ㅎㅏ-ㅣ가-힣].*[`,~,!,@,#,$,%,^,&,*,(,),-,=,_,+,\\[,\\],{,},;,:,,,.,/,<,>,?].*[0-9])"
			+ "|([A-Z].*[0-9].*[a-zㄱ-ㅎㅏ-ㅣ가-힣].*[`,~,!,@,#,$,%,^,&,*,(,),-,=,_,+,\\[,\\],{,},;,:,,,.,/,<,>,?])"
			+ "|([A-Z].*[0-9].*[`,~,!,@,#,$,%,^,&,*,(,),-,=,_,+,\\[,\\],{,},;,:,,,.,/,<,>,?].*[a-zㄱ-ㅎㅏ-ㅣ가-힣])"
			+ "|([A-Z].*[`,~,!,@,#,$,%,^,&,*,(,),-,=,_,+,\\[,\\],{,},;,:,,,.,/,<,>,?].*[a-zㄱ-ㅎㅏ-ㅣ가-힣].*[0-9])"
			+ "|([A-Z].*[`,~,!,@,#,$,%,^,&,*,(,),-,=,_,+,\\[,\\],{,},;,:,,,.,/,<,>,?].*[0-9].*[a-zㄱ-ㅎㅏ-ㅣ가-힣])"
			
			+ "|([0-9].*[a-zㄱ-ㅎㅏ-ㅣ가-힣].*[A-Z].*[`,~,!,@,#,$,%,^,&,*,(,),-,=,_,+,\\[,\\],{,},;,:,,,.,/,<,>,?])"
			+ "|([0-9].*[a-zㄱ-ㅎㅏ-ㅣ가-힣].*[`,~,!,@,#,$,%,^,&,*,(,),-,=,_,+,\\[,\\],{,},;,:,,,.,/,<,>,?].*[A-Z])"
			+ "|([0-9].*[A-Z].*[a-zㄱ-ㅎㅏ-ㅣ가-힣].*[`,~,!,@,#,$,%,^,&,*,(,),-,=,_,+,\\[,\\],{,},;,:,,,.,/,<,>,?])"
			+ "|([0-9].*[A-Z].*[`,~,!,@,#,$,%,^,&,*,(,),-,=,_,+,\\[,\\],{,},;,:,,,.,/,<,>,?].*[a-zㄱ-ㅎㅏ-ㅣ가-힣])"
			+ "|([0-9].*[`,~,!,@,#,$,%,^,&,*,(,),-,=,_,+,\\[,\\],{,},;,:,,,.,/,<,>,?].*[a-zㄱ-ㅎㅏ-ㅣ가-힣].*[A-Z])"
			+ "|([0-9].*[`,~,!,@,#,$,%,^,&,*,(,),-,=,_,+,\\[,\\],{,},;,:,,,.,/,<,>,?].*[A-Z].*[a-zㄱ-ㅎㅏ-ㅣ가-힣])"
			
			+ "|([`,~,!,@,#,$,%,^,&,*,(,),-,=,_,+,\\[,\\],{,},;,:,,,.,/,<,>,?].*[a-zㄱ-ㅎㅏ-ㅣ가-힣].*[A-Z].*[0-9])"
			+ "|([`,~,!,@,#,$,%,^,&,*,(,),-,=,_,+,\\[,\\],{,},;,:,,,.,/,<,>,?].*[a-zㄱ-ㅎㅏ-ㅣ가-힣].*[0-9].*[A-Z])"
			+ "|([`,~,!,@,#,$,%,^,&,*,(,),-,=,_,+,\\[,\\],{,},;,:,,,.,/,<,>,?].*[A-Z].*[0-9].*[a-zㄱ-ㅎㅏ-ㅣ가-힣])"
			+ "|([`,~,!,@,#,$,%,^,&,*,(,),-,=,_,+,\\[,\\],{,},;,:,,,.,/,<,>,?].*[A-Z].*[a-zㄱ-ㅎㅏ-ㅣ가-힣].*[0-9])"
			+ "|([`,~,!,@,#,$,%,^,&,*,(,),-,=,_,+,\\[,\\],{,},;,:,,,.,/,<,>,?].*[a-zㄱ-ㅎㅏ-ㅣ가-힣].*[0-9].*[A-Z])"
			+ "|([`,~,!,@,#,$,%,^,&,*,(,),-,=,_,+,\\[,\\],{,},;,:,,,.,/,<,>,?].*[a-zㄱ-ㅎㅏ-ㅣ가-힣].*[A-Z].*[0-9])"
			// 공백검사
			, "^[^\\s]*$"
			// 동일문자 반복, 연속문자 검사
			, "[a-zA-Z0-9`~!@#$%^&*()-=_+\\[\\]{};:,./<>?\\s]"
	};

	public static Pattern[] patterns = {
			  Pattern.compile(expressions[0])
			, Pattern.compile(expressions[1])
			, Pattern.compile(expressions[2])
			, Pattern.compile(expressions[3])
			, Pattern.compile(expressions[4])
	};
	
	public static String passwdValidator(String passwd, String limitWord, String limitWords, boolean limitBlank, int lengthLimit, int validateType, int repeatLimit, int sequenceLimit) {
		String msg = null;
		
		if(passwd!=null && passwd.length() > 0) {
			if(lengthLimit == 0 || passwd.length() >= lengthLimit) {				// 길이제한 검사
				if(validateType == 0 || passwdValidator(validateType-1, passwd)) {	// 특수문자 포함 제한 or 특수문자,숫자 포함 제한 or 특수문자,숫자,대/소문자 포함 검사
					if(!limitBlank || passwdValidator(3, passwd)) {								// 공백검사
						if(passwdValidator(passwd, repeatLimit, sequenceLimit)) {	// 동일문자 반복 또는 연속문자 제한 검사
							try {
								if(limitWord != null && passwd.matches(limitWord.replaceAll("\r\n", " ").replaceAll("\n", " ").trim())) {
									msg = "금지단어 제한 오류(전화번호, 생년월일 등)";
								} else {
									msg = "";	// 통과
								}
							} catch(Exception e) {
								msg = e.getMessage();
								LOGGER.error(e.getMessage() + " : {}", e.getCause(), e);
							}

						} else {
							msg = "동일문자 반복 또는 연속문자 제한 오류";
						}
						
					} else {
						msg = "공백 제한 오류";
					}
					
				} else {
					switch(validateType) {
					case 1:
						msg = "특수문자 포함 제한 오류\n허용특수문자 : [`,~,!,@,#,$,%,^,&,*,(,),-,=,_,+,\\[,\\],{,},;,:,,,.,/,<,>,?]";
						break;
					case 2:
						msg = "특수문자,숫자 포함 제한 오류\n허용특수문자 : [`,~,!,@,#,$,%,^,&,*,(,),-,=,_,+,\\[,\\],{,},;,:,,,.,/,<,>,?]";
						break;
					case 3:
						msg = "특수문자,숫자,대/소문자 포함  제한 오류\n허용특수문자 : [`,~,!,@,#,$,%,^,&,*,(,),-,=,_,+,\\[,\\],{,},;,:,,,.,/,<,>,?]";
						break;
					}
				}
				
			} else {
				msg = "비밀번호 길이 제한 오류";
			}
			
		} else {
			msg = "비밀번호 오류";
		}
		
		return msg;
	}
	public static boolean passwdValidator(int type, String passwd) {
		Matcher m = patterns[type].matcher(passwd);
		return m.find();
	}
	public static boolean passwdValidator(String passwd, int repeatLimit, int sequenceLimit) {
		Matcher m = patterns[4].matcher(passwd);

		String oleChar = "";
		int repeat		= 0;
		int sequence	= 0;

		while(m.find()) {
			String newChar = m.group();
			if(oleChar.equals(newChar)) {
				repeat++;
			} else {
				repeat = 1;
			}
			
			if(oleChar != null && oleChar.length() > 0 && newChar != null && newChar.length() > 0) {
				byte oldByte = oleChar.getBytes()[0];
				byte newByte = newChar.getBytes()[0];

				if(oldByte + 1 == newByte || oldByte == newByte + 1) {
					sequence++;
				} else {
					sequence = 1;
				}
			}
			
			oleChar = newChar;
			if(repeat   >= repeatLimit   && repeatLimit   != 0) return false;
			if(sequence >= sequenceLimit && sequenceLimit != 0) return false;
		}
		
		return true;
	}
	
	public static void mergeWord(List<String> main, List<String> sub) {
		for (int i = 0; i < sub.size(); i++) {
			if (!main.contains(sub.get(i))) {
				main.add(sub.get(i));
			}
		}
	}
	
	public static List<String> genWord(String strData) {
		List<String> lstWord = new ArrayList<String>();
		
		if(strData != null && strData.trim().length() > 0) {
			String seperators = new String("");
			String tmpSeperator = strData.replaceAll("[가-힣ㄱ-ㅎㅏ-ㅣa-zA-Z0-9 ]", "");
			for (int i = 0; i < tmpSeperator.length(); i++) {
				if (!seperators.startsWith("" + tmpSeperator.charAt(i))) {
					seperators += "" + tmpSeperator.charAt(i);
				}
			}

			if(seperators.length() == 0) {
				lstWord.add(strData);
			} else {
				for(int i=0; i<seperators.length(); i++) {
					tmpSeperator = seperators.replaceAll("\\" + seperators.substring(i, i+1), "");
					String tmpData = strData;
					for (int j = 0; j < tmpSeperator.length(); j++) {
						tmpData = tmpData.replaceAll("\\" + tmpSeperator.substring(j, j+1), "\\\\" + tmpSeperator.substring(j, j+1));
					}
					mergeWord(lstWord, genWord(tmpData.split("\\" + seperators.substring(i, i+1))));
					mergeWord(lstWord, genWord(tmpData, "\\" + seperators.substring(i, i+1)));
				}
				mergeWord(lstWord, genWord(strData.replaceAll("[^가-힣ㄱ-ㅎㅏ-ㅣa-zA-Z0-9]", " ").split(" ")));
			}
		}
		
		return lstWord;
	}
	
	public static List<String> genWord(String strData, String seperator) {
		String[] lstData = strData.split(seperator);
		String[] lstWord = new String[lstData.length];
   	
		return genWord(lstData.length, 0, getRemainChar(lstData, -1), lstData.length, lstWord, seperator);
    }
	
	public static List<String> genWord(String[] lstData) {
		String[] lstWord = new String[lstData.length];
   	
		return genWord(lstData.length, 0, getRemainChar(lstData, -1), lstData.length, lstWord, null);
    }
	
	private static List<String> genWord(int n, int depth, String[] remainWords, int length, String[] lstWord, String seperator) {
		List<String> lst = new ArrayList<String>();
		for(int i=0; i<n; i++) {
			StringBuffer sbWord			= new StringBuffer();
			lstWord[depth] = remainWords[i];
			for(int j=0; j<length; j++) {
				if(depth >= j) {
					if(sbWord.length() > 0 && seperator != null && seperator.length() > 0) sbWord.append(seperator);
					sbWord.append(lstWord[j].trim());
				}
			}
			if(sbWord.length() > 0 && !lst.contains(sbWord.toString().trim())) {
				lst.add(sbWord.toString().trim());
			}
			
			lst.addAll(genWord(n - 1, depth + 1, getRemainChar(remainWords, i), length, lstWord, seperator));
		}
		return lst;
	}
	
	private static String[] getRemainChar(String[] source, int removeIdx) {
		if(removeIdx == -1){
			return source;
		}

		String[] result = new String[source.length - 1];
		int index = 0;
		for(int i = 0; i < source.length; i++){
			if(i != removeIdx) {
				result[index] = source[i];
				index++;
			}
		}
		
		return result;
	}
}
