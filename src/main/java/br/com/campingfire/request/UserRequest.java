package br.com.campingfire.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest implements Serializable {

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Email address is not valid")
    @NotBlank(message = "Email address is required")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

}