package br.com.campingfire.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IdResponse implements Serializable {

    @ApiModelProperty(value = "ID", required = true)
    private Long id;

}
