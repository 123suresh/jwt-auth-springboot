package com.springPostgres.SpringPSQL.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "users")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
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

    @NotNull(message="name is required")
    @Size(min = 3, max = 50)
    @Column(nullable = false)
    private String userName;
    @NotNull(message="email is required")
    @Email
    private String email;
    @NotNull(message="password is required")
    @Size(min=5, max = 8)
    @Column(nullable = false)
    private String password;
}
