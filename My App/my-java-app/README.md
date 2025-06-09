# MSP Construction App

## Overview
The MSP Construction App is a Java-based desktop application for managing construction projects and properties. It features a user-friendly GUI built with Swing, allowing users to manage accounts, view services, browse properties, and send inquiries.

## Project Structure
```
my-java-app
├── src
│   ├── MspConstructionApp.java       # Main application class for the MSP Construction app
│   ├── EmailUtil.java                # Utility functions for sending emails
│   └── db
│       └── DatabaseUtil.java         # Database connection and operations
├── sql
│   └── schema.sql                    # SQL schema for the MySQL database
└── README.md                         # Documentation for the project
```

## Setup Instructions

### 1. Clone the Repository
```sh
git clone <repository-url>
cd my-java-app
```

### 2. Database Setup
- Ensure you have MySQL installed and running on your system.
- Create the database and tables by running the SQL script:
  1. Open your MySQL client (e.g., MySQL Workbench, command line).
  2. Execute the commands in `sql/schema.sql`:
     ```sql
     SOURCE sql/schema.sql;
     ```
- Update your database credentials in `src/db/DatabaseUtil.java` if needed.

### 3. Dependencies
- **Jakarta Mail API**: Required for email functionality.
- **MySQL Connector/J**: Required for database connectivity.
- If you are using Gradle, add these to your `build.gradle`:
  ```gradle
  dependencies {
      implementation 'com.sun.mail:jakarta.mail:2.0.1'
      implementation 'mysql:mysql-connector-java:8.4.0'
  }
  ```
- If you are compiling manually, download the JARs and add them to your classpath.

### 4. Build and Run the Application
- **Compile the Java files:**
  ```sh
  javac -cp "path/to/mysql-connector-java.jar;path/to/jakarta.mail.jar" -d bin src/**/*.java
  ```
- **Run the application:**
  ```sh
  java -cp "bin;path/to/mysql-connector-java.jar;path/to/jakarta.mail.jar" MspConstructionApp
  ```

## Usage Guidelines
- **Registration/Login:** Users can register and log in to access features.
- **Navigation:** The application provides tabs for Home, Services, Properties, and Contact.
- **Inquiries:** Users can send inquiries about properties or services.
- **Admin Features:** (If implemented) Admins can manage users, properties, and services.

## Contributing
Contributions are welcome! Please fork the repository and submit a pull request, or open an issue for suggestions and bug reports.

## License
This project is licensed under the MIT License.

## Support
For any questions or issues, please open an issue in this repository.