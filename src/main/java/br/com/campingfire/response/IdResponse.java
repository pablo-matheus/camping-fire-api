package br.com.campingfire.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IdResponse implements Serializable {

    @ApiModelProperty(value = "ID", required = true)
    private Long id;

}
