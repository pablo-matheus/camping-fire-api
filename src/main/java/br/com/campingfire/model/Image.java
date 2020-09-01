package br.com.campingfire.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "images")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Image implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 12)
    private Long id;

    @Column(length = 200, nullable = false)
    private String name;

    @Column(length = 300, nullable = false)
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_camping", nullable = false, foreignKey = @ForeignKey(name = "fk_campings_images"))
    private Camping camping;

    public Image(String name, String url) {

        this.name = name;
        this.url = url;

    }

}
