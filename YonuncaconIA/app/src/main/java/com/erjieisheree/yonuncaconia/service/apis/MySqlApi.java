package com.erjieisheree.yonuncaconia.service.apis;

import com.erjieisheree.yonuncaconia.service.DDBBService;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

public class MySqlApi implements DDBBService {

    @Override
    public int addUser(String username, String password) {
        String info = "{\"nick\": \"" + username + "\", \"pwd\": \"" + password + "\"}";
        JsonNode root = null;

        try {
            URL url = new URL("http://10.0.2.2:5000/users");
            InputStream dBStream = APIsUtils.getStream(url, info, "POST");
            root = APIsUtils.getResponse(dBStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (root == null) {
            throw new IllegalStateException();
        }

        return root.get("Succeed").intValue();
    }

    @Override
    public boolean updateUser(String id, String newNick, String newPwd) {
        String info = "{\"id\": \"" + id + "\", \"newNick\": \"" + newNick + "\", \"newPwd\": \"" + newPwd + "\"}";
        JsonNode root = null;

        try {
            URL url = new URL("http://10.0.2.2:5000/users");
            InputStream dBStream = APIsUtils.getStream(url, info, "PATCH");
            root = APIsUtils.getResponse(dBStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (root == null) {
            throw new IllegalStateException();
        }

        return root.get("Succeed").asBoolean();
    }

    @Override
    public int findUser(String username, String password) {
        String info = null;
        try {
            info = "?nick=" + URLEncoder.encode(username, "UTF-8") + "&pwd=" + URLEncoder.encode(password, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        JsonNode root = null;

        try {
            URL url = new URL("http://10.0.2.2:5000/users" + info);
            InputStream dBStream = APIsUtils.getStream(url, null, "GET");
            root = APIsUtils.getResponse(dBStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (root == null) {
            throw new IllegalStateException();
        }
        System.out.println("Root: " + root.get("Succeed").intValue());
        return root.get("Succeed").intValue();
    }

}
