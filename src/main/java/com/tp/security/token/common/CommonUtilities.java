/**
 * 
 */
package com.tp.security.token.common;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.MalformedJsonException;

/**
 * common Utilities class.
 * 
 * @author ii00083746: Shamaeel khan
 *
 */
public class CommonUtilities {

	private static CommonUtilities commonUtilities;
	private final Gson gson;
	
	/*
	 * private default constructor
	 */
	private CommonUtilities() {
		gson= new Gson();
	}
	
	/*
	 * static factory method
	 */
	public static CommonUtilities getInstance() {
		if(commonUtilities == null) {
			commonUtilities = new CommonUtilities();
		}
		return commonUtilities;
	}
	
	/*
	 * convert model into json 
	 */
	public String toJson(Object object) {
		String json = gson.toJson(object);
		System.out.println("json object: "+json);
		return gson.toJson(object);
	}
	
	/*
	 * convert json to model.
	 */
	public <T> T jsontoObject(String json, Class<T> classInstance)
			throws IllegalArgumentException, MalformedJsonException, JsonSyntaxException {
		return this.gson.fromJson(json, classInstance);
	}
}
