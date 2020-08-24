package br.com.campingfire.response;

import br.com.campingfire.enums.State;
import br.com.campingfire.model.Camping;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampingResponse implements Serializable {

    private Long id;
    private String name;
    private State state;
    private String city;
    private String description;
    private Long contact;
    private String image;

    public CampingResponse(Camping camping) {

        this.id = camping.getId();
        this.name = camping.getName();
        this.state = camping.getState();
        this.city = camping.getCity();
        this.description = camping.getDescription();
        this.contact = camping.getContact();
        this.image = camping.getImage();

    }

}
