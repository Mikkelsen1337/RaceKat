package com.example.racekat.domain.dtos;

/*DTO'er har jeg fundet ud af, bruges til at sende data uden at inkludere
specifikke oplysninger, fek.s. Password

 */

public class UserDTO {
    private String username;
    private String email;

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
