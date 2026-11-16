import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PasswordManager manager = new PasswordManager();

            VaultStorage storage = new VaultStorage("data/credentials.csv");

            manager.getEntries().addAll(storage.loadEntries());

            new Dashboard(manager, storage);
        });
    }
}