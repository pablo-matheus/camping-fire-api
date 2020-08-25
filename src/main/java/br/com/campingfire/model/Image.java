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

    @Column(length = 30, nullable = false)
    private String name;

    @Column(length = 25, nullable = false)
    private String type;

    @Column(length = 1000, nullable = false)
    private byte[] pictureBytes;

    @ManyToOne
    @JoinColumn(name = "id_camping", nullable = false, foreignKey = @ForeignKey(name = "fk_campings_images"))
    private Camping camping;

    public Image(String name, String type, byte[] pictureBytes) {

        this.name = name;
        this.type = type;
        this.pictureBytes = pictureBytes;

    }

}
