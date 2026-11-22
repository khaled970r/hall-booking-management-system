/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.io.*;
import java.util.*;
/**
 *
 */
public class SchedulerStaff {
    private int id;
    private String name;
    private String position;
    private String contact;

    public SchedulerStaff(int id, String name, String position, String contact) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.contact = contact;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getPosition() { return position; }
    public String getContact() { return contact; }

    public void setName(String name) { this.name = name; }

    @Override
    public String toString() {
        return id + "," + name + "," + position + "," + contact;
    }

    public static List<SchedulerStaff> loadStaffFromFile() {
        List<SchedulerStaff> staffList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("scheduler_staff.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                String position = parts[2];
                String contact = parts[3];
                staffList.add(new SchedulerStaff(id, name, position, contact));
            }
        } catch (IOException e) {
            System.out.println("Error loading scheduler staff data.");
        }
        return staffList;
    }

    public static void saveStaffToFile(List<SchedulerStaff> staffList) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("scheduler_staff.txt"))) {
            for (SchedulerStaff staff : staffList) {
                writer.println(staff.toString());
            }
        } catch (IOException e) {
            System.out.println("Error saving scheduler staff data.");
        }
    }
}

