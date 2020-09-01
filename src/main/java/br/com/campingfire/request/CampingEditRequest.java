package br.com.campingfire.request;

import br.com.campingfire.enums.State;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampingEditRequest implements Serializable {

    @ApiModelProperty(value = "Camping Name", required = true)
    @NotBlank(message = "Name is required")
    @Size(max = 30, message = "Name must be at maximum 30 characters")
    private String name;

    @ApiModelProperty(value = "Camping State", required = true)
    @NotNull(message = "State is required")
//    @Size(min = 2, max = 2, message = "Name must have 2 characters")
    private State state;

    @ApiModelProperty(value = "Camping City", required = true)
    @NotBlank(message = "City is required")
    @Size(max = 25, message = "City must be at maximum 25 characters")
    private String city;

    @ApiModelProperty(value = "Camping Address", required = true)
    @NotBlank(message = "Address is required")
    @Size(max = 80, message = "Address must be at maximum 25 characters")
    private String address;

    @ApiModelProperty(value = "Camping Description")
    @Size(max = 300, message = "Description must be at maximum 25 characters")
    private String description;

    @ApiModelProperty(value = "Camping Contact", required = true)
    @NotNull(message = "Contact is required")
//    @Size(min = 10, message = "Contact must be at least 10 characters")
    private Long contact;

}
