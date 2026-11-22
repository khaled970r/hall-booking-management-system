/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Sale {
    private String bookingID;
    private Date date;
    private String hall;
    private double amount;
    private String customer;

    // Constructor
    public Sale(String bookingID, Date date, String hall, double amount, String customer) {
        this.bookingID = bookingID;
        this.date = date;
        this.hall = hall;
        this.amount = amount;
        this.customer = customer;
    }

    // Getters
    public String getBookingID() { return bookingID; }
    public Date getDate() { return date; }
    public String getHall() { return hall; }
    public double getAmount() { return amount; }
    public String getCustomer() { return customer; }

    // Method to load sales data from file
    public static List<Sale> loadSalesData() {
        List<Sale> sales = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try (BufferedReader reader = new BufferedReader(new FileReader("sales_data.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                String bookingID = parts[0].split(": ")[1];
                Date date = sdf.parse(parts[1].split(": ")[1]);
                String hall = parts[2].split(": ")[1];
                double amount = Double.parseDouble(parts[3].split(": ")[1]);
                String customer = parts[4].split(": ")[1];

                sales.add(new Sale(bookingID, date, hall, amount, customer));
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return sales;
    }

public static List<Sale> filterSales(String filterOption) {
    List<Sale> sales = loadSalesData();
    List<Sale> filteredSales = new ArrayList<>();
    Calendar currentDate = Calendar.getInstance();  // Current date

    // Loop through sales and filter based on the selected option
    for (Sale sale : sales) {
        Calendar saleDate = Calendar.getInstance();
        saleDate.setTime(sale.getDate());  // Set sale date
        

        switch (filterOption) {
            case "Weekly":
                if (currentDate.get(Calendar.WEEK_OF_YEAR) == saleDate.get(Calendar.WEEK_OF_YEAR) &&
                    currentDate.get(Calendar.YEAR) == saleDate.get(Calendar.YEAR)) {
                    filteredSales.add(sale);
                }
                break;

            case "Monthly":
                if (currentDate.get(Calendar.MONTH) == saleDate.get(Calendar.MONTH) &&
                    currentDate.get(Calendar.YEAR) == saleDate.get(Calendar.YEAR)) {
                    filteredSales.add(sale);
                }
                break;

            case "Yearly":
                if (currentDate.get(Calendar.YEAR) == saleDate.get(Calendar.YEAR)) {
                    filteredSales.add(sale);
                }
                break;
        }
    }

    return filteredSales;
}

}
