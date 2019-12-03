/**
 * 
 */
package com.tp.security.token.common;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.MalformedJsonException;

/**
 * @author ii00083746
 *
 */
public class CommonUtilities {

	private static CommonUtilities commonUtilities;
	private final Gson gson;
	
	private CommonUtilities() {
		gson= new Gson();
	}
	
	public static CommonUtilities getInstance() {
		if(commonUtilities == null) {
			commonUtilities = new CommonUtilities();
		}
		return commonUtilities;
	}
	
	public String toJson(Object object) {
		return gson.toJson(object);
	}
	
	public <T> T jsontoObject(String json, Class<T> classInstance)
			throws IllegalArgumentException, MalformedJsonException, JsonSyntaxException {
		return this.gson.fromJson(json, classInstance);
	}
}
