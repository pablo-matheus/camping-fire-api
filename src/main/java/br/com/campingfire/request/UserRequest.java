package br.com.campingfire.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest implements Serializable {

    @ApiModelProperty(value = "User Name", required = true)
    @NotBlank(message = "Name is required")
    @Size(max = 45, message = "Name must be a maximum of 45 characters")
    private String name;

    //TODO Example of filling?
    @ApiModelProperty(value = "User Email", required = true)
    @NotBlank(message = "Email address is required")
    @Size(min = 12, max = 50, message = "Email must be between 12 and 50 characters")
    @Email(message = "Email address is not valid")
    private String email;

    @ApiModelProperty(value = "User Password", required = true)
    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 16, message = "Password must be between 8 and 16 characters")
    private String password;

}
