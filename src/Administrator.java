/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.io.*;
import java.util.*;
import java.util.ArrayList;

/**
 *
 */
public class Administrator {
    private String username;
    private String password;

    public Administrator(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean login(String inputUsername, String inputPassword) {
        return inputUsername.equals(username) && inputPassword.equals(password);
    }

    // Placeholder for logout or other admin functions
    public void logout() {
        System.out.println("Administrator logged out.");
    }
}

