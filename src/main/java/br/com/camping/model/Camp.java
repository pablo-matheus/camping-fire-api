package br.com.camping.model;

import br.com.camping.dto.CampRequestDTO;
import br.com.camping.enums.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "tbcamp")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Camp implements Serializable {

    private static final String STATES = "'AC', 'AL', 'AP', 'AM', 'BA', 'CE', " +
            "'DF', 'ES', 'GO', 'MA', 'MT', 'MS', 'MG', 'PA', 'PB', 'PR', 'PE', 'PI', " +
            "'RJ', 'RN', 'RS', 'RO', 'RR', 'SC', 'SP', 'SE', 'TO'";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 12)
    private Long id;

    @Column(length = 30, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = ("ENUM(" + STATES + ")"), nullable = false)
    private State state;

    @Column(length = 25, nullable = false)
    private String city;

    @Column(length = 300)
    private String description;

    /* TODO This can be a new table (one to many)*/
    @Column(length = 11, nullable = false)
    private Long contact;

    /* TODO This can be a new table (one to many) */
    @Column(length = 150)
    private String image;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false, foreignKey = @ForeignKey(name = "fk_user_camp"))
    private User user;

    public Camp(CampRequestDTO campRequestDTO) {

        this.name = campRequestDTO.getName();
        this.state = campRequestDTO.getState();
        this.city = campRequestDTO.getCity();
        this.description = campRequestDTO.getDescription();
        this.contact = campRequestDTO.getContact();
        this.image = campRequestDTO.getImage();

    }

}
