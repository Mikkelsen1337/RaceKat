package com.example.racekat.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*DTO'er har jeg fundet ud af, bruges til at sende data uden at inkludere
specifikke oplysninger, fek.s. Password

 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String username;
    private String email;

}
