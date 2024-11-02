package users;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserManager {
    private final List<User> users;

    public UserManager(User... initialUsers) {
        this.users = new ArrayList<>();
        this.users.addAll(Arrays.asList(initialUsers));
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public boolean removeUser(String username) {
        var user = this.findUserByUsername(username);
        return this.users.remove(user);
    }

    public User findUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public User login(String username, String password) {
        for (User user : users) {
            if (user.authenticate(username, password)) {
                return user;
            }
        }
        return null;
    }

    public List<User> getUsers() {
        return users;
    }
}
