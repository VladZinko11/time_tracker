package com.zinko.time_tracker.service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthDto implements Serializable {
    @Email(regexp = ".+[@].+[\\.].+")
    @NotBlank(message = "enter your email")
    String email;
    @Pattern(regexp = "^(?=.+[0-9])(?=.+[a-z])(?=.+[A-Z]).*$", message = "password must contain at least one number, an uppercase letter and lowercase latter ")
    @NotBlank(message = "password can't be empty")
    @Length(min = 8, message = "too short password")
    String password;
}