package com.example.BucketList.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Getter
@Setter
@Table(name="admins")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long adminId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable(
            name="admins_roles",
            joinColumns={@JoinColumn(name="ADMIN_ID", referencedColumnName="adminId")},
            inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="roleId")})
    private List<Role> roles = new ArrayList<>();
}