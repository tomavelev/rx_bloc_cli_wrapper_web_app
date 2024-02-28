package com.programtom.rx_bloc_cli_wrapper_web_app.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service
public class HashingService {

    public HashingService() {
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Missing Algorithm in Environment", e);
        }
    }

    BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder(16);

    MessageDigest md;

    public String bencrypt(String content) {
        return (bcrypt.encode(content));
    }

    public boolean bencryptVerify(String password, String storedPass) {
        return bcrypt.matches(password, storedPass);
    }

    public String base64(String content) {
        try {
            return (Base64.getEncoder().encodeToString(content.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException e) {
            return content;
        }
    }

    public String sha512(String content) {
        try {
            return (Base64.getEncoder().encodeToString(md.digest(content.getBytes("UTF-8"))));
        } catch (UnsupportedEncodingException e) {
            return content;
        }
    }
}
