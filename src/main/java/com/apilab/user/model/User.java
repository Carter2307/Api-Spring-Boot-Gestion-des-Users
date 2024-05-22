package com.apilab.user.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {


    @Id //Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto increment id
    private long id;

    @Column(name="first_name", nullable = false)
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email", unique = true)
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="location")
    private String location;

    @Column(name="role")
    private String role;

    @Column(name="profile_picture")
    private String profilePicture;

    @Column(name="bio")
    private String bio;

    @Column(name="account_status")
    private String accountStatus;
}
