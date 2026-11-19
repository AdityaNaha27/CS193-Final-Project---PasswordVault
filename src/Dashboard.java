import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.awt.GridLayout;

public class Dashboard {

    private JFrame frame;
    private JPanel mainPanel;
    private JButton addButton;
    private JButton viewButton;
    private JButton searchButton;
    private JButton deleteButton;

    private PasswordManager manager;
    private VaultStorage storage;

    public Dashboard(PasswordManager manager, VaultStorage storage) {
        this.manager = manager;
        this.storage = storage;

        frame = new JFrame("PasswordVault");
        mainPanel = new JPanel();

        JLabel title = new JLabel("PasswordVault");

        addButton = new JButton("Add Credential");
        viewButton = new JButton("View Credentials");
        searchButton = new JButton("Search Credentials");
        deleteButton = new JButton("Delete Credential");

        mainPanel.setLayout(new GridLayout(4, 1, 10, 10));

        mainPanel.add(addButton);
        mainPanel.add(viewButton);
        mainPanel.add(searchButton);
        mainPanel.add(deleteButton);

        frame.setLayout(new BorderLayout(10, 20));
        frame.add(title, BorderLayout.NORTH);
        frame.add(mainPanel, BorderLayout.CENTER);

        addButton.addActionListener(e -> addCredential());
        viewButton.addActionListener(e -> viewCredentials());
        searchButton.addActionListener(e -> searchCredentials());
        deleteButton.addActionListener(e -> deleteCredential());

        frame.setSize(400, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void addCredential() {
        JTextField websiteField = new JTextField();
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));

        panel.add(new JLabel("Website:"));
        panel.add(websiteField);

        panel.add(new JLabel("Username:"));
        panel.add(usernameField);

        panel.add(new JLabel("Password:"));
        panel.add(passwordField);

        int result = JOptionPane.showConfirmDialog(
                frame,
                panel,
                "Add Credential",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            String website = websiteField.getText().trim();
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());

            if (website.isEmpty() || username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill in all fields.");
            } else {
                PasswordEntry entry = new PasswordEntry(
                        website,
                        username,
                        password
                );

                manager.addEntry(entry);
                storage.saveEntries(manager.getEntries());

                JOptionPane.showMessageDialog(frame, "Credential added successfully!");
            }
        }
    }

    private void viewCredentials() {
        if (manager.getEntryCount() == 0) {
            JOptionPane.showMessageDialog(frame, "No credentials saved yet.");
            return;
        }

        StringBuilder message = new StringBuilder();

        for (PasswordEntry entry : manager.getEntries()) {
            message.append("Website: ")
                    .append(entry.getWebsite())
                    .append("\nUsername: ")
                    .append(entry.getUsername())
                    .append("\nPassword: ")
                    .append(entry.getPassword())
                    .append("\n\n");
        }

        JOptionPane.showMessageDialog(
                frame,
                message.toString(),
                "Saved Credentials",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void searchCredentials() {
        String website = JOptionPane.showInputDialog(
                frame,
                "Enter the website you want to search for:"
        );

        if (website == null || website.trim().isEmpty()) {
            return;
        }

        String search = website.trim();
        StringBuilder results = new StringBuilder();

        for (PasswordEntry entry : manager.getEntries()) {
            if (entry.getWebsite().toLowerCase().contains(search.toLowerCase())) {
                results.append("Website: ")
                        .append(entry.getWebsite())
                        .append("\nUsername: ")
                        .append(entry.getUsername())
                        .append("\nPassword: ")
                        .append(entry.getPassword())
                        .append("\n\n");
            }
        }

        if (results.length() == 0) {
            JOptionPane.showMessageDialog(frame, "No matching credentials found.");
        } else {
            JOptionPane.showMessageDialog(
                    frame,
                    results.toString(),
                    "Search Results",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    private void deleteCredential() {
        if (manager.getEntryCount() == 0) {
            JOptionPane.showMessageDialog(frame, "No credentials available to delete.");
            return;
        }

        String[] websites = new String[manager.getEntryCount()];

        for (int i = 0; i < manager.getEntryCount(); i++) {
            PasswordEntry entry = manager.getEntry(i);
            websites[i] = entry.getWebsite() + " - " + entry.getUsername();
        }

        String selected = (String) JOptionPane.showInputDialog(
                frame,
                "Select the credential you want to delete:",
                "Delete Credential",
                JOptionPane.QUESTION_MESSAGE,
                null,
                websites,
                websites[0]
        );

        if (selected == null) {
            return;
        }

        int index = -1;

        for (int i = 0; i < websites.length; i++) {
            if (websites[i].equals(selected)) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            int confirmation = JOptionPane.showConfirmDialog(
                    frame,
                    "Are you sure you want to delete this credential?",
                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirmation == JOptionPane.YES_OPTION) {
                java.util.ArrayList<PasswordEntry> entries = manager.getEntries();
                entries.remove(index);

                manager = new PasswordManager();

                for (PasswordEntry entry : entries) {
                    manager.addEntry(entry);
                }

                storage.saveEntries(manager.getEntries());

                JOptionPane.showMessageDialog(frame, "Credential deleted successfully!");
            }
        }
    }
}