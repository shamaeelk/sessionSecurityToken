/**
 * 
 */
package com.tp.security.token.decription;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.tp.util.security.token.constant.ApplicationConstant;

/**
 * Asymmetric SHA256 and RSA encode and decode class.
 * 
 * @author ii00083746
 *
 */
public class AsymmetricSHA256RSA {

	private static AsymmetricSHA256RSA instance;
	
	/*
	 * private constructor
	 */
	private AsymmetricSHA256RSA() {
		
	}
	
	/*
	 * static factory method
	 */
	public static AsymmetricSHA256RSA getInstance() {
		if( instance == null )
			instance = new AsymmetricSHA256RSA();
		
		return instance;
	}
	
	private PublicKey getPublicKey(String base64PublicKey){
        PublicKey publicKey = null;
        try{
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(base64PublicKey.getBytes()));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(keySpec);
            return publicKey;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return publicKey;
    }

	
    private PrivateKey getPrivateKey(String base64PrivateKey){
        PrivateKey privateKey = null;
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(base64PrivateKey.getBytes()));
        KeyFactory keyFactory = null;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            privateKey = keyFactory.generatePrivate(keySpec);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return privateKey;
    }
    
    /*
     * encode method.
     * 
     * @Param data: data for encode
     */
    public String encrypt(String data) throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
        return encrypt(data, ApplicationConstant.ASYMMETRIC_SHA256_RSA_PUBLIC_KEY);
    }
	
    /*
     * encode method.
     * 
     * @Param data: data for encode
     * @Param publicKey: key for encode.
     */
	public String encrypt(String data, String publicKey) throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(publicKey));
        byte doFinal[] = cipher.doFinal(data.getBytes());
        return  Base64.getEncoder().encodeToString(doFinal);
    }
	
	/*
     * decryption method.
     * 
     * @Param data: encrypted data for decrypt.
     */
	public String decrypt(String data) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        return decrypt(data, ApplicationConstant.ASYMMETRIC_SHA256_RSA_PRIVATE_KEY);
    }
	
	/*
     * decryption method.
     * 
     * @Param data: base 64 encrypted data for decrypt.
     * @Param base64PrivateKey: base 64 private key for decrypt.
     */
	public String decrypt(String data, String base64PrivateKey) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        return decrypt(Base64.getDecoder().decode(data.getBytes()), getPrivateKey(base64PrivateKey));
    }
	
	/*
     * decryption method.
     * 
     * @Param data: byte array encrypted data for decrypt.
     * @Param privateKey: private key object.
     */
    private String decrypt(byte[] data, PrivateKey privateKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(cipher.doFinal(data));
    }
    
}