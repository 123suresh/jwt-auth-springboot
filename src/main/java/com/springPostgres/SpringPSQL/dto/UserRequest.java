package com.springPostgres.SpringPSQL.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserRequest {
    @NotNull
    private String userName;
    @NotNull
    private String email;
    @NotNull
    private String password;
}
