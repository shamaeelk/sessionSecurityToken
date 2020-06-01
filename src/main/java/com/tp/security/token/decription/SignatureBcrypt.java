/**
 * 
 */
package com.tp.security.token.decription;

import java.util.Base64;

import org.mindrot.jbcrypt.BCrypt;

import com.tp.util.security.token.constant.ApplicationConstant;

/**
 * hashed signation bcrypt class.
 *  
 * @author ii00083746
 *
 */
public class SignatureBcrypt {
	
	/*
	 * default hashed bcrypt signature method.
	 */
	public String getSignatureBcrypt() {
		return getSignatureBcrypt(ApplicationConstant.SIGNATURE);
	}
	
	/*
	 * hashed bcrypt signature method.
	 * 
	 * @Param signature: signature 
	 */
	public String getSignatureBcrypt(final String signature) {
		return Base64.getEncoder().encodeToString(BCrypt.hashpw(signature, BCrypt.gensalt()).getBytes());
	}
	
	/*
	 * default hashed bcrypt signature method with iterative hashed count.
	 * 
	 * @Param hashcount: iterative hashed count 
	 */
	public String getSignatureBcrypt(final int hashcount) {
		return BCrypt.hashpw(ApplicationConstant.SIGNATURE, BCrypt.gensalt(hashcount));
	}
	
	/*
	 * hashed bcrypt signature method with iterative hashed count.
	 * 
	 * @Param signature: signature 
	 * @Param hashcount: iterative hashed count 
	 */
	public String getSignatureBcrypt(final String signature, final int hashcount) {
		return BCrypt.hashpw(signature ,BCrypt.gensalt(hashcount));
	}
	
	/*
	 * match hashed signature.
	 * 
	 * @Param signature: bcrypt signature 
	 * @Param hashcount: iterative hashed count 
	 */
	public boolean matchSignature(final String value, final String hashed) {
		return BCrypt.checkpw(value,  new String(Base64.getDecoder().decode(hashed)));
	}
	
	/*
	 * match hashed signature.
	 * 
	 * @Param hashcount: iterative hashed count 
	 */
	public boolean matchSignature(final String hashed) {
		return matchSignature(ApplicationConstant.SIGNATURE, hashed);
	}
	
}
