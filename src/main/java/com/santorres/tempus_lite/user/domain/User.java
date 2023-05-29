package com.santorres.tempus_lite.user.domain;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String id;
    private String name;
    private String lastName;
    private String username;
    private String password;
    private boolean enabled;
}
