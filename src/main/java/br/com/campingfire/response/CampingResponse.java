package br.com.campingfire.response;

import br.com.campingfire.enums.State;
import br.com.campingfire.model.Camping;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CampingResponse implements Serializable {

    @ApiModelProperty(value = "Camping ID", required = true)
    private Long id;

    @ApiModelProperty(value = "Camping Name", required = true)
    private String name;

    @ApiModelProperty(value = "Camping State", required = true)
    private State state;

    @ApiModelProperty(value = "Camping City", required = true)
    private String city;

    @ApiModelProperty(value = "Camping Address", required = true)
    private String address;

    @ApiModelProperty(value = "Camping Description")
    private String description;

    @ApiModelProperty(value = "Camping Contact", required = true)
    private Long contact;

    @ApiModelProperty(value = "Camping Image URL", required = true)
    private String imageUrl;

    public CampingResponse(Camping camping) {

        this.id = camping.getId();
        this.name = camping.getName();
        this.state = camping.getState();
        this.city = camping.getCity();
        this.address = camping.getAddress();
        this.description = camping.getDescription();
        this.contact = camping.getContact();

    }

}
