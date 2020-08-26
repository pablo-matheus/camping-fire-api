package br.com.campingfire.response;

import br.com.campingfire.model.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse implements Serializable {

    @ApiModelProperty(value = "User ID", required = true)
    private Long id;

    @ApiModelProperty(value = "User Name", required = true)
    private String name;

    @ApiModelProperty(value = "User Email", required = true)
    private String email;

    public UserResponse(User user) {

        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();

    }

}
