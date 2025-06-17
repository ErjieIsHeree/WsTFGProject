package com.erjieisheree.yonuncaconia.service;

public interface DDBBService {
    int addUser(String username, String password);
    boolean updateUser(String id, String username, String password);
    int findUser(String username, String password);
}
