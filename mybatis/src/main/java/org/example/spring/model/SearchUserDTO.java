package org.example.spring.model;

import lombok.Data;

@Data
public class SearchUserDTO {
    private Integer userId;
    private String userName;
    private Integer userAge;
    private String email;
}
