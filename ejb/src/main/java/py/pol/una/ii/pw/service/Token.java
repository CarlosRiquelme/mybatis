package py.pol.una.ii.pw.service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

import com.auth0.jwt.internal.org.apache.commons.codec.binary.Base64;

public class Token {
	
    public static String generar(String username, String password) {
    	
    	Base64 decoder = new Base64(true);
        String CLIENT_SECRET=username+password;
        Random random = new SecureRandom();
        String token2 = new BigInteger(130, random).toString(32);
        System.out.println("ramdon  "+random);
        System.out.println("token prueba  "+token2);
        
        byte[] secret = decoder.decodeBase64(CLIENT_SECRET);
        String token = secret.toString();
        return token;
	       
    }
	

}
