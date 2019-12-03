/**
 * 
 */
package com.tp.security.token.ecription;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import com.tp.security.token.common.CommonUtilities;
import com.tp.util.security.token.constant.ApplicationConstant;

/**
 * @author ii00083746
 *
 */
public class ObjectEncryption {

	private CommonUtilities commonUtilities;
	private final KeyGenerator keyGenerator;
	private final Cipher cipher;
	
	public ObjectEncryption() throws NoSuchAlgorithmException, NoSuchPaddingException {
		commonUtilities = CommonUtilities.getInstance();
		keyGenerator = KeyGenerator.getInstance("DESede");
		cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
	}
	

	public String encrypt(String value) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		return encrypt(value, ApplicationConstant.DEFAULT_SECRET_KEY);
	}

	public String encrypt(Object object) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		return encrypt(object, ApplicationConstant.DEFAULT_SECRET_KEY);
	}

	public String encrypt(Object object, String key) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		return encrypt(commonUtilities.toJson(object), key);
	}

	public String encrypt(final String value, String key) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException  {
		SecureRandom secureRandom = new SecureRandom(key.getBytes());
		keyGenerator.init(secureRandom);
		SecretKey secretKey = keyGenerator.generateKey();
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		
		final byte[] encrypted = cipher.doFinal(value.getBytes());
		return Base64.getEncoder().encodeToString(encrypted);
	}
	
	
	
}
