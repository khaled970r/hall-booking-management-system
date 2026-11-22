/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 */
public class CustomerIssue {
    private String issueID;
    private String hall;
    private String customer;
    private String issue;
    private String dateReported;
    private String status;  // This will be set later

    // Constructor
    public CustomerIssue(String issueID, String hall, String customer, String issue, String dateReported) {
        this.issueID = issueID;
        this.hall = hall;
        this.customer = customer;
        this.issue = issue;
        this.dateReported = dateReported;
        this.status = ""; // Initialize status as empty
    }

    // Getters and Setters
    public String getIssueID() { return issueID; }
    public String getHall() { return hall; }
    public String getCustomer() { return customer; }
    public String getIssue() { return issue; }
    public String getDateReported() { return dateReported; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
