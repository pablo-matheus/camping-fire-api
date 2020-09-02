package br.com.campingfire.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity(name = "roles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable, GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 2)
    private Long id;

    @Column(length = 25, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    @Override
    public String getAuthority() {
        return name;
    }

}
