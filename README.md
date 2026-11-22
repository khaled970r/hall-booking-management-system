# Hall Booking Management System (Java)

A desktop application for managing event-hall bookings, built in Java with a Swing GUI (NetBeans). It supports four user roles, from customers booking and paying for halls to managers tracking sales and maintenance issues.

## Features

| Role | Features |
|---|---|
| **Customer** | Register / log in, browse available halls, book a hall, pay (cash or card), view receipt, booking history, leave feedback, edit profile |
| **Scheduler** | Manage halls, set hall availability schedules, plan maintenance schedules |
| **Administrator** | Manage users and staff, view the dashboard |
| **Manager** | Sales dashboard, customer issue management, assign schedulers to issues |

- Object-oriented design (`User`, `Booking`, `Administrator`, `CustomerIssue`, `Sale`, …)
- Data stored in plain-text files (bookings, halls, schedules, issues, sales)
- GUI forms built with the NetBeans GUI builder (`.form` files)

## Getting Started

1. Open the project folder in **Apache NetBeans** (it is a standard NetBeans Ant project; `build.xml` is included).
2. Build and run. The main entry point is `MainLoginHall`.
3. Keep the `.txt` data files in the project root; they are read and written at runtime.

Requires JDK 8+.

## Project Structure

```
hall-booking-management-system/
├── src/            # Java sources + Swing .form files + images
├── nbproject/      # NetBeans project configuration
├── build.xml
└── *.txt           # runtime data (halls, bookings, schedules, issues, sales)
```

## Team

Team project (4 members). My part was the **Customer module**: registration and login, hall booking, payment, receipt, booking history, feedback and profile.

## Tech Stack

Java · Swing · NetBeans · OOP · file-based persistence
