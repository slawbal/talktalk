package com.sb.talktalk.frontend.domain;

public class UserState {
    private int id;
    private Long lastUpdate;

    public UserState() {
    }

    public UserState(int id, Long lastUpdate) {
        this.id = id;
        this.lastUpdate = lastUpdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
