/**
 * 
 */
package com.tp.util.security.token;

/**
 * @author ii00083746
 *
 */
public class MokitoModel {
	
	private String fname;
	private String lname;
	private int age;
	private boolean login;
	
	
	public MokitoModel() {
		super();
	}

	public MokitoModel(String fname, String lname, int age, boolean login) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.age = age;
		this.login = login;
	}
	
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public boolean isLogin() {
		return login;
	}
	public void setLogin(boolean login) {
		this.login = login;
	}

	@Override
	public String toString() {
		return "MokitoModel [fname=" + fname + ", lname=" + lname + ", age=" + age + ", login=" + login + "]";
	}
	
	
	
}
