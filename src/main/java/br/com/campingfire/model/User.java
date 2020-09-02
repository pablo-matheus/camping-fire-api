package br.com.campingfire.model;

import br.com.campingfire.request.UserSubmitRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"email"}, name = "uc_user_email"))
public class User implements Serializable, UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 12)
    private Long id;

    @Column(length = 45, nullable = false)
    private String name;

    @Column(length = 50, nullable = false)
    private String email;

    @Column(length = 60, nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "users_roles",
        joinColumns = @JoinColumn(name = "id_user"),
        inverseJoinColumns = @JoinColumn(name = "id_role"),
        foreignKey = @ForeignKey(name = "fk_user_role"),
        inverseForeignKey = @ForeignKey(name = "fk_role_user")
    )
    private Set<Role> roles;

    @OneToMany(mappedBy = "user")
    private List<Camping> campings;

    public User(UserSubmitRequest userSubmitRequest) {
        this.name = userSubmitRequest.getName();
        this.email = userSubmitRequest.getEmail();
        this.password = userSubmitRequest.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
