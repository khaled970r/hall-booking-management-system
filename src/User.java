/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class User {
    private String name;
    private String contact;

    public User(String name, String contact) {
        this.name = name;
        this.contact = contact;
    }

    public String getName() { return name; }
    public String getContact() { return contact; }

    @Override
    public String toString() {
        return name + "," + contact;
    }

    public static List<User> loadUsersFromFile() {
        List<User> userList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0];
                String contact = parts[1];
                userList.add(new User(name, contact));
            }
        } catch (IOException e) {
            System.out.println("Error loading users data.");
        }
        return userList;
    }

    public static void saveUsersToFile(List<User> userList) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("users.txt"))) {
            for (User user : userList) {
                writer.println(user.toString());
            }
        } catch (IOException e) {
            System.out.println("Error saving users data.");
        }
    }
}
