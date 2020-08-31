package br.com.campingfire.model;

import br.com.campingfire.request.CampingEditRequest;
import br.com.campingfire.request.CampingSubmitRequest;
import br.com.campingfire.enums.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "campings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Camping implements Serializable {

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

    //TODO This can be a new table (one to many)
    @Column(length = 11, nullable = false)
    private Long contact;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", nullable = false, foreignKey = @ForeignKey(name = "fk_users_campings"))
    private User user;

    @OneToMany(mappedBy = "camping")
    private List<Image> images;

    public Camping(CampingSubmitRequest campingSubmitRequest) {

        this.name = campingSubmitRequest.getName();
        this.state = campingSubmitRequest.getState();
        this.city = campingSubmitRequest.getCity();
        this.description = campingSubmitRequest.getDescription();
        this.contact = campingSubmitRequest.getContact();

    }

    public Camping(CampingEditRequest campingEditRequest) {

        this.name = campingEditRequest.getName();
        this.state = campingEditRequest.getState();
        this.city = campingEditRequest.getCity();
        this.description = campingEditRequest.getDescription();
        this.contact = campingEditRequest.getContact();

    }

}
