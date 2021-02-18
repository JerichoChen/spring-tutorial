package org.example.spring.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

    private Integer userId;
    private String userName;
    private Integer userAge;
    private String email;
}
