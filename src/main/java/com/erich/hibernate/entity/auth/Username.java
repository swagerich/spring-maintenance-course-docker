package com.erich.hibernate.entity.auth;

import com.erich.hibernate.entity.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "usernames")
public class Username extends AbstractEntity {

    @Column(nullable = false, unique = true)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String apellido;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "usernames_roles",
            joinColumns = @JoinColumn(name = "username_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id",referencedColumnName = "id"))
    private Set<Role> roles;
}
