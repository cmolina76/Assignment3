package com.cejv679.DBPersistance.repository;

public class PlayerNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private long id;

    public PlayerNotFoundException(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

}
