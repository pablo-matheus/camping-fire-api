package br.com.camping.dto;

import br.com.camping.enums.State;
import br.com.camping.model.Camp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampResponseDTO implements Serializable {

    private Long id;
    private String name;
    private State state;
    private String city;
    private String description;
    private Long contact;
    private String image;

    public CampResponseDTO(Camp camp) {

        this.id = camp.getId();
        this.name = camp.getName();
        this.state = camp.getState();
        this.city = camp.getCity();
        this.description = camp.getDescription();
        this.contact = camp.getContact();
        this.image = camp.getImage();

    }

}
