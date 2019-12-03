/**
 * 
 */
package com.tp.util.security.token;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;

import com.tp.security.token.exception.InvalidSignatureException;
import com.tp.security.token.exception.InvalidTokenException;

/**
 * @author ii00083746
 *
 */
public class SecurityTokenTest {
	/*
	public static void main(String... strings ) {
		
		String sa =  "1Hy5Wf/0WHEYOtWdn04s6yeHNnIOO/oLzbKEgQBgGs1lyqbKvP0FAWHtfpPs4NGuWAszYlwEJwVCE/2Ev+L79g==.JDJhJDEwJEFJckVheHU3b1lTUUx2VklsWFJhL3VQTkkyN1VmWE51ZmtHN1JVZnR5QzRaM2kycHpRclB5";
				//SecurityToken.encodeToken(getModel());
		System.out.println(sa);
		try {
			MokitoModel m = SecurityToken.decodeToken(sa, MokitoModel.class);
			System.out.println(m.toString());
		} catch (InvalidTokenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidSignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	*/
	@Test
	public void decodeTokenTest() throws InvalidTokenException, InvalidSignatureException {
		String token = SecurityToken.encodeToken(getModel());
		MokitoModel mokitoModel = SecurityToken.decodeToken(token, MokitoModel.class);
		assertEquals("shamaeel", mokitoModel.getFname());
		assertEquals("khan", mokitoModel.getLname());
		assertEquals(27, mokitoModel.getAge());
		assertEquals(true, mokitoModel.isLogin());
	}
	
	@Test(expected = InvalidTokenException.class)
	public void invalidTokenTest() throws InvalidTokenException, InvalidSignatureException {
		String token = "1Hy5Wf/0WHEYOtWdn04s6yeHNnIOO/oLzbKEgQBgGs1lyqbKvP0FAWHtfpPS4NGuWAszYlwEJwVCE/2Ev+L79g==.JDJhJDEwJEFJckVheHU3b1lTUUx2VklsWFJhL3VQTkkyN1VmWE51ZmtHN1JVZnR5QzRaM2kycHpRclB5";
		MokitoModel mokitoModel = SecurityToken.decodeToken(token, MokitoModel.class);
	}
	
	@Test(expected = InvalidSignatureException.class)
	public void invalidSignatureTest() throws InvalidTokenException, InvalidSignatureException {
		String token="1Hy5Wf/0WHEYOtWdn04s6yeHNnIOO/oLzbKEgQBgGs1lyqbKvP0FAWHtfpPs4NGuWAszYlwEJwVCE/2Ev+L79g==.JDJhJDEwJEFJckVheHU3b1lTUUx2VklsWFJhL3VQTkKyN1VmWE51ZmtHN1JVZnR5QzRaM2kycHpRclB5";
		MokitoModel mokitoModel = SecurityToken.decodeToken(token, MokitoModel.class);
	}
	
	public static MokitoModel getModel() {
		return new MokitoModel("shamaeel", "khan", 27, true);
	}
	
	
}
