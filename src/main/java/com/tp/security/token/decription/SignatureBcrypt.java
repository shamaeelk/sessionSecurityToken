/**
 * 
 */
package com.tp.security.token.decription;

import java.util.Base64;

import org.mindrot.jbcrypt.BCrypt;

import com.tp.util.security.token.constant.ApplicationConstant;

/**
 * @author ii00083746
 *
 */
public class SignatureBcrypt {
	
	public String getSignatureBcrypt() {
		return getSignatureBcrypt(ApplicationConstant.SIGNATURE);
	}
	
	public String getSignatureBcrypt(final String signature) {
		return Base64.getEncoder().encodeToString(BCrypt.hashpw(signature, BCrypt.gensalt()).getBytes());
	}
	
	public String getSignatureBcrypt(final int hashcount) {
		return BCrypt.hashpw(ApplicationConstant.SIGNATURE, BCrypt.gensalt(hashcount));
	}
	
	
	public String getSignatureBcrypt(final String signature, final int hashcount) {
		return BCrypt.hashpw(signature ,BCrypt.gensalt(hashcount));
	}
	
	public boolean matchSignature(final String value, final String hashed) {
		return BCrypt.checkpw(value,  new String(Base64.getDecoder().decode(hashed)));
	}
	
	public boolean matchSignature(final String hashed) {
		return matchSignature(ApplicationConstant.SIGNATURE, hashed);
	}
	
	/**
	 * @param strings
	 */
	
	public static void main(String...strings ) {
		String hashed = "cGFzc3dvcmQ=";
		System.out.println(new String(Base64.getDecoder().decode(hashed)));
	}
	
}
