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
import java.time.LocalDate;
import java.io.*;

/**
 *
 */



public class Booking {
    private int bookingId;
    private LocalDate bookingDate;
    private String customerName;
    private String hallName;
    private String bookingStatus;  // Add a new field for status

    public Booking(int bookingId, LocalDate bookingDate, String customerName, String hallName, String bookingStatus) {
        this.bookingId = bookingId;
        this.bookingDate = bookingDate;
        this.customerName = customerName;
        this.hallName = hallName;
        this.bookingStatus = bookingStatus;  // Initialize the new field
    }

    // Getters and setters
    public int getBookingId() { return bookingId; }
    public LocalDate getBookingDate() { return bookingDate; }
    public String getCustomerName() { return customerName; }
    public String getHallName() { return hallName; }
    public String getBookingStatus() { return bookingStatus; }  // Getter for status

    // Check if the booking is upcoming
    public boolean isUpcoming() {
        return bookingDate.isAfter(LocalDate.now());
    }

    // Check if the booking is in the past
    public boolean isPast() {
        return bookingDate.isBefore(LocalDate.now());
    }

    @Override
    public String toString() {
        return bookingId + "," + bookingDate + "," + customerName + "," + hallName + "," + bookingStatus;
    }

    // Load bookings from file
    public static List<Booking> loadBookingsFromFile() {
        List<Booking> bookingList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("bookings.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int bookingId = Integer.parseInt(parts[0]);
                LocalDate bookingDate = LocalDate.parse(parts[1]);
                String customerName = parts[2];
                String hallName = parts[3];
                String bookingStatus = parts[4];  // Read the booking status
                bookingList.add(new Booking(bookingId, bookingDate, customerName, hallName, bookingStatus));
            }
        } catch (IOException e) {
            System.out.println("Error loading booking data.");
        }
        return bookingList;
    }

    // Save bookings to file
    public static void saveBookingsToFile(List<Booking> bookingList) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("bookings.txt"))) {
            for (Booking booking : bookingList) {
                writer.println(booking.toString());
            }
        } catch (IOException e) {
            System.out.println("Error saving booking data.");
        }
    }
}


