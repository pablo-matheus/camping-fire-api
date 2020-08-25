package br.com.campingfire.request;

import br.com.campingfire.enums.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampingRequest implements Serializable {

    @NotBlank(message = "Camping name is required")
    private String name;

    @NotNull(message = "Camping state is required")
    private State state;

    @NotBlank(message = "Camping city is required")
    private String city;

    private String description;

    @NotNull(message = "Camping contact is required")
    @Min(value = 10, message = "Contact must be at least 10 characters")
    private Long contact;

    @NotNull(message = "User id is required")
    private Long userId;

}
