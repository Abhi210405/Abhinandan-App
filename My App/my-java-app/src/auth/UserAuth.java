import java.util.HashMap;

public class UserAuth {
    private final HashMap<String, String> users = new HashMap<>();

    // Register a new user. Returns true if successful, false if username exists.
    public boolean register(String username, String password) {
        if (users.containsKey(username.toLowerCase())) {
            return false; // User already exists
        }
        users.put(username.toLowerCase(), password);
        return true;
    }

    // Login with username and password. Returns true if credentials match.
    public boolean login(String username, String password) {
        String stored = users.get(username.toLowerCase());
        if (stored == null) return false;
        return stored.equals(password);
    }
}