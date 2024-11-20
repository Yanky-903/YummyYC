package com.Yashraj.restaurantyummy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

//    @Column(name = "age", unique = true, nullable = false)
//    private Long age;

    @Column(name="password", nullable = false)
    private String password;

    @Column(name="address", nullable = false)
    private String addr;

    @Column(name="city", nullable = false)
    private String city;

    @Column(name="pincode", nullable = false)
    private Long pinCode;

    @Column(name = "access_token", unique = true)
    private String accessToken;

    @Column(name="updated_on")
    private LocalDateTime updatedOn;
}

