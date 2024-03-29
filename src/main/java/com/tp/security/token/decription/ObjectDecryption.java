/**
 * 
 */
package com.tp.security.token.decription;

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

import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.MalformedJsonException;
import com.tp.security.token.common.CommonUtilities;
import com.tp.util.security.token.constant.ApplicationConstant;

/**
 * pojo model decryption class.
 * 
 * @author ii00083746
 *
 */
public class ObjectDecryption {
	
	private final CommonUtilities commonUtilities;
	private final KeyGenerator keyGenerator;
	private final Cipher cipher;
	
	/*
	 * default constructor. 
	 */
	public ObjectDecryption() throws NoSuchAlgorithmException, NoSuchPaddingException {
		commonUtilities = CommonUtilities.getInstance();
		keyGenerator = KeyGenerator.getInstance("DESede");
		cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
	}
	
	/*
	 * decrypt method.
	 * 
	 * @Param encritedValue: encrypted java model.
	 * @Param classType: class type object.
	 * @Param key: decrypt private key
	 */
	public <T> T decrypt(final String encritedValue, Class<T> classType, String key) throws JsonSyntaxException, InvalidKeyException, IllegalArgumentException, MalformedJsonException, IllegalBlockSizeException, BadPaddingException {
		return commonUtilities.jsontoObject(decrypt(encritedValue, key), classType);
	}
	
	/*
	 * decrypt method.
	 * 
	 * @Param encritedValue: encrypted java model.
	 * @Param classType: class type object.
	 */
	public <T> T decrypt(final String encritedValue, Class<T> classType) throws JsonSyntaxException, IllegalArgumentException, MalformedJsonException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		return decrypt(encritedValue,classType, ApplicationConstant.DEFAULT_SECRET_KEY);
	}
	
	/*
	 * decrypt method.
	 * 
	 * @Param encritedValue: encrypted java model.
	 */
	public <T> Object decrypt(final String encritedValue) throws JsonSyntaxException, IllegalArgumentException, MalformedJsonException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		return decrypt(encritedValue, Object.class);
	}
	
	/*
	 * decrypt method.
	 * 
	 * @Param encritedValue: encrypted java model.
	 * @Param key: decrypt private key
	 */
	public String decrypt(final String value, String key) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		SecureRandom secureRandom = new SecureRandom(key.getBytes());
		keyGenerator.init(secureRandom);
		SecretKey secretKey = keyGenerator.generateKey();
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(value));
		return new String(decrypted);
	}
}
