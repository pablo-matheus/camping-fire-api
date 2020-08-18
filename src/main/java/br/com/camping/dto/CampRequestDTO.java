package br.com.camping.dto;

import br.com.camping.enums.State;
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
public class CampRequestDTO implements Serializable {

    @NotBlank(message = "Camp name is required")
    private String name;

    @NotNull(message = "Camp state is required")
    private State state;

    @NotBlank(message = "Camp city is required")
    private String city;

    private String description;

    @NotNull(message = "Camp contact is required")
    @Min(value = 10, message = "Contact must be at least 10 characters")
    private Long contact;

    private String image;

    @NotNull(message = "User id is required")
    private Long userId;

}
