package br.com.camping.dto;

import br.com.camping.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO implements Serializable {

    private Long id;
    private String name;
    private String email;

    public UserResponseDTO (User user) {

        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();

    }

}
