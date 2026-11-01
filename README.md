
# CS193 final project - PasswordVault

A simple Java-based desktop application for organizing and managing account credentials in one centralized location.

## Project Goal

Managing multiple online accounts requires remembering numerous usernames and passwords, which can become difficult and inconvenient. People often resort to reusing passwords or storing their credentials in disorganized locations.

PasswordVault aims to provide a simple solution by allowing users to store, organize, search, and retrieve their account credentials through an easy-to-use graphical interface.

## MVP Scope

The Minimum Viable Product (MVP) will focus on the core functionality needed to make PasswordVault usable.

- **Add Credentials:** Allow users to save new account information, including website names, usernames, and passwords.
- **View Credentials:** Display saved accounts and allow users to retrieve their stored information.
- **Search Accounts:** Allow users to search for specific accounts using website names or usernames.
- **Delete Credentials:** Allow users to remove saved account entries.
- **Local Data Storage:** Save account information in a local CSV file so that entries remain available between application sessions.
- **Graphical User Interface:** Provide a simple desktop interface using Java Swing.

## Future Plans (Version 2.0+)

The following features may be added after completing the MVP:

- **Edit Existing Entries:** Allow users to modify previously saved usernames, passwords, and website information.
- **Password Categories:** Organize credentials into categories such as Education, Social Media, Finance, and Entertainment.
- **Password Generator:** Generate random passwords based on user-selected length and character requirements.
- **Favorites:** Allow users to mark frequently accessed accounts for quicker retrieval.

## Tech Stack

### Programming Languages
- Java
- Bash

### Java Libraries and Packages
- `javax.swing` - Creating the graphical user interface.
- `java.awt` - Managing interface layouts and interactions.
- `java.util.ArrayList` - Managing password entries.
- `java.io` - Reading and writing local files.
- `java.nio.file` - Managing files and directories.

### Bash Utilities
- `cp` - Creating backups of stored data.
- `mkdir` - Creating directories.
- `date` - Generating timestamps for backup filenames.
- `java` - Launching the application.

### Data Storage
- Local CSV file for persistent data storage.
- Java ArrayList for managing entries while the application is running.

### Development Tools
- IntelliJ IDEA
- Java JDK
- Git
- GitHub

## Project Structure

```text
PasswordVault/
├── src/
│   ├── Main.java
│   ├── PasswordEntry.java
│   ├── PasswordManager.java
│   ├── Dashboard.java
│   ├── PasswordForm.java
│   └── VaultStorage.java
├── scripts/
│   ├── run.sh
│   └── backup.sh
├── data/
├── README.md
└── .gitignore
```

## Security Notice

This initial version is an educational prototype and does not encrypt stored credentials. The CSV file should only contain fictional credentials used for testing and demonstration purposes. Secure encrypted storage may be considered as a future improvement.

## Project Status

Currently in the planning and initial development stage.
