/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 */
public class Main {
    public static void main(String[] args) {
        // The main method will start the application if needed
        Main mainInstance = new Main();
        mainInstance.showAdminGUI();
    }

    public void showAdminGUI() {
        AdminGUI admin = new AdminGUI();
        admin.setVisible(true); // Make the Admin GUI visible
    }

    void setVisible(boolean b) {
        // Optional: Implement if you want to use Main as a GUI itself
        throw new UnsupportedOperationException("Not supported yet.");
    }
}