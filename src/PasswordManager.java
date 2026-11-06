import java.util.ArrayList;
public class PasswordManager {
    private ArrayList<PasswordEntry> entries;

    public PasswordManager() {
        entries = new ArrayList<>();
    }

    public void addEntry(PasswordEntry entry) {
        if (entry != null) {
            entries.add(entry);
        }
    }

    public ArrayList<PasswordEntry> getEntries() {
        return new ArrayList<>(entries);
    }

    public PasswordEntry getEntry(int index) {
        return entries.get(index);
    }

    public int getEntryCount() {
        return entries.size();
    }
}
