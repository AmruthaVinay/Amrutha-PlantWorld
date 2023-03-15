package com.perscholas.app.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@ToString
public class LoginUser {

    String email;
    String password;

}
