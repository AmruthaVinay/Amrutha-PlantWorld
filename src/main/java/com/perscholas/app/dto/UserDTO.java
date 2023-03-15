package com.perscholas.app.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level= AccessLevel.PRIVATE)
@Data@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    String email;
    String userName;

}
