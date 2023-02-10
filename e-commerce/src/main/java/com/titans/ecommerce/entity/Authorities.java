package com.titans.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Authorities {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int userId;

    public enum Authority {user, admin, blocked}
    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Override
    public String toString() {
        return "Authorities{" +
                "id=" + id +
                ", userId=" + userId +
                ", authority=" + authority +
                '}';
    }
}
