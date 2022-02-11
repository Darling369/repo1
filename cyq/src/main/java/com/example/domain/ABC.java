package com.example.domain;

import java.util.Objects;

public class ABC {
        private String username;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

    @Override
    public String toString() {
        return username ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ABC abc = (ABC) o;
        return Objects.equals(username, abc.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}

