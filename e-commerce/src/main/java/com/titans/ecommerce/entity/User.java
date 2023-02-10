package com.titans.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(unique = true)
    private String username; // should be email id

    private String password;

    public enum State {normal, banned, closed}
    @Enumerated(EnumType.STRING)
    private State state;

    @OneToOne
    private Cart cart;

    private Date registrationTime;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", state=" + state +
                ", cart=" + cart +
                '}';
    }
}
