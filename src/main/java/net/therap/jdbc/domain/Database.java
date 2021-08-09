package net.therap.jdbc.domain;

/**
 * @author rumi.dipto
 * @since 8/9/21
 */
public class Database {

    private String databaseUrl, username, password;

    public Database(String databaseUrlValue, String usernameValue, String passwordValue) {
        databaseUrl = databaseUrlValue;
        username = usernameValue;
        password = passwordValue;
    }

    public String getDatabaseUrl() {
        return databaseUrl;
    }

    public void setDatabaseUrl(String databaseUrlValue) {
        databaseUrl = databaseUrlValue;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String usernameValue) {
        username = usernameValue;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String passwordValue) {
        password = passwordValue;
    }
}