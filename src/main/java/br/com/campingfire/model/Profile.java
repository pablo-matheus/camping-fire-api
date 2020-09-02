package br.com.campingfire.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "profiles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Profile implements Serializable, GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 2)
    private Long id;

    @Column(length = 25, nullable = false)
    private String name;

    @ManyToMany
    private List<User> users;

    @Override
    public String getAuthority() {
        return name;
    }

}
