package com.io.salt.sha;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class GenerateSalt {
	
	private static byte[] salt;
	
	public static void main(String [] args) {
		generate_salt();
		try {
			byte[] newPassword = messageDigest("PleaseHashThisPassword");
			 StringBuilder sb = new StringBuilder();
	            for(int i=0; i< newPassword.length ;i++)
	            {
	                sb.append(Integer.toString((newPassword[i] & 0xff) + 0x100, 16).substring(1));
	            }
	           String generatedPassword = sb.toString();
	           System.out.println(generatedPassword);
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void generate_salt() {
	SecureRandom random =  new SecureRandom();
	 salt = new byte[16];
	random.nextBytes(salt);
	}
	public static byte[] messageDigest(String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(salt);
		byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
		return hashedPassword;
	}
	
	
}
