package com.notanoty.demo.User;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.notanoty.demo.Member.Member;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "users")  // Escape reserved keyword
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("login")
    private String login;

    @JsonProperty("password")
    private String password;

}
