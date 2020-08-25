package br.com.campingfire.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "images")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 12)
    private Long id;

    @Column(length = 200, nullable = false)
    private String name;

    @Column(length = 60, nullable = false)
    private String type;

    @Column(length = 1000, nullable = false)
    private byte[] file;

    @ManyToOne
    @JoinColumn(name = "id_camping", nullable = false, foreignKey = @ForeignKey(name = "fk_campings_images"))
    private Camping camping;

    public Image(String name, String type, byte[] file) {

        this.name = name;
        this.type = type;
        this.file = file;

    }

}
