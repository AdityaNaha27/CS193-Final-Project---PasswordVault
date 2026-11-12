import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class VaultStorage {

    private String filePath;

    public VaultStorage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<PasswordEntry> loadEntries() {
        ArrayList<PasswordEntry> entries = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", -1);

                if (parts.length == 3) {
                    PasswordEntry entry = new PasswordEntry(
                            parts[0],
                            parts[1],
                            parts[2]
                    );

                    entries.add(entry);
                }
            }

        } catch (IOException e) {
            System.out.println("No existing credential file found.");
        }

        return entries;
    }

    public void saveEntries(ArrayList<PasswordEntry> entries) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

            for (PasswordEntry entry : entries) {
                writer.write(entry.getWebsite() + ","
                        + entry.getUsername() + ","
                        + entry.getPassword());

                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error saving credentials.");
        }
    }
}