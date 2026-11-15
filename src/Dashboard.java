import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Font;

public class Dashboard {

    private JFrame frame;
    private JPanel mainPanel;
    private JButton addButton;
    private JButton viewButton;
    private JButton searchButton;
    private JButton deleteButton;

    public Dashboard() {
        frame = new JFrame("PasswordVault");
        mainPanel = new JPanel();

        JLabel title = new JLabel("PasswordVault");
        title.setFont(new Font("Arial", Font.BOLD, 28));

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

        frame.setSize(400, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
