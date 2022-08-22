package se.lexicon.todoapi.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(nullable = false, updatable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    private boolean expired;

    @ManyToMany(
            cascade = {CascadeType.DETACH, CascadeType.REFRESH},
            fetch = FetchType.EAGER
    )
    @JoinTable(name = "USERS_ROLES",
            joinColumns = {@JoinColumn(name = "USERNAME")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID")}
    )
    private Set<Role> roles;




    public void addRole(Role role) {
        if (role == null) throw new IllegalArgumentException("Role was null");
        if (roles == null) roles = new HashSet<>();

        roles.add(role);
    }


    public void removeRole(Role role) {
        if (role == null) throw new IllegalArgumentException("Role was null");
        if (roles != null) roles.remove(role);
    }

}
