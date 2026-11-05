public class PasswordEntry {
    private String website;
    private String username;
    private String password;

    public PasswordEntry(String website, String username, String password) {
        this.website = website;
        this.username = username;
        this.password = password;
    }

    public String getWebsite() {
        return this.website;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }
}
