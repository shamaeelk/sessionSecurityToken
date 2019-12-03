/**
 * 
 */
package com.tp.util.security.token;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ii00083746
 *
 */
public class ChildAuthenticationCredential {

	
	private Map<String, AuthenticationCredential> authenticationCredential = new HashMap<String, ChildAuthenticationCredential.AuthenticationCredential>();
	
	

	@Override
	public String toString() {
		return "ChildAuthenticationCredential [authenticationCredential=" + authenticationCredential + "]";
	}



	public Map<String, AuthenticationCredential> getAuthenticationCredential() {
		return authenticationCredential;
	}



	public void setAuthenticationCredential(Map<String, AuthenticationCredential> authenticationCredential) {
		this.authenticationCredential = authenticationCredential;
	}



	public static class AuthenticationCredential {
		
		private String jwtToken;
		private String airlineSession;
		
		public String getJwtToken() {
			return jwtToken;
		}
		public void setJwtToken(String jwtToken) {
			this.jwtToken = jwtToken;
		}
		public String getAirlineSession() {
			return airlineSession;
		}
		public void setAirlineSession(String airlineSession) {
			this.airlineSession = airlineSession;
		}
		@Override
		public String toString() {
			return "AuthenticationCredential [jwtToken=" + jwtToken + ", airlineSession=" + airlineSession + "]";
		}
		
		
		
	}
	
}
