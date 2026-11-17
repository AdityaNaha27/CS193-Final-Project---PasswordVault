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
}