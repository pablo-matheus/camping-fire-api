package br.com.camping.model;

import br.com.camping.request.UserRequest;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 12)
    private Long id;

    @Column(length = 45, nullable = false)
    private String name;

    //TODO Name unique constraint
    @Column(length = 50, nullable = false, unique = true)
    private String email;

    @Column(length = 16, nullable = false)
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Camping> campings;

    public User(UserRequest userRequest) {
        this.name = userRequest.getName();
        this.email = userRequest.getEmail();
        this.password = userRequest.getPassword();
    }

}
