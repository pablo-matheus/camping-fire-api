package br.com.campingfire.response;

import br.com.campingfire.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse implements Serializable {

    private Long id;
    private String name;
    private String email;

    public UserResponse(User user) {

        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();

    }

}
