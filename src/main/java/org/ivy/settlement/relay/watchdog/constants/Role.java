package org.ivy.settlement.relay.watchdog.constants;

public enum Role {
    DEFENDER(0, "defender"),
    CHALLENGER(1,"challenger");

    private int id;

    private String role;

    Role(Integer id, String role) {
        this.id = id;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getRole() {
        return role;
    }
}
