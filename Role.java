package users;

public enum Role {
    ADMIN("admin"), USER("cajero");

    private final String displayName;

    Role(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static Role parse(String role) {
        for (Role r : Role.values()) {
            if (r.getDisplayName().equalsIgnoreCase(role)) {
                return r;
            }
        }
        return null;
    }
}
