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
@Table(name="users")
public class User {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String password;

    @OneToMany(targetEntity = Destination.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_fk", referencedColumnName = "userId")
    private List<Destination> bucketList;

    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable(
            name="users_roles",
            joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="userId")},
            inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="roleId")})
    private List<Role> roles = new ArrayList<>();
}
