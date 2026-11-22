/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
/**
 *
 */
public class AdminGUI extends javax.swing.JFrame {
    private Administrator admin;
    private List<SchedulerStaff> schedulerStaffList;
    private List<User> userList;
    private List<Booking> bookingList;
    /**
     * Creates new form AdminGUI
     */
    public AdminGUI() {
        initComponents();
        admin = new Administrator("admin", "password");  // Example admin login

        // Load data from files
        schedulerStaffList = SchedulerStaff.loadStaffFromFile();
        userList = User.loadUsersFromFile();
        bookingList = Booking.loadBookingsFromFile();

        // Set up GUI components
        setTitle("Administrator Panel");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JButton loginButton = new JButton("Login");
        JButton manageSchedulerStaffButton = new JButton("Manage Scheduler Staff");
        JButton manageUsersButton = new JButton("Manage Users");
        JButton manageBookingsButton = new JButton("Manage Bookings");
        JButton logoutButton = new JButton("Logout");

        // Add action listeners for each button
        loginButton.addActionListener(e -> showLoginDialog());
        manageSchedulerStaffButton.addActionListener(e -> manageSchedulerStaff());
        manageUsersButton.addActionListener(e -> manageUsers());
        manageBookingsButton.addActionListener(e -> manageBookings());
        logoutButton.addActionListener(e -> logout());

        add(loginButton);
        add(manageSchedulerStaffButton);
        add(manageUsersButton);
        add(manageBookingsButton);
        add(logoutButton);
    }
    private void showLoginDialog() {
        String username = JOptionPane.showInputDialog(this, "Enter Username:");
        String password = JOptionPane.showInputDialog(this, "Enter Password:");
        if (admin.login(username, password)) {
            JOptionPane.showMessageDialog(this, "Login Successful!");
        } else {
            JOptionPane.showMessageDialog(this, "Login Failed!");
        }
    }

    private void manageSchedulerStaff() {
        String[] options = {"Add Staff", "View Staff", "Edit Staff", "Delete Staff"};
        int choice = JOptionPane.showOptionDialog(this, "Choose an option", "Scheduler Staff Management",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        switch (choice) {
            case 0:
                addSchedulerStaff();
                break;
            case 1:
                viewSchedulerStaff();
                break;
            case 2:
                editSchedulerStaff();
                break;
            case 3:
                deleteSchedulerStaff();
                break;
        }
    }

    private void addSchedulerStaff() {
        String name = JOptionPane.showInputDialog(this, "Enter Scheduler Name:");
        String id = JOptionPane.showInputDialog(this, "Enter Scheduler ID:");
        String position = JOptionPane.showInputDialog(this, "Enter Scheduler Position:");
        String contact = JOptionPane.showInputDialog(this, "Enter Scheduler Contact Info:");

        try {
            int idInt = Integer.parseInt(id);  // Convert ID to integer
            SchedulerStaff newStaff = new SchedulerStaff(idInt, name, position, contact);
            schedulerStaffList.add(newStaff);

            SchedulerStaff.saveStaffToFile(schedulerStaffList);
            JOptionPane.showMessageDialog(this, "Scheduler Staff added successfully!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid ID format. Please enter a valid number.");
        }
    }

    private void viewSchedulerStaff() {
        schedulerStaffList = SchedulerStaff.loadStaffFromFile();  // Reload data from file
        StringBuilder staffInfo = new StringBuilder();
        for (SchedulerStaff staff : schedulerStaffList) {
            staffInfo.append(staff.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(this, staffInfo.toString());
    }
    private void editSchedulerStaff() {
        String id = JOptionPane.showInputDialog(this, "Enter Scheduler ID to edit:");

        try {
            int idInt = Integer.parseInt(id);  // Convert the input ID to an int
            for (SchedulerStaff staff : schedulerStaffList) {
                if (staff.getId() == idInt) {  // Use '==' to compare int values
                    String newName = JOptionPane.showInputDialog(this, "Enter new name for Scheduler:");
                    staff.setName(newName);
                    SchedulerStaff.saveStaffToFile(schedulerStaffList);
                    JOptionPane.showMessageDialog(this, "Scheduler Staff updated successfully!");
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Scheduler ID not found!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid ID format. Please enter a number.");
        }
    }
    private void deleteSchedulerStaff() {
        String id = JOptionPane.showInputDialog(this, "Enter Scheduler ID to delete:");

        try {
            int idInt = Integer.parseInt(id);  // Convert the input ID to an int
            boolean removed = schedulerStaffList.removeIf(staff -> staff.getId() == idInt);  // Use '==' to compare int values

            if (removed) {
                SchedulerStaff.saveStaffToFile(schedulerStaffList);
                JOptionPane.showMessageDialog(this, "Scheduler Staff deleted successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Scheduler ID not found!");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid ID format. Please enter a number.");
        }
    }
    private void manageUsers() {
        String[] options = {"View Users", "Block/Delete User"};
        int choice = JOptionPane.showOptionDialog(this, "Choose an option", "User Management",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        switch (choice) {
            case 0:
                viewUsers();
                break;
            case 1:
                blockOrDeleteUser();
                break;
        }
    }

    private void viewUsers() {
        userList = User.loadUsersFromFile();  // Reload data from file
        StringBuilder userInfo = new StringBuilder();
        for (User user : userList) {
            userInfo.append(user.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(this, userInfo.toString());
    }

    private void blockOrDeleteUser() {
        String username = JOptionPane.showInputDialog(this, "Enter Username to block/delete:");
        boolean userRemoved = userList.removeIf(user -> user.getName().equals(username));

        if (userRemoved) {
            User.saveUsersToFile(userList);
            JOptionPane.showMessageDialog(this, "User blocked/deleted successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "User not found!");
        }
    }


    private void manageBookings() {
        String[] options = {"View Upcoming Bookings", "View Past Bookings"};
        int choice = JOptionPane.showOptionDialog(this, "Choose an option", "Booking Management",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        switch (choice) {
            case 0:
                viewBookings(true);
                break;
            case 1:
                viewBookings(false);
                break;
        }
    }

    private void viewBookings(boolean upcoming) {
        bookingList = Booking.loadBookingsFromFile();  // Reload the data from file
        StringBuilder bookingInfo = new StringBuilder();
        for (Booking booking : bookingList) {
            if (upcoming && booking.isUpcoming()) {
                bookingInfo.append(booking.toString()).append("\n");
            } else if (!upcoming && !booking.isUpcoming()) {
                bookingInfo.append(booking.toString()).append("\n");
            }
        }
        JOptionPane.showMessageDialog(this, bookingInfo.toString());
    }


    private void logout() {
        JOptionPane.showMessageDialog(this, "Logged out!");
        System.exit(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

//    private void initComponents() {
//
//        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
//
//        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
//        getContentPane().setLayout(layout);
//        layout.setHorizontalGroup(
//            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGap(0, 400, Short.MAX_VALUE)
//        );
//        layout.setVerticalGroup(
//            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGap(0, 300, Short.MAX_VALUE)
//        );
//
//        pack();
//    }// </editor-fold>      
    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(AdminGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(AdminGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(AdminGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(AdminGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        new AdminGUI();
//
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new AdminGUI().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

