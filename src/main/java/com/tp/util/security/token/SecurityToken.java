/**
 * 
 */
package com.tp.util.security.token;

import java.lang.reflect.Field;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.MalformedJsonException;
import com.tp.security.token.decription.ObjectDecryption;
import com.tp.security.token.decription.SignatureBcrypt;
import com.tp.security.token.ecription.ObjectEncryption;
import com.tp.security.token.exception.InvalidSignatureException;
import com.tp.security.token.exception.InvalidTokenException;
import com.tp.util.security.token.constant.ApplicationConstant;

/**
 * @author ii00083746
 *
 */
public class SecurityToken {
	
	private static ObjectEncryption encryption;
	private static ObjectDecryption decryption;
	private static SignatureBcrypt signatureBcrypt;
	private final static Logger LOGGER = Logger.getLogger(SecurityToken.class.getName());
	
	static {
		try {
			encryption = new ObjectEncryption();
			decryption=new ObjectDecryption();
			signatureBcrypt = new SignatureBcrypt();
		} catch (NoSuchAlgorithmException e) {
			LOGGER.log(Level.ALL, e.toString());
		} catch (NoSuchPaddingException e) {
			LOGGER.log(Level.ALL, e.toString());
		}
		
	}

	public static String encodeToken(Object token, String key) {
		try {
			return encryption.encrypt(token, key)+"."+signatureBcrypt.getSignatureBcrypt();
		} catch (InvalidKeyException e) {
			LOGGER.log(Level.ALL, e.toString());
		} catch (IllegalBlockSizeException e) {
			LOGGER.log(Level.ALL, e.toString());
		} catch (BadPaddingException e) {
			LOGGER.log(Level.ALL, e.toString());
		}
		return null;
	}
	
	public static String encodeToken(Object token) {
		try {
			return encryption.encrypt(token)+"."+signatureBcrypt.getSignatureBcrypt();
		} catch (InvalidKeyException e) {
			LOGGER.log(Level.ALL, e.toString());
		} catch (IllegalBlockSizeException e) {
			LOGGER.log(Level.ALL, e.toString());
		} catch (BadPaddingException e) {
			LOGGER.log(Level.ALL, e.toString());
		}
		return null;
	}
	
	
	public static <T> T decodeToken(String token, Class<T> classType, String key) throws InvalidTokenException, InvalidSignatureException{
		
		String[] values = token.split("[.]");
		if(values.length != 2) {
			throw new InvalidTokenException("Invalid token: "+token);
		}
		try {
			if(signatureBcrypt.matchSignature(values[1])) {
				T t = decryption.decrypt(values[0], classType, key);
				try {
					Field field = t.getClass().getDeclaredField("expiredTime");
					
				} catch (NoSuchFieldException | SecurityException e) {
					LOGGER.log(Level.ALL, "token do not have expired Time");
				}
				return decryption.decrypt(values[0], classType, key);
			} else {
				throw new InvalidSignatureException("invalid signature");
			}
		} catch (JsonSyntaxException e) {
			throw new InvalidTokenException("invalid token syntex");
		} catch (IllegalArgumentException e) {
			throw new InvalidTokenException("illegal argument token");
		} catch (MalformedJsonException e) {
			throw new InvalidTokenException("malformed token");
		} catch (InvalidKeyException e) {
			throw new InvalidTokenException("invalid key");
		} catch (IllegalBlockSizeException e) {
			throw new InvalidSignatureException("malformed signature");
		} catch (BadPaddingException e) {
			throw new InvalidSignatureException("malformed signature");
		}
	}
	
	public static <T> T decodeToken(String token, Class<T> classType) throws InvalidTokenException, InvalidSignatureException {
		return decodeToken(token, classType, ApplicationConstant.DEFAULT_SECRET_KEY);
	}

}
