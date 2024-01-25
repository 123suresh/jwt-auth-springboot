package com.springPostgres.SpringPSQL.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

//now to achieve auth implement this class from UserDetails
public class User implements UserDetails {
    @Id
    @SequenceGenerator(
            name="student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )

    @Column(name="id", nullable = false, unique = true, updatable = false, length = 50)
    private Integer id;

    @NotNull(message="first name is required")
    @Size(min = 3, max = 50)
    @Column(nullable = false)
    private String firstName;
    @NotNull(message="last name is required")
    @Size(min = 3, max = 50)
    @Column(nullable = false)
    private String lastName;
    @NotNull(message="email is required")
    @Email
    private String email;
    @NotNull(message="password is required")
    @Size(min=5, max = 8)
    @Column(nullable = false)
    private String password;
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
